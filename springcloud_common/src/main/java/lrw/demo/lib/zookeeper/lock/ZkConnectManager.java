package lrw.demo.lib.zookeeper.lock;

import lombok.Data;
import lrw.demo.lib.redis.redission.RedissonConnectManage;
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author by lrw
 * @Classname ZkConnectManager
 * @Description TODO
 * @Date 2020/9/29 16:53
 */
@Data
public class ZkConnectManager {

    private ZkLockConfigProperties zkLockConfigProperties;

    public ZkConnectManager(ZkLockConfigProperties zkLockConfigProperties){
        this.zkLockConfigProperties = zkLockConfigProperties;
    }

    private ZKClientFactory getZkClientFactory(){
        return ZKClientFactory.getInstance();
    }

    public ZkClient getZKClient(String key){
        if(StringUtils.isEmpty(key)){
            throw new RuntimeException("key值不能为空");
        }
        return getZkClientFactory().getZkClient(this.zkLockConfigProperties.getConnectString(),key);
    }

    public ZkClient releaseZkClient(String key){
        if(StringUtils.isEmpty(key)){
            throw new RuntimeException("key值不能为空");
        }
        return getZkClientFactory().releaseZkClient(key);
    }

    public ZkClient createZkClient(String key,int sessionTimeOut){
        if(StringUtils.isEmpty(key)){
            throw new RuntimeException("key值不能为空");
        }
        return getZkClientFactory().createZkClient(this.zkLockConfigProperties.getConnectString(),key,sessionTimeOut);
    }
}
