package lrw.demo.lib.common.exception;

import lombok.Getter;
import lrw.demo.lib.common.ErrorCode;

/**
 * @author by lrw
 * @Classname BaseException
 * @Description TODO
 * @Date 2020/9/14 10:36
 */
@Getter
public class BaseException extends RuntimeException {

    private ErrorCode code;

    public BaseException(){}

    public BaseException(ErrorCode code){
        super(code.getMessage());

        this.code = code;
    }
    public BaseException(ErrorCode code,Throwable throwable){
        super(code.getMessage(),throwable);
        this.code = code;
    }


}
