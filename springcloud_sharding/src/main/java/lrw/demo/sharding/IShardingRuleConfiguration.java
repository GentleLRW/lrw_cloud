package lrw.demo.sharding;

import lrw.demo.sharding.factory.ShardingPropertiesBuilder;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;

import java.util.List;
import java.util.Properties;

/**
 * @author by lrw
 * @Classname IShardingRuleConfiguration
 * @Description TODO
 * @Date 2020/11/10 17:21
 */
public interface IShardingRuleConfiguration {

    /**
     * 获取分片规则
     * @return
     */
    ShardingRuleConfiguration getShardingRuleConfiguration();

    /**
     * 额外参数配置
     *
     * @return
     */
    default Properties getProperties() {
        return ShardingPropertiesBuilder.builder()
                .build();
    }

}
