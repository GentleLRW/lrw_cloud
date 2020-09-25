package lrw.demo.lib.annotion;

import lrw.demo.lib.aspect.LimitAspect;
import lrw.demo.lib.redis.config.RedisConfig;
import lrw.demo.lib.redis.redission.RedissonAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author by lrw
 * @Classname LimitQueue
 * @Description TODO
 * @Date 2020/9/14 11:23
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({RedisConfig.class, RedissonAutoConfiguration.class})
public @interface EnableRedisConfig {

}
