package lrw.demo.lib.pay.config;

import lrw.demo.sharding.IShardingExpandTable;
import lrw.demo.sharding.IShardingRuleConfiguration;
import lrw.demo.sharding.algorithm.BaseComplexKeysShardingAlgorithm;
import lrw.demo.sharding.config.ShardingDataSourceProperties;
import lrw.demo.sharding.factory.ShardingConfigurationFactory;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.ComplexShardingStrategyConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author by lrw
 * @Classname PayShardingConfiguration
 * @Description TODO
 * @Date 2020/11/10 17:28
 */
@Component
public class PayShardingConfiguration implements IShardingRuleConfiguration, IShardingExpandTable {

    @Autowired
    private ShardingDataSourceProperties shardingDataSourceProperties;

    private static List<String> shardingTables;

    static{
        shardingTables = Arrays.asList("shop_order");
    }


    @Override
    public ShardingRuleConfiguration getShardingRuleConfiguration() {
        ShardingRuleConfiguration shardingRuleConfiguration = new ShardingRuleConfiguration();
        TableRuleConfiguration rule = new TableRuleConfiguration("shop_order"
                , String.format(ShardingConfigurationFactory.DEFAULT_TIME_BASE_ACTUAL_NODE_FORMAT
                , shardingDataSourceProperties.getDateSourceName(), "shop_order"
                , shardingDataSourceProperties.getDateSourceName(), "shop_order"));
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
