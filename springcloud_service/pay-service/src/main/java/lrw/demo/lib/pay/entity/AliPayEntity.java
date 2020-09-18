package lrw.demo.lib.pay.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author by lrw
 * @Classname AliPayEntity
 * @Description TODO
 * @Date 2020/9/2 16:24
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class AliPayEntity extends PayEntity{

    private String aliPay;



    @Override
    public String toString() {
        return "AliPayEntity{" +
                "aliPay='" + aliPay + '\'' +
                "price='" + getPayPrice() + '\'' +
                '}';
    }
}
