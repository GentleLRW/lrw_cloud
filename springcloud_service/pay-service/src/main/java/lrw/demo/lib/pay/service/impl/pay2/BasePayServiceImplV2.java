package lrw.demo.lib.pay.service.impl.pay2;

import lombok.extern.slf4j.Slf4j;
import lrw.demo.lib.dto.eum.PayMannerEnum;
import lrw.demo.lib.dto.payService.request.PayRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author by lrw
 * @Classname AliPayServiceImpl
 * @Description TODO
 * @Date 2020/9/18 14:16
 */
@Service("basePayService")
@Slf4j
public class BasePayServiceImplV2 implements ApplicationListener<ContextRefreshedEvent>,BasePayService{

    private ConcurrentHashMap<PayMannerEnum, BasePayService> payMap = new ConcurrentHashMap<>();

    private ApplicationContext applicationContext;

    void registerPayBean(){
        payMap.put(PayMannerEnum.ALIPAY,(BasePayService) this.applicationContext.getBean("aliPayService"));
        payMap.put(PayMannerEnum.WECHAT,(BasePayService) this.applicationContext.getBean("weChatPayService"));
        payMap.put(PayMannerEnum.BANKCARD,(BasePayService) this.applicationContext.getBean("bankCardPayService"));
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.applicationContext = contextRefreshedEvent.getApplicationContext();
        registerPayBean();
    }

    @Override
    public void basePay(PayRequest request) {
        BasePayService basePayService = payMap.get(request.getPayManner());
        basePayService.basePay(request);
    }
}
