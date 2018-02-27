package org.lunker.matcher.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.lunker.matcher.util.FacebookUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by dongqlee on 2018. 2. 20..
 */
@Controller
@RequestMapping("/api/fb")
public class FacebookController {

    private Logger logger= LoggerFactory.getLogger(FacebookController.class);

    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    @ResponseBody
    public String callback(
            @RequestParam("hub.verify_token") String verifyToken,
            @RequestParam("hub.challenge") String challenge){
        logger.info("[GET] /api/fb/callback");

        logger.info("VerifyToken : " + verifyToken);
        logger.info("Challenge : " + challenge);
        return challenge;
    }

    @RequestMapping(value = "/callback", method = RequestMethod.POST)
    @ResponseBody
    public String callback(@RequestBody String webhookMessage){
        logger.info("[POST] /api/fb/callback");
        logger.info(webhookMessage);

        try{
            JsonObject jsonMessage=new JsonParser().parse(webhookMessage).getAsJsonObject();
            JsonArray entries=jsonMessage.getAsJsonArray("entry");

            for(int idx=0; idx<entries.size(); idx++){
                JsonObject entry=entries.get(idx).getAsJsonObject();
                JsonArray messaging=entry.getAsJsonArray("messaging");

                for(int messageIdx=0; messageIdx<messaging.size(); messageIdx++) {
                    JsonObject message=messaging.get(messageIdx).getAsJsonObject();

                    // testing
                    // echo
                    if(message.has("message")){
                        String targetId=message.get("sender").getAsJsonObject().get("id").getAsString();
                        String text="hello?";
                        new FacebookUtil().sendMessage(targetId, text);
                        logger.info("Send message " + text + "to " + targetId);
                    }



                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return "!";
    }



}
