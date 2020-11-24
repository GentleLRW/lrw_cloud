package lrw.demo.lib.pay.config;

import lrw.demo.lib.annotion.EnableLimitQueue;
import lrw.demo.lib.annotion.EnableRedisConfig;
import lrw.demo.lib.annotion.EnableZkLockConfig;
import lrw.demo.sharding.annotion.EnableSharding;
import org.springframework.boot.SpringBootConfiguration;

/**
 * @author by lrw
 * @Classname BaseConfig
 * @Description TODO
 * @Date 2020/9/16 17:54
 */
@SpringBootConfiguration
@EnableLimitQueue
//@EnableRedisConfig
@EnableZkLockConfig
@EnableSharding
public class BaseConfig {
}
