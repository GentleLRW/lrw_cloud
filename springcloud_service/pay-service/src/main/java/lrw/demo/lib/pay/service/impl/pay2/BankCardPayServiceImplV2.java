package lrw.demo.lib.pay.service.impl.pay2;

import lombok.extern.slf4j.Slf4j;
import lrw.demo.lib.dto.eum.PayMannerEnum;
import lrw.demo.lib.dto.payService.request.PayRequest;
import lrw.demo.lib.pay.service.impl.pay.PayAbstractServiceFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author by lrw
 * @Classname AliPayServiceImpl
 * @Description TODO
 * @Date 2020/9/18 14:16
 */
@Service("bankCardPayService")
@Slf4j
public class BankCardPayServiceImplV2 extends AbstractPayV2 {


    @Override
    public void pay(PayRequest request) {
        log.info("远程调用银行卡支付:"+request.toString());
    }
}
