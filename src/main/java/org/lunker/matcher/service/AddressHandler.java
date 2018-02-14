package org.lunker.matcher.service;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.lunker.matcher.model.openapi.City;
import org.lunker.matcher.model.openapi.Gu;
import org.lunker.matcher.repository.openapi.CityRepository;
import org.lunker.matcher.repository.openapi.GuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by dongqlee on 2018. 2. 11..
 */
@Component
public class AddressHandler {

    private Logger logger= LoggerFactory.getLogger(AddressHandler.class);

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private GuRepository guRepository;

    private String endpoint = "http://openapi.epost.go.kr/postal/retrieveLotNumberAdressAreaCdService/retrieveLotNumberAdressAreaCdService";

    private String dataApiKey = "";

    private AddressHandler() {
        dataApiKey = System.getProperty("dataApiKey");
        System.out.println("dataApiKey: \n");
        System.out.println(dataApiKey);
    }

    public boolean load(){
        boolean result=true;

        read1stDepth();
        read2ndDepth();
        logger.info("Load address data api");
        return result;
    }

    public String read1stDepth(){
        try{
            String requestUrl = endpoint + "/getBorodCityList";

            CloseableHttpClient httpClient= HttpClients.createDefault();

            requestUrl += "?ServiceKey=" + dataApiKey;
            logger.info("data api url : \n " + requestUrl);

            HttpGet httpGet=new HttpGet(requestUrl);
            CloseableHttpResponse response=httpClient.execute(httpGet);
            HttpEntity httpEntity=response.getEntity();
            String strResponse=EntityUtils.toString(httpEntity);

            logger.info(strResponse);

            org.json.JSONObject xmlJSONObj = XML.toJSONObject(strResponse);
            JSONArray array=new JSONArray();

            array=xmlJSONObj.getJSONObject("BorodCityResponse").getJSONArray("borodCity");

            for(int idx=0; idx<array.length(); idx++){
                City city=City.serialize(array.getJSONObject(idx));
                cityRepository.save(city);
            }

            EntityUtils.consume(httpEntity);

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return "";
    }

    // 구
    public boolean read2ndDepth(){
        final String requestUrl=endpoint + "/getSiGunGuList" + "?ServiceKey=" + dataApiKey ;

        Stream<City> cityStream= StreamSupport.stream(cityRepository.findAll().spliterator(), false);

        cityStream.forEach((city) -> {

            try{
                String url=requestUrl + "&brtcCd="+ city.getName();
                String strResponse=HttpService.get(url,null);

                org.json.JSONObject xmlJSONObj = XML.toJSONObject(strResponse);
                System.out.println("str repsonse;:\n");
                System.out.println(xmlJSONObj.toString());

                JSONArray array=new JSONArray();

                array=xmlJSONObj.getJSONObject("SiGunGuListResponse").getJSONArray("siGunGuList");
                JSONObject item=null;
                Gu gu=null;
                List<Gu> guList=new ArrayList<>();

                for(int idx=0; idx<array.length(); idx++){
                    item=array.getJSONObject(idx);
                    gu=new Gu(city.getName(), item.getString("signguCd"));
//                    guRepository.save(gu);
                    guList.add(gu);
                }

                guRepository.saveAll(guList);
            }
            catch (IOException ioe){
                ioe.printStackTrace();
                logger.error(ioe.getMessage());
            }
        });

        return true;
    }
}
