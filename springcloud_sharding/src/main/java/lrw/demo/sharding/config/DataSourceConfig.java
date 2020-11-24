package lrw.demo.sharding.config;

import com.alibaba.druid.pool.DruidDataSource;
import lrw.demo.sharding.IShardingRuleConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by lrw
 * @Classname DataSourceConfig
 * @Description TODO
 * @Date 2020/11/10 17:11
 */
@SpringBootConfiguration
@EnableConfigurationProperties({DataSourceProperties.class})
public class DataSourceConfig {
    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Resource
    private IShardingRuleConfiguration shardingRuleConfiguration;



    @Bean
    @Primary
    public DataSource dataSource() throws SQLException {
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap()
                ,shardingRuleConfiguration.getShardingRuleConfiguration(),shardingRuleConfiguration.getProperties());
    }

    private Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> map = new HashMap<>();
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(dataSourceProperties.getUrl());
        druidDataSource.setPassword(dataSourceProperties.getPassword());
        druidDataSource.setUsername(dataSourceProperties.getUsername());
        druidDataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        map.put(dataSourceProperties.getDateSourceName(),druidDataSource);
        return map;
    }

}
