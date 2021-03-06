package lrw.demo.app.controller;

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
public class ZkLockPayController {

    @Autowired
    private PayServiceFeign payServiceFeign;

    @PostMapping("/zkLock/pay")
    public BaseResponse pay(PayRequest payRequest){
        return payServiceFeign.zkLockPayController(payRequest);
    }
}
