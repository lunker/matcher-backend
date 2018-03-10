package org.lunker.matcher.util;

import com.google.gson.Gson;
import org.lunker.matcher_common.model.Constants;
import org.lunker.matcher_common.util.DistanceMatrix;
import org.lunker.matcher_common.util.ObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by dongqlee on 2018. 2. 14..
 */

public class Installation {
    private static Logger logger= LoggerFactory.getLogger(Installation.class);

    public static void install(){
        JedisPool jedisPool=new JedisPool("127.0.0.1", 6379);
        ObjectFactory objectFactory=ObjectFactory.getInstance();

        try {
            Jedis jedis=jedisPool.getResource();
            String deserializedStr=jedis.get(Constants.DISTANCE_KEY);

            Gson gson=new Gson();
            DistanceMatrix distanceMatrix=gson.fromJson(deserializedStr, DistanceMatrix.class);
            jedis.close();

            objectFactory.setObject(Constants.DISTANCE_KEY, distanceMatrix);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            // may throw JedisException
            jedisPool.destroy();
        }

        logger.debug("Install & Setup Matcher");
    }

}
