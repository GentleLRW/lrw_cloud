package lrw.demo.lib.annotion;

import org.springframework.core.annotation.AliasFor;

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
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LimitQueue {

    int count() default 10;

    String limitQueueName() default "";
}
