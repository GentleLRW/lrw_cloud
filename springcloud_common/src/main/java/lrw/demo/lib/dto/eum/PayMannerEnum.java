package lrw.demo.lib.dto.eum;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author by lrw
 * @Classname PayMannerEnum
 * @Description TODO
 * @Date 2020/9/2 15:25
 */
@Getter
@AllArgsConstructor
public enum PayMannerEnum implements BaseEnum{

    ALIPAY("支付宝"),
    WECHAT("微信"),
    BANKCARD("银行卡");

    private String desc;

}
