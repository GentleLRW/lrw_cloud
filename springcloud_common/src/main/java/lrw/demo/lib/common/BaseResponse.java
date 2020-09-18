package lrw.demo.lib.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author by lrw
 * @Classname BaseResponse
 * @Description TODO
 * @Date 2020/9/3 16:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {

    private int code;

    private String msg;

    private long time;

    private T data;

    public boolean isSuccess(){
        return this.code == BaseCode.SUCCESS;
    }

    public BaseResponse(int code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.time = System.currentTimeMillis();
    }

    public BaseResponse(int code,String msg){
        this(code,msg,null);
    }
    public BaseResponse(ErrorCode code){
        this(code.getCode(),code.getMessage(),null);
    }
    public BaseResponse(int code,T data){
        this(code,null,data);
    }

    public BaseResponse(int code){
        this(code,null,null);
    }

    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<>(BaseCode.SUCCESS,data);
    }

    public static <T> BaseResponse<T> success(){
        return new BaseResponse<>(BaseCode.SUCCESS);
    }

    public static <T> BaseResponse<T> failure(String msg){
        return new BaseResponse<>(BaseCode.FAILURE,msg);
    }

    public static <T> BaseResponse<T> failure(ErrorCode code){
        return new BaseResponse<>(code);
    }



}
