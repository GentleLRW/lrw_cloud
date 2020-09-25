package lrw.demo.lib.redis.redission;

import lombok.extern.slf4j.Slf4j;
import lrw.demo.lib.redis.redission.service.RedissonConfigService;
import lrw.demo.lib.redis.redission.service.impl.ClusterConfigImpl;
import lrw.demo.lib.redis.redission.service.impl.MasterslaveConfigImpl;
import lrw.demo.lib.redis.redission.service.impl.SentineConfigImpl;
import lrw.demo.lib.redis.redission.service.impl.StandaloneConfigImpl;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author by lrw
 * @Classname RedissonConnectManage
 * @Description TODO
 * @Date 2020/9/24 18:29
 */
@Component
@Slf4j
public class RedissonConnectManage {

    private Config config = new Config();

    private Redisson redisson = null;

    public RedissonConnectManage() {
    }

    public RedissonConnectManage(RedissonConfig redissonConfig) {
        try {
            //通过不同部署方式获得不同cofig实体
            config = RedissonConfigFactory.getInstance().createConfig(redissonConfig);
            redisson = (Redisson) Redisson.create(config);
        } catch (Exception e) {
            log.error("Redisson init error", e);
            throw new IllegalArgumentException("please input correct configurations," +
                    "connectionType must in standalone/sentinel/cluster/masterslave");
        }
    }

    public Redisson getRedisson() {
        return redisson;
    }

    /**
     * Redisson连接方式配置工厂
     * 双重检查锁
     */
    static class RedissonConfigFactory {

        private RedissonConfigFactory() {
        }

        private static volatile RedissonConfigFactory factory = null;

        public static RedissonConfigFactory getInstance() {
            if (factory == null) {
                synchronized (Object.class) {
                    if (factory == null) {
                        factory = new RedissonConfigFactory();
                    }
                }
            }
            return factory;
        }


        /**
         * 根据连接类型获取对应连接方式的配置,基于策略模式
         *
         * @param redissonConfig redis连接信息
         * @return Config
         */
        Config createConfig(RedissonConfig redissonConfig) {
            Assert.notNull(redissonConfig);
            Assert.notNull(redissonConfig.getAddress(), "redisson.lock.server.address cannot be NULL!");
            Assert.notNull(redissonConfig.getType(), "redisson.lock.server.password cannot be NULL");
            Assert.notNull(redissonConfig.getDatabase(), "redisson.lock.server.database cannot be NULL");
            String connectionType = redissonConfig.getType();
            //声明配置上下文
            RedissonConfigService redissonConfigService = null;
            if (connectionType.equals(RedisConnectionType.STANDALONE.getConnection_type())) {
                redissonConfigService = new StandaloneConfigImpl();
            } else if (connectionType.equals(RedisConnectionType.SENTINEL.getConnection_type())) {
                redissonConfigService = new SentineConfigImpl();
            } else if (connectionType.equals(RedisConnectionType.CLUSTER.getConnection_type())) {
                redissonConfigService = new ClusterConfigImpl();
            } else if (connectionType.equals(RedisConnectionType.MASTERSLAVE.getConnection_type())) {
                redissonConfigService = new MasterslaveConfigImpl();
            } else {
                throw new IllegalArgumentException("创建Redisson连接Config失败！当前连接方式:" + connectionType);
            }
            return redissonConfigService.createRedissonConfig(redissonConfig);
        }
    }
}
