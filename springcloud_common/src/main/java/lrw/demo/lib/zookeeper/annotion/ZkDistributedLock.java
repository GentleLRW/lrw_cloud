package lrw.demo.lib.zookeeper.annotion;

import java.lang.annotation.*;


/**
 * 基于注解的分布式式锁
 * @author lrw
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ZkDistributedLock {

    /**
     * 锁目录
     */
    String value() default "/baseLock";

}


