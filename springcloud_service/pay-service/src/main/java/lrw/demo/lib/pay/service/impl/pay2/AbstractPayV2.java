package lrw.demo.lib.pay.service.impl.pay2;

import lrw.demo.lib.dto.eum.PayMannerEnum;
import lrw.demo.lib.dto.payService.request.PayRequest;
import lrw.demo.lib.pay.service.impl.pay.PayAbstractServiceFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author by lrw
 * @Classname AbstractPayV2
 * @Description TODO
 * @Date 2020/10/23 15:14
 */
public abstract class AbstractPayV2 implements ApplicationListener<ContextRefreshedEvent>,BasePayService {

    private ConcurrentHashMap<PayMannerEnum, AbstractPayV2> payMap = new ConcurrentHashMap<>();

    private ApplicationContext applicationContext;

    void registerPayBean(){
        payMap.put(PayMannerEnum.ALIPAY,this.applicationContext.getBean("aliPayService",AbstractPayV2.class));
        payMap.put(PayMannerEnum.WECHAT,this.applicationContext.getBean("weChatPayService",AbstractPayV2.class));
        payMap.put(PayMannerEnum.BANKCARD,this.applicationContext.getBean("bankCardPayService",AbstractPayV2.class));
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.applicationContext = contextRefreshedEvent.getApplicationContext();
        registerPayBean();
    }

    public abstract void pay(PayRequest request);

    @Override
    public void basePay(PayRequest request) {
        AbstractPayV2 payFactory = payMap.get(request.getPayManner());
        payFactory.pay(request);
    }
}
