package lrw.demo.feign.config;

import feign.Logger;
import lrw.demo.feign.fallback.PayServiceFallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author by lrw
 * @Classname FeignConfig
 * @Description TODO
 * @Date 2020/9/9 11:44
 */
@SpringBootConfiguration
@EnableFeignClients(basePackages = {"lrw.demo.feign"},defaultConfiguration = FeignRequestIntercept.class)
//@ComponentScan(basePackages = {"lrw.demo.feign"})
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.BASIC;
    }

    @Bean
    PayServiceFallbackFactory payServiceFallbackFactory(){
        return new PayServiceFallbackFactory();
    }

}
