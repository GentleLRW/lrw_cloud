package lrw.demo.lib.app.controller;

import lrw.demo.feign.PayServiceFeign;
import lrw.demo.lib.common.BaseResponse;
import lrw.demo.lib.dto.payService.request.PayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by lrw
 * @Classname AppPayController
 * @Description TODO
 * @Date 2020/9/3 19:38
 */
@RestController
public class RedissonPayController {

    @Autowired
    private PayServiceFeign payServiceFeign;

    @PostMapping("/redisson/pay")
    public BaseResponse pay(PayRequest payRequest){
        return payServiceFeign.redissonPayController(payRequest);
    }
}
