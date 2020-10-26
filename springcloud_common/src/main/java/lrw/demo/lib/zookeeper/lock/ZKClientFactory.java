package lrw.demo.lib.zookeeper.lock;

import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author by lrw
 * @Classname ZKClientFactory
 * @Description TODO
 * @Date 2020/9/29 17:30
 */
@Slf4j
public class ZKClientFactory {

    private ZKClientFactory() {
    }

    private static volatile ZKClientFactory factory = null;

    private static ConcurrentHashMap<String,ZkClient> map = new ConcurrentHashMap<>();

    public static ZKClientFactory getInstance() {
        if (factory == null) {
            synchronized (Object.class) {
                if (factory == null) {
                    factory = new ZKClientFactory();
                }
            }
        }
        return factory;
    }


    public ZkClient getZkClient(String host,String key){
        if(map.containsKey(key)){
            return map.get(key);
        }else{
            return createZkClient(host,key);
        }
    }

    public ZkClient createZkClient(String host,String key){
        return createZkClient(host,key,10000);
    }

    public ZkClient createZkClient(String host,String key,int sessionTimeOut){
        ZkClient zkClient = new ZkClient(host,sessionTimeOut,100000);
        map.put(key,zkClient);
        return zkClient;
    }

    public ZkClient releaseZkClient(String key){
        return map.remove(key);
    }


}
