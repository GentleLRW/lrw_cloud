package lrw.demo.sharding.algorithm;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author by lrw
 * @Classname BaseComplexKeysShardingAlgorithm
 * @Description TODO 基础复杂的分片算法
 * @Date 2020/11/16 15:05
 */
@Slf4j
public class BaseComplexKeysShardingAlgorithm implements ComplexKeysShardingAlgorithm<Long> {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Long> complexKeysShardingValue) {
        log.info("availableTargetNames:"+ JSON.toJSONString(availableTargetNames)+"\n"+"complexKeysShardingValue:"+JSON.toJSONString(complexKeysShardingValue));
        return null;
    }
}
