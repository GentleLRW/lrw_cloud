package lrw.demo.feign;

import lrw.demo.lib.common.BaseResponse;
import lrw.demo.lib.dto.payService.request.PayRequest;
import lrw.demo.feign.fallback.PayServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author by lrw
 * @Classname PayServiceFeign
 * @Description TODO
 * @Date 2020/9/9 15:21
 */
@FeignClient(name = "pay-service",fallbackFactory = PayServiceFallbackFactory.class)
public interface PayServiceFeign {

    @PostMapping("/payService/pay")
    BaseResponse payController(@RequestBody PayRequest request);

    @PostMapping("/limiting/pay")
    BaseResponse limitingPayController(@RequestBody PayRequest request);
}
