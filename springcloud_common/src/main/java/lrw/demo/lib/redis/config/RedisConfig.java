package lrw.demo.lib.redis.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.integration.redis.util.RedisLockRegistry;

import javax.annotation.Resource;

/**
 * @author by lrw
 * @Classname RedisConfig
 * @Description TODO
 * @Date 2020/8/31 15:07
 */
@SpringBootConfiguration
public class RedisConfig {

    @Resource
    private RedisProperties redisProperties;

    @Bean
    public GenericObjectPoolConfig genericObjectPoolConfig() {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(redisProperties.getLettuce().getPool().getMaxIdle());
        poolConfig.setMaxTotal(redisProperties.getLettuce().getPool().getMaxActive());
        poolConfig.setMinIdle(redisProperties.getLettuce().getPool().getMinIdle());
        poolConfig.setMaxWaitMillis(redisProperties.getLettuce().getPool().getMaxWait().toMillis());
        return poolConfig;
    }

    /**
     * 初始化锁注册器
     *
     * @param lettuceConnectionFactory
     * @return lock register
     */
    @Bean
    public RedisLockRegistry redisLockRegistry(LettuceConnectionFactory lettuceConnectionFactory) {
        return new RedisLockRegistry(lettuceConnectionFactory, "f5-future-store-cloud", 30000);
    }

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory(GenericObjectPoolConfig poolConfig) {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setDatabase(redisProperties.getDatabase());
        configuration.setHostName(redisProperties.getHost());
        configuration.setPassword(RedisPassword.of(redisProperties.getPassword()));
        configuration.setPort(redisProperties.getPort());
        LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder()
                .commandTimeout(redisProperties.getTimeout())
                .poolConfig(poolConfig)
                .build();
        return new LettuceConnectionFactory(configuration, clientConfiguration);
    }

    @Bean
    public RedisTemplate redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        redisTemplate.setDefaultSerializer(new GenericFastJsonRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        return new StringRedisTemplate(lettuceConnectionFactory);
    }

}
