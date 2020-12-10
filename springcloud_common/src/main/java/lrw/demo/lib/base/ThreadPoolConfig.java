package lrw.demo.lib.base;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author lrw
 * @date 2020/2/26
 */
@SpringBootConfiguration
public class ThreadPoolConfig {

    @Bean
    @Primary
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("Self-Pool-");
        executor.setBeanName("threadPoolTaskExecutor");
        executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors() + 1);
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors() / 2);
        executor.setKeepAliveSeconds(100);
        executor.setQueueCapacity(64);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

}
