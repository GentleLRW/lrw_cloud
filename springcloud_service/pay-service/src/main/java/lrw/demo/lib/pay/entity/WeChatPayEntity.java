package lrw.demo.lib.pay.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author by lrw
 * @Classname WeChatPayEntity
 * @Description TODO
 * @Date 2020/9/2 16:30
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@ToString
public class WeChatPayEntity extends PayEntity {

    private String weChatPay;

}
