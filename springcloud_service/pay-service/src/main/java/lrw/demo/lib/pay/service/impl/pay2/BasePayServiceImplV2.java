package lrw.demo.lib.pay.service.impl.pay2;

import lombok.extern.slf4j.Slf4j;
import lrw.demo.lib.dto.payService.request.PayRequest;
import org.springframework.stereotype.Service;

/**
 * @author by lrw
 * @Classname AliPayServiceImpl
 * @Description TODO
 * @Date 2020/9/18 14:16
 */
@Service("basePayService")
@Slf4j
public class BasePayServiceImplV2 extends AbstractPayV2 {

    @Override
    public void pay(PayRequest request) {
//        log.info("远程调用支付宝支付:"+request.toString());
    }
}
