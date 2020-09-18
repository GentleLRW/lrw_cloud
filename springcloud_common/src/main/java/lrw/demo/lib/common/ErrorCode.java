package lrw.demo.lib.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author by lrw
 * @Classname ErrorCode
 * @Description TODO
 * @Date 2020/9/14 10:50
 */
@Getter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class ErrorCode {

    private Integer code;

    private String message;

}
