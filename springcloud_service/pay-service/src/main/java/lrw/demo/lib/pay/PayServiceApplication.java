package lrw.demo.lib.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author by lrw
 * @Classname PayServiceApplication
 * @Description TODO
 * @Date 2020/9/1 11:14
 */
@SpringBootApplication
@EnableEurekaClient
public class PayServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayServiceApplication.class, args);
    }
}
