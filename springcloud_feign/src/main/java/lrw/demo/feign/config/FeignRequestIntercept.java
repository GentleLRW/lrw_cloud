package lrw.demo.feign.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author by lrw
 * @Classname FeignRequestIntercept
 * @Description TODO
 * @Date 2020/9/9 17:57
 */
@Slf4j
@Component
public class FeignRequestIntercept implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        log.info("调用链路信息:Url{"+requestTemplate.url()+"}" +
                ",Method{"+requestTemplate.method()+"},参数{"+requestTemplate.body().toString()+"}");
    }
}
