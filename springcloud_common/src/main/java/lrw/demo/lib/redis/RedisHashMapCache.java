package lrw.demo.lib.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author by lrw
 * @Classname DivisionTimeRedisBlockQueue
 * @Description TODO
 * @Date 2020/8/31 15:07
 */
@Component
public class RedisHashMapCache {

//    private
//
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    public String get(String key){
//        return redisTemplate.opsForHash().get(key,);
//    }
//
//    public <T> void put(String key,T obj){
//        redisTemplate.opsForList().leftPush(key,JSON.toJSONString(obj));
//    }
//
//    public Integer size(String key){
//        return redisTemplate.opsForList().size(key).intValue();
//    }
//
//    public boolean exist(String key){
//        return redisTemplate.hasKey(key);
//    }

}
