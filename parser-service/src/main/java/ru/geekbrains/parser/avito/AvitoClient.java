package ru.geekbrains.parser.avito;

import lombok.extern.java.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log
@Service
public class AvitoClient {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    @Value("${parsers.avito.baseUrl:https://www.avito.ru}")
    private String baseUrl;

    @Value("${parsers.avito.cityFindUrl:https://www.avito.ru/web/1/slocations}")
    private String cityFindUrl;

    @Value("${parsers.avito.cityInfoUrl:https://www.avito.ru/js/catalog}")
    private String cityInfoUrl;

    @Value("${parsers.avito.apartmentCategoryUrlPath:/kvartiry/prodam-ASgBAgICAUSSA8YQ}")
    private String apartmentCategoryUrlPath;

    public String findCity(String city) {

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("limit", "10"));
        params.add(new BasicNameValuePair("q", city));

        return sendGet(this.cityFindUrl, params);
    }

    public String cityInfo(Integer cityId) {

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("locationId", cityId.toString()));

        return sendGet(this.cityInfoUrl, params);
    }

    public String apartmentList(String cityPrefix, Integer sort, Integer page) {

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("s", sort.toString()));
        params.add(new BasicNameValuePair("p", page.toString()));

        return sendGet(baseUrl + cityPrefix + apartmentCategoryUrlPath, params);
    }

    private String sendGet(String url, List<NameValuePair> getParameters) {

        try {


            URIBuilder builder = new URIBuilder(url);
            for (NameValuePair param : getParameters) {
                builder.setParameter(param.getName(), param.getValue());
            }

            HttpGet request = new HttpGet(builder.build());
            this.prepareHeaders(request);

            return this.request(request);
        } catch (Exception e) {
            log.severe(e.getMessage());
            return null;
        }
    }

    private String sendPost(String url, List<NameValuePair> postParameters) {
        try {
            HttpPost request = new HttpPost(url);
            request.setEntity(new UrlEncodedFormEntity(postParameters));

            return this.request(request);
        } catch (Exception e) {
            log.severe(e.getMessage());
            return null;
        }
    }

    private void prepareHeaders(HttpUriRequest request) {

        //TODO сделать генерацию user agent
        request.addHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.75 Safari/537.36");
    }

    private String request(HttpUriRequest request) {

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            HttpEntity entity = response.getEntity();

            if (entity != null) {
                return EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            log.severe(e.getMessage());
        }

        return null;
    }
}
