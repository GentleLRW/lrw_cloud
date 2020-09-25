package lrw.demo.lib.redis.redission.service.impl;


import lombok.extern.slf4j.Slf4j;
import lrw.demo.lib.redis.redission.GlobalConstant;
import lrw.demo.lib.redis.redission.RedissonConfig;
import lrw.demo.lib.redis.redission.service.RedissonConfigService;
import org.redisson.config.Config;
import org.springframework.util.StringUtils;

/**
 * @author by lrw
 * @Classname StandaloneConfigImpl
 * @Description TODO
 * @Date 2020/9/24 18:22
 * 单机部署 Redisson配置
 */
@Slf4j
public class StandaloneConfigImpl implements RedissonConfigService {

    @Override
    public Config createRedissonConfig(RedissonConfig redissonConfig) {
        Config config = new Config();
        try {
            String address = redissonConfig.getAddress();
            String password = redissonConfig.getPassword();
            String port = redissonConfig.getPort();
            int database = redissonConfig.getDatabase();
            String redisAddr = GlobalConstant.REDIS_CONNECTION_PREFIX.getConstant_value() + address+":"+port;
            config.useSingleServer().setAddress(redisAddr);
            config.useSingleServer().setDatabase(database);
            //密码可以为空
            if (!StringUtils.isEmpty(password)) {
                config.useSingleServer().setPassword(password);
            }
            log.info("初始化[单机部署]方式Config,redisAddress:" + address);
        } catch (Exception e) {
            log.error("单机部署 Redisson init error", e);
        }
        return config;
    }
}
