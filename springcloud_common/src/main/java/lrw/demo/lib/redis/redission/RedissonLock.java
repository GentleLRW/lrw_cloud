package lrw.demo.lib.redis.redission;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 针对源码 Redisson进行一层封装
 * @author Yuqiang
 */
@Slf4j
@Component
public class RedissonLock {

  private RedissonConnectManage redissonConnectManage;

  private Redisson redisson;


    public RedissonLock(RedissonConnectManage redissonManager) {
        this.redissonConnectManage = redissonManager;
        this.redisson = redissonManager.getRedisson();
    }

    public RedissonLock() {}

    /**
     * 加锁操作 （设置锁的有效时间）
     * @param lockName 锁名称
     * @param leaseTime  锁有效时间
     */
    public void lock(String lockName, long leaseTime) {
        RLock rLock = redisson.getLock(lockName);
        rLock.lock(leaseTime,TimeUnit.SECONDS);
    }

    /**
     * 加锁操作 (锁有效时间采用默认时间30秒）
     * @param lockName 锁名称
     */
    public void lock(String lockName) {
        RLock rLock = redisson.getLock(lockName);
        rLock.lock();
    }

    /**
     * 加锁操作(tryLock锁，没有等待时间）
     * @param lockName  锁名称
     * @param leaseTime 锁有效时间
     */
    public boolean tryLock(String lockName, long leaseTime) {

        RLock rLock = redisson.getLock(lockName);
        boolean getLock = false;
        try {
            getLock = rLock.tryLock( leaseTime, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("获取Redisson分布式锁[异常]，lockName=" + lockName, e);
            e.printStackTrace();
            return false;
        }
        return getLock;
    }

    /**
     * 加锁操作(tryLock锁，有等待时间）
     * @param lockName   锁名称
     * @param leaseTime  锁有效时间
     * @param waitTime   等待时间
     */
    public  boolean tryLock(String lockName, long leaseTime,long waitTime) {

        RLock rLock = redisson.getLock(lockName);
        boolean getLock = false;
        try {
            getLock = rLock.tryLock( waitTime,leaseTime, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("获取Redisson分布式锁[异常]，lockName=" + lockName, e);
            e.printStackTrace();
            return false;
        }
        return getLock;
    }

    /**
     * 解锁
     * @param lockName  锁名称
     */
    public void unlock(String lockName) {
        redisson.getLock(lockName).unlock();
    }

    /**
     * 判断该锁是否已经被线程持有
     * @param lockName  锁名称
     */
    public boolean isLock(String lockName) {
        RLock rLock = redisson.getLock(lockName);
        return rLock.isLocked();
    }


    /**
     * 判断该线程是否持有当前锁
     * @param lockName  锁名称
     */
    public boolean isHeldByCurrentThread(String lockName) {
        RLock rLock = redisson.getLock(lockName);
        return rLock.isHeldByCurrentThread();
    }

    public RedissonConnectManage getRedissonManager() {
        return redissonConnectManage;
    }

    public void setRedissonManager(RedissonConnectManage redissonManager) {
        this.redissonConnectManage = redissonManager;
    }
}
