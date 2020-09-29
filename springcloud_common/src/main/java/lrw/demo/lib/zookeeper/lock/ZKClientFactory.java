package lrw.demo.lib.zookeeper.lock;

import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author by lrw
 * @Classname ZKClientFactory
 * @Description TODO
 * @Date 2020/9/29 17:30
 */
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


    public ZkClient getZkClient(String path){
        if(map.containsKey(path)){
            return map.get(path);
        }else{
            ZkClient zkClient = new ZkClient(path);
            map.put(path,zkClient);
            return zkClient;
        }
    }

}
