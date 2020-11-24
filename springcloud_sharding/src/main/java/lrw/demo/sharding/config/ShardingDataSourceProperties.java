package lrw.demo.sharding.config;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author by lrw
 * @Classname DataSourceProperties
 * @Description TODO
 * @Date 2020/11/10 17:06
 */
@Data
@SpringBootConfiguration
@ConfigurationProperties(prefix = "spring.sharding-jdbc")
public class ShardingDataSourceProperties {
    private String dateSourceName;

    private String driverClassName;

    private String url;

    private String username;

    private String password;
}
