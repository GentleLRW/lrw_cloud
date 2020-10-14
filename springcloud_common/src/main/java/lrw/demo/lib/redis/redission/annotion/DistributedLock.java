package lrw.demo.lib.redis.redission.annotion;

import java.lang.annotation.*;


/**
 * 基于注解的分布式式锁
 * @author lrw
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DistributedLock {

    /**
     * 锁的名称
     */
    String value() default "redisson";

    /**
     * 锁的有效时间
     */
    int leaseTime() default 10;
}


