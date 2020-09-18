package lrw.demo.lib.pay.controller;

import lrw.demo.lib.common.BaseResponse;
import lrw.demo.lib.dto.payService.request.PayRequest;
import lrw.demo.lib.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by lrw
 * @Classname PayController
 * @Description TODO
 * @Date 2020/9/3 16:58
 */
@RestController
public class PayController {

    @Autowired
    private PayService payService;

    @Value("${server.port}")
    private String port;


    @PostMapping("/payService/pay")
    public BaseResponse payController(@RequestBody PayRequest request){
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        payService.pay(request);
        return BaseResponse.success(port);
    }

}
