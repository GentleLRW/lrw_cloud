package lrw.demo.lib.common;

/**
 * @author by lrw
 * @Classname BaseErrorCode
 * @Description TODO
 * @Date 2020/9/14 10:52
 */
public interface BaseErrorCode {

    public final static ErrorCode BASE_FAILURE = ErrorCode.of(BaseCode.FAILURE,"业务异常");

    public final static ErrorCode LIMIT_FAILURE = ErrorCode.of(BaseCode.FAILURE,"访问人数过多，请稍后访问");

    public final static ErrorCode PAY_TYPE_FAILURE = ErrorCode.of(BaseCode.FAILURE,"不存在此支付类型");

}
