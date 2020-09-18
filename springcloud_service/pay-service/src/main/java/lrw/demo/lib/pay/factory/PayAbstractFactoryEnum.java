package lrw.demo.lib.pay.factory;

import lrw.demo.lib.pay.entity.AliPayEntity;
import lrw.demo.lib.pay.entity.BankCardPayEntity;
import lrw.demo.lib.pay.entity.PayEntity;
import lrw.demo.lib.pay.entity.WeChatPayEntity;
import lrw.demo.lib.utils.UniqueIdUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author by lrw
 * @Classname PayAbstractFactoryEnum
 * @Description TODO
 * @Date 2020/9/3 16:23
 */
public enum PayAbstractFactoryEnum {
    ALIPAY{
        @Override
        public PayEntity getPayEntity(BigDecimal price) {
            return AliPayEntity.builder()
                    .payPrice(price)
                    .aliPay("aliPay")
                    .id(UniqueIdUtils.generateUUID())
                    .createTime(Timestamp.valueOf(LocalDateTime.now()))
                    .updateTime(Timestamp.valueOf(LocalDateTime.now()))
                    .build();
        }
    },
    WECHAT{
        @Override
        public PayEntity getPayEntity(BigDecimal price) {
            return WeChatPayEntity.builder()
                    .payPrice(price)
                    .weChatPay("weChatPay")
                    .id(UniqueIdUtils.generateUUID())
                    .createTime(Timestamp.valueOf(LocalDateTime.now()))
                    .updateTime(Timestamp.valueOf(LocalDateTime.now()))
                    .build();
        }
    },
    BANKCARD{
        @Override
        public PayEntity getPayEntity(BigDecimal price) {
            return BankCardPayEntity.builder()
                    .payPrice(price)
                    .bankCardPay("bankCardPay")
                    .id(UniqueIdUtils.generateUUID())
                    .createTime(Timestamp.valueOf(LocalDateTime.now()))
                    .updateTime(Timestamp.valueOf(LocalDateTime.now()))
                    .build();
        }
    };

    public abstract PayEntity getPayEntity(BigDecimal price);

}
