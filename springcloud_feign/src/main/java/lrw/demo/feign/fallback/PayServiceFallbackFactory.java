package lrw.demo.feign.fallback;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import lrw.demo.lib.common.BaseResponse;
import lrw.demo.lib.dto.payService.request.PayRequest;
import lrw.demo.feign.PayServiceFeign;

/**
 * @author by lrw
 * @Classname PayServiceFallbackFactory
 * @Description TODO
 * @Date 2020/9/9 18:29
 */
@Slf4j
public class PayServiceFallbackFactory implements FallbackFactory<PayServiceFeign> {

    private static final BaseResponse response = BaseResponse.failure("远程调用超时异常");

    @Override
    public PayServiceFeign create(Throwable throwable) {
        return new PayServiceFeign() {
            @Override
            public BaseResponse payController(PayRequest request) {
                log.error("远程调用超时异常",throwable);
                return response;
            }

            @Override
            public BaseResponse limitingPayController(PayRequest request) {
                log.error("远程调用超时异常",throwable);
                return response;
            }

            @Override
            public BaseResponse redissonPayController(PayRequest request) {
                log.error("远程调用超时异常",throwable);
                return response;
            }
        };
    }
}
