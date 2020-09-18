package lrw.demo.lib.aspect;

import com.alibaba.fastjson.JSONArray;
import lrw.demo.lib.annotion.LimitQueue;
import lrw.demo.lib.common.BaseErrorCode;
import lrw.demo.lib.common.BaseResponse;
import lrw.demo.lib.queue.RedisListQueue;
import lrw.demo.lib.redis.RedisUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;

/**
 * @author by lrw
 * @Classname LimitAspect
 * @Description TODO
 * @Date 2020/9/14 11:25
 */
@Aspect
@Component
public class LimitAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Pointcut(value = "@annotation(limitQueue)")
    public void getPointCut(LimitQueue limitQueue){

    }

    @Around(value = "getPointCut(limitQueue)")
    public Object limiting(ProceedingJoinPoint proceedingJoinPoint, LimitQueue limitQueue) throws Throwable {
        int limitCount = limitQueue.count();
        if(limitCount <= 0){
            return BaseResponse.failure(BaseErrorCode.LIMIT_FAILURE);
        }
        String limitQueueName = StringUtils.isEmpty(limitQueue.limitQueueName())
                ? proceedingJoinPoint.getSignature().getName() : limitQueue.limitQueueName();
        if (redisTemplate.hasKey(limitQueueName)) {
            Integer currentCount = Integer.valueOf(redisTemplate.opsForValue().get(limitQueueName));
            if (currentCount >= limitCount) {
                return BaseResponse.failure(BaseErrorCode.LIMIT_FAILURE);
            }
            redisTemplate.opsForValue().increment(limitQueueName,1L);
        }else{
            redisTemplate.opsForValue().set(limitQueueName,"1");
        }
        return proceedingJoinPoint.proceed();
    }


    @After(value = "getPointCut(limitQueue)")
    public void reduceLimitCount(JoinPoint joinPoint, LimitQueue limitQueue) throws Throwable {
        int limitCount = limitQueue.count();
        if(limitCount <= 0){
            return;
        }
        String limitQueueName = StringUtils.isEmpty(limitQueue.limitQueueName())
                ? joinPoint.getSignature().getName() : limitQueue.limitQueueName();
        if (redisTemplate.hasKey(limitQueueName)) {
            Integer currentCount = Integer.valueOf(redisTemplate.opsForValue().get(limitQueueName));
            if (currentCount <= 0) {
                return;
            }
            redisTemplate.opsForValue().set(limitQueueName,String.valueOf(currentCount-1));
        }
    }

}
