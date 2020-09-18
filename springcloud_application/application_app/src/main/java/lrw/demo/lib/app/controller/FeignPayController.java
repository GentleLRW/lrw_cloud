package lrw.demo.lib.app.controller;

import lrw.demo.lib.common.BaseResponse;
import lrw.demo.lib.dto.payService.request.PayRequest;
import lrw.demo.feign.PayServiceFeign;
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
public class FeignPayController {

    @Autowired
    private PayServiceFeign payServiceFeign;

    @PostMapping("/feign/pay")
    public BaseResponse pay(PayRequest payRequest){
        return payServiceFeign.payController(payRequest);
    }
}
