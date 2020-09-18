package lrw.demo.lib.pay.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * @author by lrw
 * @Classname PayEntity
 * @Description TODO
 * @Date 2020/9/2 16:28
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class PayEntity extends BaseEntity {

    private BigDecimal payPrice;


}
