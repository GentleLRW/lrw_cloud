package lrw.demo.lib.pay.service.impl.pay;


import lrw.demo.lib.dto.eum.PayMannerEnum;
import lrw.demo.lib.dto.payService.request.PayRequest;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author by lrw
 * @Classname PayAbstractService
 * @Description TODO
 * @Date 2020/9/2 16:18
 */
public interface PayAbstractServiceFactory {

    public static final ConcurrentHashMap<PayMannerEnum,PayAbstractServiceFactory> payMap = new ConcurrentHashMap<>();

    void register();

    void pay(PayRequest request);

}
