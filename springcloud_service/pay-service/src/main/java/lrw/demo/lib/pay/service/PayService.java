package lrw.demo.lib.pay.service;

import lrw.demo.lib.common.BaseResponse;
import lrw.demo.lib.dto.payService.request.PayRequest;

/**
 * @author by lrw
 * @Classname PayService
 * @Description TODO
 * @Date 2020/9/2 16:17
 */
public interface PayService {
    BaseResponse pay(PayRequest request);
}
