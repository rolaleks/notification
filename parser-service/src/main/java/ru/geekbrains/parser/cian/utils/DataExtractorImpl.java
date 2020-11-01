package ru.geekbrains.parser.cian.utils;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import ru.geekbrains.entity.*;
import ru.geekbrains.parser.cian.utils.exception.PeriodNotFoundException;
import ru.geekbrains.parser.cian.utils.exception.PriceNotFoundException;
import ru.geekbrains.parser.cian.utils.exception.SquareInfoNotFoundException;
import ru.geekbrains.utils.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class DataExtractorImpl implements DataExtractor {


    @Override
    public Address getAddress(Element adTag) {
        Elements addressElements = adTag.select("a[data-name$=GeoLabel]");
        //Регион и город обозначаются одинаково: region
        String[] regionAndCity = addressElements.stream().filter(element -> element.attr("href").contains("region")).map(Element::text).toArray(String[]::new);
        String regionFromAd = regionAndCity[0]; //регион
        String cityFromAd = (regionAndCity.length > 1) ? regionAndCity[1] : regionFromAd; // город
        //Если в городе есть более крупное деление, чем на районы (например на округа), то district будет 2 шт.
        String[] districts = addressElements.stream().filter(element -> element.attr("href").contains("district")).map(Element::text).toArray(String[]::new);
        StringJoiner stringJoiner = new StringJoiner(", ");
        Arrays.stream(districts).forEach(stringJoiner::add);
        String districtFromAd = stringJoiner.toString();
        String streetFromAd = addressElements.stream().filter(element -> element.attr("href").contains("street")).map(Element::text).findFirst().orElse("улица не указана");

        System.out.print(regionFromAd + ", " + cityFromAd + ", ");
        System.out.print(districtFromAd + ", ");
        System.out.println(streetFromAd);

        Country country = new Country();
        country.setName("Россия");
        Region region = new Region();
        region.setName(regionFromAd);
        City city = new City();
        city.setName(cityFromAd);
        District district = new District();
        district.setName(districtFromAd);
        Street street = new Street();
        street.setName(streetFromAd);
        Address address = new Address();
        address.setCity(city);
        address.setCountry(country);
        address.setDistrict(district);
        address.setRegion(region);
        address.setStreet(street);
        return address;
    }

    @Override
    public Byte getQuantity(Element adTag) {

        Element adTitle = adTag.select("span[data-mark$=OfferTitle]").first();
        Element adSubtitle = adTag.select("span[data-mark$=OfferSubtitle]").first();

        Pattern roomsAmountPattern = Pattern.compile("[0-9]-комн.");
        Matcher matcherRoomsInTitle = roomsAmountPattern.matcher(adTitle.text());
        Matcher matcherRoomsInSubtitle = null;
        if (adSubtitle != null) {
            matcherRoomsInSubtitle = roomsAmountPattern.matcher(adSubtitle.text());
        }
        String roomsAmount;
        if (matcherRoomsInTitle.find()) {
            roomsAmount = matcherRoomsInTitle.group().substring(0, matcherRoomsInTitle.group().indexOf("-"));
        } else if (matcherRoomsInSubtitle != null && matcherRoomsInSubtitle.find()) {
            roomsAmount = matcherRoomsInSubtitle.group().substring(0, matcherRoomsInSubtitle.group().indexOf("-"));
        } else {
            roomsAmount = "0";
        }
        System.out.print("Количество комнат: " + roomsAmount + " ");

        return Byte.parseByte(roomsAmount);
    }

    @Override
    public Short getQuadrature(Element adTag) {

        Element adTitle = adTag.select("span[data-mark$=OfferTitle]").first();
        Element adSubtitle = adTag.select("span[data-mark$=OfferSubtitle]").first();

        Pattern squarePattern = Pattern.compile("\\d{1,4},?\\d{1,2}?\\sм²");
        Matcher matcherSquareInTitle = squarePattern.matcher(adTitle.text());
        Matcher matcherSquareInSubtitle = null;
        if (adSubtitle != null) {
            matcherSquareInSubtitle = squarePattern.matcher(adSubtitle.text());
        }
        String square;
        if (matcherSquareInTitle.find()) {
            square = matcherSquareInTitle.group();
        } else if (matcherSquareInSubtitle != null && matcherSquareInSubtitle.find()) {
            square = matcherSquareInSubtitle.group();
        } else {
            throw new SquareInfoNotFoundException("Apartment's square hasn't been found on the cian.ru page");
        }
        System.out.print("Площадь: " + square + " ");
        String squareWithoutMetersAndRounded = String.valueOf(Math.round(Float.parseFloat(square.replaceAll("\\sм²", "").replaceAll(",",""))));
        return Short.parseShort(squareWithoutMetersAndRounded);

    }

    @Override
    public String getPeriod(Element adTag) {
        Element adPriceContent = adTag.select("span[data-mark$=MainPrice]").first();
        Pattern periodPattern = Pattern.compile("[ЁёА-я]{3,5}", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcherPeriod = periodPattern.matcher(adPriceContent.text());
        String period;
        if (matcherPeriod.find()) {
            period = matcherPeriod.group().equals("мес") ? "месяц" : matcherPeriod.group();
        } else {
            throw new PeriodNotFoundException("Period hasn't been found on the cian.ru page");
        }
        System.out.println(period);
        return period;
    }

    @Override
    public BigDecimal getPrice(Element adTag) {
        Element adPriceContent = adTag.select("span[data-mark$=MainPrice]").first();

        Pattern pricePattern = Pattern.compile("^[\\d\\s]+\\s₽");
        Matcher matcherPrice = pricePattern.matcher(adPriceContent.text());
        BigDecimal price;
        if (matcherPrice.find()) {
            price = new BigDecimal(matcherPrice.group().substring(0, matcherPrice.group().lastIndexOf(" ")).replaceAll("\\s", ""));
        } else {
            throw new PriceNotFoundException("Price hasn't been found on the cian.ru page");
        }
        System.out.print(price + " в ");
        return price;
    }

    @Override
    public String getTitle(Element adTag) {

        Element adTitle = adTag.select("span[data-mark$=OfferTitle]").first();
        Element adSubtitle = adTag.select("span[data-mark$=OfferSubtitle]").first();

        String title = adTitle.text() + " " + (adSubtitle != null ? adSubtitle.text() : "");
        System.out.println(title);
        return title;
    }

    @Override
    public String getDescription(Element adTag) {
        Element adDescription = adTag.select("div[class~=\\S*description\\S*]").first();
        String description = adDescription.text();
        System.out.println(description);
        return description;
    }

    @Override
    public String getLink(Element adTag) {
        Element adLinkTag = adTag.selectFirst("div[data-name$=LinkArea]");
        String link = adLinkTag.selectFirst("a").attr("href");
        System.out.println(link);
        return link;
    }
}
