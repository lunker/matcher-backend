package org.lunker.matcher.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

/**
 * Created by dongqlee on 2018. 2. 7..
 */
public class GsonHelper {

    private static Gson gson=null;

    public GsonHelper() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder = gsonBuilder.setPrettyPrinting();   //Sets pretty formatting
        gson = gsonBuilder.create();
    }



    public static String formattedString(JsonElement jsonElement){

        return "";
    }


}
