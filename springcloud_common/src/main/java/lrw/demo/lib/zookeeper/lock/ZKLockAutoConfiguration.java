package lrw.demo.lib.zookeeper.lock;

import lrw.demo.lib.redis.redission.RedissonAutoConfiguration;
import lrw.demo.lib.redis.redission.RedissonConfig;
import lrw.demo.lib.redis.redission.RedissonConnectManage;
import lrw.demo.lib.redis.redission.annotion.DistributedLockHandler;
import lrw.demo.lib.zookeeper.annotion.ZkDistributedLockHandler;
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
 * @Classname ZKAutoConfiguration
 * @Description TODO
 * @Date 2020/9/29 16:11
 */
@SpringBootConfiguration
@EnableConfigurationProperties({ZkLockConfigProperties.class})
public class ZKLockAutoConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZKLockAutoConfiguration.class);

    @Bean
    public ZkConnectManager redissonConnectManage(ZkLockConfigProperties zkLockConfigProperties){
        return new ZkConnectManager(zkLockConfigProperties);
    }

    @Bean
    public ZkDistributedLockHandler zkDistributedLockHandler(){
        return new ZkDistributedLockHandler();
    }

}
