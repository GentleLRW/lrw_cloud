package lrw.demo.lib.zookeeper.lock;

import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.CountDownLatch;

/**
 * @author by lrw
 * @Classname ZkLock
 * @Description TODO
 * @Date 2020/9/29 17:40
 */
@Slf4j
public abstract class ZkAbstractLock implements ZkBaseLock {


    // 通过定义计数器标识创建临时节点状态
    protected CountDownLatch countDownLatch = new CountDownLatch(1);


    @Override
    public void getLock() {
        if (tryLock()) {
            log.info("获取lock锁的资源");
        } else {
            // 等待
            waitLock();
            // 重新获取锁资源
            getLock();
        }
    }

    @Override
    public void unLock() {
        if (getZkClient() != null) {
            releaseZkClient().close();
            log.info("释放lock锁资源");
        }
    }



    protected abstract void waitLock();

    protected abstract boolean tryLock();

    protected abstract ZkClient getZkClient();

    protected abstract ZkClient releaseZkClient();


}
