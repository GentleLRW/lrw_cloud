package lrw.demo.app.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author by lrw
 * @Classname RestTemplateConfig
 * @Description TODO
 * @Date 2020/9/3 18:08
 */
@Configuration
public class RestTemplateConfig {

//    @Autowired
//    private RestTemplateBuilder builder;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
