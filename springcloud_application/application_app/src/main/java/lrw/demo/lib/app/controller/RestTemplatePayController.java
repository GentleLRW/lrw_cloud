package lrw.demo.lib.app.controller;

import lrw.demo.lib.common.BaseResponse;
import lrw.demo.lib.dto.payService.request.PayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author by lrw
 * @Classname AppPayController
 * @Description TODO
 * @Date 2020/9/3 19:38
 */
@RestController
public class RestTemplatePayController {

    private final String uri = "http://pay-service/payService/pay";

    @Autowired(required = false)
    private RestTemplate restTemplate;


    @PostMapping("/restTemplate/pay")
    public BaseResponse pay(PayRequest payRequest){
        BaseResponse baseResponse = restTemplate
                .postForObject(uri, payRequest, BaseResponse.class);
        if(baseResponse.isSuccess()){
            return BaseResponse.success();
        }
        return BaseResponse.failure("失败");
    }
}
