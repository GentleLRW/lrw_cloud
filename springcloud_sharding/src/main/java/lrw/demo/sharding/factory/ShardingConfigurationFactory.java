package lrw.demo.sharding.factory;

import org.apache.commons.lang3.StringUtils;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;

import java.util.List;
import java.util.Properties;

/**
 * @author by lrw
 * @Classname ShardingConfigurationFactory
 * @Description TODO
 * @Date 2020/11/13 18:16
 */
public class ShardingConfigurationFactory  {

    public final static String SPILT = ",";
    /**
     * 默认的基于时间的 实际节点表 表达式
     * 1901~2012
     */
    public static final String DEFAULT_TIME_BASE_ACTUAL_NODE_FORMAT = "%s.%s_$->{19..21}0$->{1..9},%s.%s_$->{19..21}1$->{0..2}";


}
