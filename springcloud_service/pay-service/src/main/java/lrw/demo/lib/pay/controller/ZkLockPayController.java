package lrw.demo.lib.pay.controller;

import lombok.extern.slf4j.Slf4j;
import lrw.demo.lib.common.BaseResponse;
import lrw.demo.lib.dto.payService.request.PayRequest;
import lrw.demo.lib.pay.service.PayService;
import lrw.demo.lib.redis.redission.annotion.DistributedLock;
import lrw.demo.lib.zookeeper.annotion.ZkDistributedLock;
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
@Slf4j
public class ZkLockPayController {

    @Autowired
    private PayService payService;

    @Value("${server.port}")
    private String port;

    @ZkDistributedLock(value = "/payLock")
    @PostMapping("/zkLock/pay")
    public BaseResponse zkLockPayController(@RequestBody PayRequest request){
        log.info("进入/zkLock/pay");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        payService.pay(request);
        return BaseResponse.success(port);
    }

}
