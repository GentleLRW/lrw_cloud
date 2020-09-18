package lrw.demo.lib.dto.payService.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lrw.demo.lib.dto.BaseRabbitMqSign;
import lrw.demo.lib.dto.eum.PayMannerEnum;

import java.math.BigDecimal;

/**
 * @author by lrw
 * @Classname PayRequest
 * @Description TODO
 * @Date 2020/9/2 15:42
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PayRequest extends BaseRabbitMqSign {

    private PayMannerEnum payManner;

    private BigDecimal payPrice;

}
