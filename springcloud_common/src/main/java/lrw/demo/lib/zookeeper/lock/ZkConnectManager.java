package lrw.demo.lib.zookeeper.lock;

import lombok.Data;
import lrw.demo.lib.redis.redission.RedissonConnectManage;
import org.I0Itec.zkclient.ZkClient;

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

    public ZkClient getZKClient(String childPath){
        return getZkClientFactory().getZkClient(this.zkLockConfigProperties.getRootPath() + childPath);
    }
}
