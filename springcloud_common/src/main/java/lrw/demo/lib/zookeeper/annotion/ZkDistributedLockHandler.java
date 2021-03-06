package lrw.demo.lib.zookeeper.annotion;


import lombok.extern.slf4j.Slf4j;
import lrw.demo.lib.annotion.LimitQueue;
import lrw.demo.lib.redis.redission.RedissonLock;
import lrw.demo.lib.zookeeper.lock.ZkConnectManager;
import lrw.demo.lib.zookeeper.lock.ZkDistrbuteLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * 分布式锁注解解析器
 * @author lrw
 */
@Aspect
@Component
@Slf4j
public class ZkDistributedLockHandler {

    @Autowired
    private ZkConnectManager zkConnectManager;

    @Pointcut(value = "@annotation(zkDistributedLock)")
    public void getPointCut(ZkDistributedLock zkDistributedLock){

    }

    @Around("getPointCut(zkDistributedLock)")
    public void around(ProceedingJoinPoint joinPoint, ZkDistributedLock zkDistributedLock) {
        log.info("[开始]执行Zookeeper环绕通知,获取Zookeeper分布式锁开始");
        //获取锁目录
        String childPath = zkDistributedLock.value();
        int sessionTimeOut = zkDistributedLock.sessionTimeOut();
        ZkDistrbuteLock zkLock = new ZkDistrbuteLock(zkConnectManager, childPath,sessionTimeOut);
        zkLock.getLock();
        try {
            log.info("获取Zookeeper分布式锁[成功]，加锁完成，开始执行业务逻辑..."+System.currentTimeMillis());
            joinPoint.proceed();
        } catch (Throwable throwable) {
            log.error("获取Zookeeper分布式锁[异常]，加锁失败", throwable);
            throwable.printStackTrace();
        } finally {
            //如果该线程还持有该锁，那么释放该锁。如果该线程不持有该锁，说明该线程的锁已到过期时间，自动释放锁
            zkLock.unLock();
        }
        log.info("释放Zookeeper分布式锁[成功]，解锁完成，结束业务逻辑...");
    }
}
