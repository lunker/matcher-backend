package org.lunker.matcher.service;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by dongqlee on 2018. 2. 11..
 */
public class HttpService {

    private static Logger logger= LoggerFactory.getLogger(HttpService.class);

    public static String get(String url, NameValuePair[] nameValuePair) throws IOException {

        logger.info("Generate http GET for url : " + url);

        String strResponse="";
        CloseableHttpClient httpClient= HttpClients.createDefault();

        HttpGet httpGet=new HttpGet(url);
        CloseableHttpResponse response=httpClient.execute(httpGet);
        HttpEntity httpEntity=response.getEntity();

        String contentType=response.getFirstHeader("Content-Type").getValue();

        if(contentType.equals("application/xml")){
            logger.info("xml!");
             strResponse= EntityUtils.toString(httpEntity);
        }
        else {
            logger.info("json!");
            strResponse= EntityUtils.toString(httpEntity);
        }

        EntityUtils.consume(httpEntity);

        return strResponse;
    }

    public String post(){
        return "";
    }
}
