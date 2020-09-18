package lrw.demo.lib.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author by lrw
 * @Classname BaseRabbitMqSign
 * @Description TODO
 * @Date 2020/9/2 15:45
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BaseRabbitMqSign {

    /**
     * 每条消息唯一标识
     */
    private String rabbitMqSignId;

}
