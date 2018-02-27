package org.lunker.matcher.util;

import com.google.gson.JsonObject;
import org.lunker.matcher.service.HttpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by dongqlee on 2018. 2. 21..
 */
public class FacebookUtil {

    private Logger logger= LoggerFactory.getLogger(FacebookUtil.class);
    private String endpoint="https://graph.facebook.com/v2.6/me/messages?access_token=";
    private String pageToken="";

    public FacebookUtil() {
        pageToken=System.getProperty("pageToken");
    }

    public void sendMessage(String receipientId, String text){

        endpoint += pageToken;

        JsonObject message=new JsonObject();
        JsonObject receipent=new JsonObject();
        JsonObject data=new JsonObject();

        message.addProperty("text", text);
        receipent.addProperty("id", receipientId);

        data.addProperty("messaging_type", "");
        data.add("recipient", receipent);
        data.add("message", message);

        try{
            String response=HttpService.post(endpoint, data);
            logger.info("SendMessage Result: " + response);
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
