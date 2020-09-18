package lrw.demo.lib.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author by lrw
 * @Classname RedisUtils
 * @Description TODO
 * @Date 2020/9/16 15:00
 */
@Component
@Slf4j
public class RedisUtils {

    private static final ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<>();

    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostConstruct
    public void init(){
        map.put("list",redisTemplate.opsForList());
        map.put("string",redisTemplate.opsForValue());
        map.put("hash",redisTemplate.opsForHash());
        map.put("set",redisTemplate.opsForSet());
        map.put("zset",redisTemplate.opsForZSet());
    }

    public ListOperations opsForList() {
        Object result = map.get("list");
        if(ObjectUtils.isEmpty(result)){
            return redisTemplate.opsForList();
        }
        return (ListOperations)result;
    }
    public ValueOperations opsForValue() {
        Object result = map.get("string");
        if(ObjectUtils.isEmpty(result)){
            return redisTemplate.opsForValue();
        }
        return (ValueOperations)result;
    }
    public HashOperations opsForHash() {
        Object result = map.get("hash");
        if(ObjectUtils.isEmpty(result)){
            return redisTemplate.opsForHash();
        }
        return (HashOperations)result;
    }
    public SetOperations opsForSet() {
        Object result = map.get("set");
        if(ObjectUtils.isEmpty(result)){
            return redisTemplate.opsForSet();
        }
        return (SetOperations)result;
    }
    public ZSetOperations opsForZSet() {
        Object result = map.get("zset");
        if(ObjectUtils.isEmpty(result)){
            return redisTemplate.opsForZSet();
        }
        return (ZSetOperations)result;
    }






}
