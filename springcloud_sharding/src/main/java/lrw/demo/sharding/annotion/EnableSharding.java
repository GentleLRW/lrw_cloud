package lrw.demo.sharding.annotion;


import lrw.demo.sharding.config.DataSourceConfig;
import lrw.demo.sharding.config.ShardingDataSourceProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author by lrw
 * @Classname EnableSharding
 * @Description TODO
 * @Date 2020/11/24 15:19
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({DataSourceConfig.class, ShardingDataSourceProperties.class})
public @interface EnableSharding {

}
