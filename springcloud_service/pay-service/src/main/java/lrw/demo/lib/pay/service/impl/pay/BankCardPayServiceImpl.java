package lrw.demo.lib.pay.service.impl.pay;

import lombok.extern.slf4j.Slf4j;
import lrw.demo.lib.dto.eum.PayMannerEnum;
import lrw.demo.lib.dto.payService.request.PayRequest;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author by lrw
 * @Classname AliPayServiceImpl
 * @Description TODO
 * @Date 2020/9/18 14:16
 */
@Service
@Slf4j
public class BankCardPayServiceImpl implements PayAbstractServiceFactory {
    @PostConstruct
    @Override
    public void register() {
        payMap.put(PayMannerEnum.BANKCARD,this);
    }


    @Override
    public void pay(PayRequest request) {
        log.info("远程调用银行卡支付:"+request.toString());
    }
}
