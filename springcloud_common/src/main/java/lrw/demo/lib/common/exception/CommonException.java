package lrw.demo.lib.common.exception;

import lrw.demo.lib.common.ErrorCode;

/**
 * @author by lrw
 * @Classname CommonException
 * @Description TODO
 * @Date 2020/9/14 10:59
 */
public class CommonException extends BaseException {

    public CommonException(ErrorCode code) {
        super(code);
    }

    public CommonException(ErrorCode code, Throwable cause) {
        super(code, cause);
    }
}
