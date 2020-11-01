package ru.geekbrains.parser.cian;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.geekbrains.parser.cian.utils.CianRegionDefiner;
import ru.geekbrains.parser.cian.utils.DataExtractor;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

/**
 * Has methods to parse html from cian.ru
 * <p>
 * Full GET request URL to get Cian ads is: https://www.cian.ru/cat.php?currency=2&deal_type=rent&engine_version=2&maxprice=20000&minprice=10000&offer_type=flat&p=1&region=4581&room1=1&room2=1&room3=1&type=4
 * Where
 * https://www.cian.ru/cat.php - main URL;
 * Get parameters:
 * currency=2 - designation is undefined yet
 * deal_type=rent - type of the deal rent or sale
 * engine_version=2 - designation is undefined yet
 * maxprice=
 * minprice=
 * offer_type=flat - subject of a search may be flat or suburban for house searching
 * p= - page number
 * region= - region number
 * room0= - room search, value 1 if checkbox is marked
 * room1= - 1room flat, value 1 if checkbox is marked
 * room2= - 2room flat, value 1 if checkbox is marked
 * room3= - 4room flat, value 1 if checkbox is marked
 * type=4 - has a impact on results (change some link which logic related to
 * </p>
 */

@Slf4j
@Component
public class CianParser {

    private final DataExtractor dataExtractor;
    private final CianRegionDefiner cianRegionDefiner;
    private Map<Address, List<Ad>> addressAdMap;
    private boolean isProcessing = false;

    @Autowired
    public CianParser(DataExtractor dataExtractor, CianRegionDefiner cianRegionDefiner) {
        this.dataExtractor = dataExtractor;
        this.cianRegionDefiner = cianRegionDefiner;
    }

    /**
     * Fills Map interface implementor with <code>Address</code> as a key and List<code></><Ad></code> as a value
     *
     * @param country Name of a country where parser should find adTags
     * @param city    Name of a city in which parser should find adTags
     */
    public void start(String country, String city) {
        isProcessing = true;
        if (!country.equals("Россия")) {
            throw new RuntimeException("You are searching adTags in the country " + country + " but we can find ads only in Russia");
        }

        List<String> regionCodes = cianRegionDefiner.getRegions(city);
        String pageValue = "1";
        boolean hasNextPage = true;
        List<Ad> adsFromParse = new ArrayList<>();
        for (String regionCode : regionCodes) {
            while (!pageValue.equals("3")) { // uncomment to limit search deep to 2 pages. Needed to prevent blocking by IP
//        while (hasNextPage) {  // uncomment to search throughout all the target pages

                URIBuilder uri = new URIBuilder();
                uri.setScheme("https")
                        .setHost("www.cian.ru")
                        .setPath("/cat.php")
                        .addParameter("engine_version", "2")
                        .addParameter("deal_type", "rent")
                        .addParameter("offer_type", "flat")
                        .addParameter("region", regionCode)
                        .addParameter("p", pageValue);

                WebDriver driver = new HtmlUnitDriver();
                driver.get(uri.toString());

                Document document = Jsoup.parse(driver.getPageSource());

                Elements adsTags = (document.getElementsByTag("article").size() > 0) ? document.getElementsByTag("article") : document.getElementsByClass("div[class~=\\S*--card--\\S*]");

                System.out.println(adsTags.size());

                for (Element adTag : adsTags) {
                    Ad newAd = new Ad();
                    newAd.setAddress(dataExtractor.getAddress(adTag));
                    if(!newAd.getAddress().getCity().getName().equals(city)) continue;
                    newAd.setQuantity(dataExtractor.getQuantity(adTag));
                    newAd.setQuadrature(dataExtractor.getQuadrature(adTag));
                    newAd.setPeriod(dataExtractor.getPeriod(adTag));
                    newAd.setPrice(dataExtractor.getPrice(adTag));
                    newAd.setDescription(dataExtractor.getDescription(adTag));
                    newAd.setTitle(dataExtractor.getTitle(adTag));
                    newAd.setLink(dataExtractor.getLink(adTag));
                    adsFromParse.add(newAd);

                }
                String pageValueMark = document.selectFirst("div[data-name~=^Pagination]").getElementsByTag("li").last().children().first().text();
                if (!pageValueMark.equals("..")) {
                    hasNextPage = false;
                }

                pageValue = String.valueOf(Integer.parseInt(pageValue) + 1);

            }

        }
        addressAdMap = adsFromParse.stream().collect(groupingBy(Ad::getAddress));
        isProcessing = false;
    }

    public Map<Address, List<Ad>> getResults(String city) {

        return addressAdMap;
    }

    public boolean getProcessingStatus() {
        return isProcessing;
    }

    public void setProcessing(boolean processing) {
        isProcessing = processing;
    }
}

