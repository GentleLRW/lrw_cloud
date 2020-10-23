package lrw.demo.lib.redis.redission;

import lrw.demo.lib.redis.redission.annotion.DistributedLockHandler;
import org.redisson.Redisson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author by lrw
 * @Classname AutoRedissionConfiguration
 * @Description TODO
 * @Date 2020/9/24 18:22
 */
@SpringBootConfiguration
@ConditionalOnClass(value = Redisson.class)
@EnableConfigurationProperties({RedissonConfig.class})
public class RedissonAutoConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedissonAutoConfiguration.class);

    @Bean
    @ConditionalOnMissingBean
    public RedissonConnectManage redissonConnectManage(RedissonConfig redissonConfig){
        return new RedissonConnectManage(redissonConfig);
    }

    @Bean
    @ConditionalOnMissingBean
    public RedissonLock redissonLock(RedissonConnectManage redissonConnectManage) {
        RedissonLock redissonLock = new RedissonLock(redissonConnectManage);
        LOGGER.info("[RedissonLock]组装完毕");
        return redissonLock;
    }

    @Bean
    @ConditionalOnMissingBean
    public DistributedLockHandler distributedLockHandler( ) {
        return new DistributedLockHandler();
    }


}
