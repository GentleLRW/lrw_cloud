package lrw.demo.lib.zookeeper.lock;

/**
 * @author by lrw
 * @Classname ZkBaseLock
 * @Description TODO
 * @Date 2020/9/29 15:15
 */
public interface ZkBaseLock {
    /**
     * 获取锁
     */
    void getLock();

    /**
     * 解锁
     */
    void unLock();
}
