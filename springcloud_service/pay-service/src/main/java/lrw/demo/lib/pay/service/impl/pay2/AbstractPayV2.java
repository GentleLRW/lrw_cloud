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
public abstract class AbstractPayV2 implements BasePayService {

    public abstract void pay(PayRequest request);

    @Override
    public void basePay(PayRequest request) {
        pay(request);
    }
}
