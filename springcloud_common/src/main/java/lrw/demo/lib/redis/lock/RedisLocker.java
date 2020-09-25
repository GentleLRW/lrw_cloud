package lrw.demo.lib.redis.lock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author by lrw
 * @Classname RedisLocker
 * @Description TODO
 * @Date 2020/8/31 15:07
 */
@Component
@Slf4j
public class RedisLocker {

    /**
     * 最多尝试 N 次获得锁
     */
    private static final int MAX_TRY_GET_LOCK_TIMES = 20;
    /**
     * 获得锁默认等待时间
     */
    private static final int DEFAULT_WAIT_TIME = 60000;
    private final RedisLockRegistry redisLockRegistry;

    @Autowired
    public RedisLocker(RedisLockRegistry redisLockRegistry) {
        this.redisLockRegistry = redisLockRegistry;
    }

    public Lock lock(String key) {
        Lock lock = redisLockRegistry.obtain(key);
        if (lock.tryLock()) {
            return lock;
        }
        return null;
    }

    public Lock lock(int maxWait, String key) {
        Lock lock = redisLockRegistry.obtain(key);
        try {
            boolean res = lock.tryLock(maxWait, TimeUnit.MILLISECONDS);
            if (!res) {
                return null;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.warn("Can not Lock: {} - {}", key, e.getMessage());
            return null;
        }
        return lock;
    }

    /**
     * 强制阻塞拿锁
     *
     * @param key
     * @return
     */
    public Lock forceLock(String key) {
        return forceLock(DEFAULT_WAIT_TIME, key);
    }

    public Lock forceLock(int maxWait, String key) {
        return forceLock(maxWait, key, MAX_TRY_GET_LOCK_TIMES);
    }

    public Lock forceLock(int maxWait, String key, int maxTryForce) {
        Lock lock = null;
        for (int i = 0; i < maxTryForce; i++) {
            lock = lock(maxWait, key);
            if (lock != null) {
                break;
            }
            log.warn("Try to get lock again {}, maxWait:{}, key: {}", i, maxWait, key);
        }
        return lock;
    }

    public void unlock(Lock lock) {
        if (lock != null) {
            try{
                lock.unlock();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

}
