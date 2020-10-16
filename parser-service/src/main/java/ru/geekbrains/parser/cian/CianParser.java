package ru.geekbrains.parser.cian;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import ru.geekbrains.entities.Ad;
import ru.geekbrains.entities.Address;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * type=4 - designation is undefined yet
 * </p>
 */

@Slf4j
@Component
public class CianParser {
    private Document document;
    private Map<Address, List<Ad>> addsByAddress;

    private final String MAIN_URL = "https://www.cian.ru/cat.php";
    private final String CURRENCY= "currency";
    private final String currencyValue = "2";
    private final String DEAL_TYPE = "deal_type";
    private String dealTypeValue;
    private final String ENGINE_VERSION = "engine_version";
    private final String engineVersionValue = "2";
    private final String MAX_PRICE = "maxPrice";
    private String maxPriceValue;
    private final String MIN_PRICE = "minPrice";
    private String minPriceValue;
    private final String OFFER_TYPE = "offer_type";
    private String offerTypeValue;
    private final String PAGE = "p";
    private String pageValue;
    private final String REGION_CODE = "region";
    private String regionCode;
    private final String ONLY_ROOM = "room0";
    private String onlyRoomValue;
    private final String ONE_ROOM_FLAT = "room1";
    private final String oneRoomFlatValue = "1";
    private final String TWO_ROOM_FLAT = "room2";
    private final String twoRoomFlatValue = "1";
    private final String THREE_ROOM_FLAT = "room3";
    private final String threeRoomFlatValue = "1";
    private final String TYPE = "type";
    private final String typeValue = "4";
    /**
     * Fills Map interface implementor with <code>Address</code> as a key and List<code></><Ad></code> as a value
     *
     * @param country   Name of a country where parser should find adds
     * @param city      Name of a city in which parser should find adds
     *
     */
    public void start (String country, String city) {
        if (!country.equals("Россия")) {
            throw new RuntimeException("You are searching adds in country " + country + " but we can find adds only in Russia");
        }

        dealTypeValue = "rent"; // на данном этапе сервис ищет только тип сделок - аренда
        offerTypeValue = "flat"; // на данном этапе наш сервис ищет только квартиры
        regionCode = "1"; // это заглушка (код москвы), пока нет решения как соответствовать кодам городов на Циан
        pageValue = "1"; // TODO определиться с определением последней страницы при переборе

        try {
            document = Jsoup.connect(MAIN_URL)
                    .data(DEAL_TYPE, dealTypeValue)
                    .data(ENGINE_VERSION, engineVersionValue)
                    .data(OFFER_TYPE, offerTypeValue)
                    .data(PAGE, pageValue)
                    .data(REGION_CODE, regionCode)
                    .data(ONE_ROOM_FLAT, oneRoomFlatValue)
                    .data(TWO_ROOM_FLAT, twoRoomFlatValue)
                    .data(THREE_ROOM_FLAT, threeRoomFlatValue)
                    .header("Accept-Encoding", "gzip, deflate")
                    .maxBodySize(0)
                    .timeout(0)
                    .userAgent("Mozilla/5.0 (X11; Fedora; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.75 Safari/537.36")
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements articleTags = document.getElementsByTag("article");
        System.out.println();
        System.out.println(articleTags.size());

        new Address();
    }

    public Map<Address, List<Ad>> getResults (String city) {

        return new HashMap<Address, List<Ad>>();
    }
}

