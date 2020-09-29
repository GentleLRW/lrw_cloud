package lrw.demo.lib.zookeeper.lock;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

/**
 * @author by lrw
 * @Classname ZkDistrbuteLock
 * @Description TODO
 * @Date 2020/9/29 17:46
 */

public class ZkDistrbuteLock extends ZkAbstractLock {

    private ZkConnectManager zkConnectManager;
    private String childPath;

    public ZkDistrbuteLock(ZkConnectManager zkConnectManager,String childPath){
        this.zkConnectManager = zkConnectManager;
        this.childPath = childPath;
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
                System.out.println("删除的节点路径为" + dataPath);
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
            getZkClient().createEphemeral(getPath());
            System.out.println("获取lock锁成功");
            return true;
        } catch (Exception e) {
            System.out.println("获取lock锁失败");
            return false;
        }
    }

    @Override
    protected ZkClient getZkClient() {
        return this.zkConnectManager.getZKClient(getPath());
    }

    private String getPath(){
        return this.zkConnectManager.getZkLockConfigProperties().getRootPath()+this.childPath;
    }

}
