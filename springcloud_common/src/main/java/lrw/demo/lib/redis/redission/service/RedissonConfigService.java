package lrw.demo.lib.redis.redission.service;


import lrw.demo.lib.redis.redission.RedissonConfig;
import org.redisson.config.Config;

/**
 * @author by lrw
 * @Classname RedissonConfigService
 * @Description TODO
 * @Date 2020/9/24 18:22
 */
public interface RedissonConfigService {

    /**
     * 根据不同的Redis配置策略创建对应的Config
     * @param redissonConfig
     * @return Config
     */
    Config createRedissonConfig(RedissonConfig redissonConfig);
}
