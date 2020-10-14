package ru.geekbrains.parser.cian;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import ru.geekbrains.parser.cian.temp.Ad;
import ru.geekbrains.parser.cian.temp.Address;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Parses cian.ru bu request
 * <p>
 * Where
 * https://www.cian.ru/cat.php - main URL;
 * Get parameters:
 * currency=2 - designation is undefined yet
 * dealType=rent - type of the deal rent or sale
 * engineVersion=2 - designation is undefined yet
 * maxprice=20000
 * minprice=10000
 * offerType=flat - subject of a search may be flat or suburban for house searching
 * p=1 - page number
 * region=4581 - region number
 * room0=1 - room search checkbox is marked
 * room1=1 - 1room flat checkbox is marked
 * room2=1 - 2room flat checkbox is marked
 * room3=1 - 4room flat checkbox is marked
 * type=4 - designation is undefined yet
 * </p>
 */
@Component
public class CianParser {
    private Document document;
    private Map<Address, List<Ad>> addsByAddress;
    private String currency = "2";
    private String dealType = "rent";
    private String engineVersion = "2";
    private String maxPrice = "20000";
    private String minPrice = "10000";
    private String offerType ="flat";
    private String pageNumber = "1";
    private String regionCode = "4581";
    private String room = "1";
    private String oneRoomFlat = "1";
    private String twoRoomFlat = "1";
    private String threeRoomFlat = "1";
    private String type = "4";
    /**
     * Fills Map interface implementor with <code>Address</code> as a key and List<code></><Ad></code> as a value
     * <p>
     * Full GET request URL to get Cian ads is: https://www.cian.ru/cat.php?currency=2&deal_type=rent&engine_version=2&maxprice=20000&minprice=10000&offer_type=flat&p=1&region=4581&room1=1&room2=1&room3=1&type=4
     */
    public void start (String country, String city) {

        try {
            document = Jsoup.connect(" https://www.cian.ru/cat.php?" +
                    "currency=" + currency +
                    "&deal_type=rent" + dealType +
                    "&engine_version=" + engineVersion +
                    "&offer_type=" + offerType +
                    "&p=" + pageNumber +
                    "&region=" + regionCode +
                    "&room1=" + oneRoomFlat +
                    "&room2=1" + twoRoomFlat +
                    "&room3=1" + threeRoomFlat +
                    "&type=4").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements articleTags = document.getElementsByTag("article");

        System.out.println(document.title());
        System.out.println(articleTags.size());


    }

    public Map<Address, List<Ad>> getResults (String city) {

        return new HashMap<Address, List<Ad>>();
    }
}

