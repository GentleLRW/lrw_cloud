package lrw.demo.lib.annotion;

import lrw.demo.lib.zookeeper.lock.ZKLockAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author by lrw
 * @Classname EnableZkLock
 * @Description TODO
 * @Date 2020/9/29 18:00
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({ZKLockAutoConfiguration.class})
public @interface EnableZkLockConfig {
}
