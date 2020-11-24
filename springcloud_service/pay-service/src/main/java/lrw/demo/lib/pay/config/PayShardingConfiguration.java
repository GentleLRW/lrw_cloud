package lrw.demo.lib.pay.config;

import lrw.demo.sharding.IShardingExpandTable;
import lrw.demo.sharding.IShardingRuleConfiguration;
import lrw.demo.sharding.algorithm.BaseComplexKeysShardingAlgorithm;
import lrw.demo.sharding.config.DataSourceProperties;
import lrw.demo.sharding.factory.ShardingConfigurationFactory;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.ComplexShardingStrategyConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author by lrw
 * @Classname PayShardingConfiguration
 * @Description TODO
 * @Date 2020/11/10 17:28
 */
@Component
public class PayShardingConfiguration implements IShardingRuleConfiguration, IShardingExpandTable {

    @Resource
    private DataSourceProperties dataSourceProperties;

    private static List<String> shardingTables;

    static{
        shardingTables = Arrays.asList("shop_order");
    }


    @Override
    public ShardingRuleConfiguration getShardingRuleConfiguration() {
        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
        TableRuleConfiguration rule = new TableRuleConfiguration("shop_order"
                , String.format(ShardingConfigurationFactory.DEFAULT_TIME_BASE_ACTUAL_NODE_FORMAT
                , dataSourceProperties.getDateSourceName(), "shop_order"
                , dataSourceProperties.getDateSourceName(), "shop_order"));
        rule.setTableShardingStrategyConfig(new ComplexShardingStrategyConfiguration("create_time"
                ,new BaseComplexKeysShardingAlgorithm()));
        shardingRuleConfiguration.getTableRuleConfigs().add(rule);
        return shardingRuleConfiguration;
    }

    @Override
    public List<String> getAllExpandTables() {
        return shardingTables;
    }
}
