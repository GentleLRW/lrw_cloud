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
@Service("weChatPayService")
@Slf4j
public class WeChatPayServiceImplV2 extends AbstractPayV2 {

    @Override
    public void pay(PayRequest request) {
        log.info("远程调用微信支付:"+request.toString());
    }
}
