package lrw.demo.lib.pay.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

/**
 * @author by lrw
 * @Classname BaseEntity
 * @Description TODO
 * @Date 2020/9/2 16:25
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {

    private String id;

    private Timestamp createTime;

    private Timestamp updateTime;
}
