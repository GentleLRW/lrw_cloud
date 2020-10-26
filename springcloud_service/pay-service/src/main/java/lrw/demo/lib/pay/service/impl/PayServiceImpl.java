package lrw.demo.lib.pay.service.impl;

import lombok.extern.slf4j.Slf4j;
import lrw.demo.lib.common.BaseErrorCode;
import lrw.demo.lib.common.BaseResponse;
import lrw.demo.lib.common.ErrorCode;
import lrw.demo.lib.dto.payService.request.PayRequest;
import lrw.demo.lib.pay.service.PayService;
import lrw.demo.lib.pay.service.impl.pay.PayAbstractServiceFactory;
import lrw.demo.lib.pay.service.impl.pay2.AbstractPayV2;
import lrw.demo.lib.pay.service.impl.pay2.BasePayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author by lrw
 * @Classname PayServiceImpl
 * @Description TODO
 * @Date 2020/9/18 14:23
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    @Resource(name = "basePayService")
    private BasePayService basePayService;

    @Override
    public BaseResponse pay(PayRequest request) {
        basePayService.basePay(request);

        //第一版支付实现
//        PayAbstractServiceFactory factory = PayAbstractServiceFactory.payMap.get(request.getPayManner());
//        if(factory == null){
//            return BaseResponse.failure(BaseErrorCode.PAY_TYPE_FAILURE);
//        }
//        factory.pay(request);
        return BaseResponse.success();
    }
}
