package lrw.demo.lib.zookeeper.lock;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * @author by lrw
 * @Classname ZkDistrbuteLock
 * @Description TODO
 * @Date 2020/9/29 17:46
 */
@Slf4j
public class ZkDistrbuteLock extends ZkAbstractLock {

    private ZkConnectManager zkConnectManager;
    private String childPath;
    private String key;
    private int sessionTimeOut;

    public ZkDistrbuteLock(ZkConnectManager zkConnectManager,String childPath,int sessionTimeOut){
        this.zkConnectManager = zkConnectManager;
        this.childPath = childPath;
        this.sessionTimeOut = sessionTimeOut;
        this.key = UUID.randomUUID().toString();
    }



    @Override
    protected void waitLock() {
        IZkDataListener iZkDataListener = new IZkDataListener() {
            /**
             * 节点发生改变时事件通知
             * @param dataPath
             * @param data
             * @throws Exception
             */
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {

            }

            /**
             * 节点删除是事件通知
             * @param dataPath
             * @throws Exception
             */
            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                log.info("删除的节点路径为" + dataPath);
                // 唤醒等待的线程
                if (countDownLatch != null) {
                    countDownLatch.countDown();
                }
            }
        };

        //注册监听事件
        getZkClient().subscribeDataChanges(getPath(), iZkDataListener);

        if (getZkClient().exists(getPath())) {
            countDownLatch = new CountDownLatch(1);
            try {
                countDownLatch.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //删除监听事件
        getZkClient().unsubscribeDataChanges(getPath(), iZkDataListener);
    }

    @Override
    protected boolean tryLock() {
        try {
            this.zkConnectManager.createZkClient(this.key,this.sessionTimeOut).createEphemeral(getPath());
            log.info("获取lock锁成功");
            return true;
        } catch (Exception e) {
            log.error("获取lock锁失败");
            return false;
        }
    }

    @Override
    protected ZkClient getZkClient() {
        return this.zkConnectManager.getZKClient(this.key);
    }

    @Override
    protected ZkClient releaseZkClient() {
        return this.zkConnectManager.releaseZkClient(this.key);
    }

    private String getPath(){
        return this.childPath;
    }

}
