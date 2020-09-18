package lrw.demo.lib.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author by lrw
 * @Classname AppApplication
 * @Description TODO
 * @Date 2020/9/1 19:18
 */
@SpringBootApplication
@EnableEurekaClient
public class AppApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }
}