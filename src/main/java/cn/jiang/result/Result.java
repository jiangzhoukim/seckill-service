package cn.jiang.result;

import cn.jiang.exception.AuthenticationException;
import cn.jiang.exception.BussException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.internal.Nullable;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * JiangZhou
 * @param <T>
 */
@Slf4j
@Data
public class Result<T> {

    private Integer code = 200;
    private  String message;

    private T data;

    private  ErrorInfo errorInfo;

    public  Result(){}

    @JsonIgnore
    public  ErrorInfo getErrorResult(){
        log.error("error={}",errorInfo);
        return  errorInfo;
    }

    /**
     * 如果业务异常则抛出
     */
    @JsonIgnore
    public T getDataWithThrow() {
        successOrThrow();
        return data;
    }

    public Result(ResultCodeEnum resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public Result(ResultCodeEnum resultCode, T data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    /**
     * 返回成功
     */
    public static <T> Result<T> success(@Nullable T t) {
        return new Result<>(ResultCodeEnum.SUCCESS, t);
    }

    /**
     * 返回成功
     */
    public static <T> Result<T> success() {
        return new Result<>(ResultCodeEnum.SUCCESS);
    }

    /**
     * 返回失败
     */
    public Result<T> failure(ResultCodeEnum resultCode, T data) {
        return new Result<>(resultCode, data);
    }


    /**
     * 业务异常 返回失败
     */
    public static <T> Result<T> failure(ResultCodeEnum resultCode) {
        return new Result<>(resultCode);
    }

    /**
     * 其他 异常
     */
    public static <T> Result<T> error(ErrorInfo errorResult) {
        log.info("errorResult: {}", errorResult);
        return new Result<>(ResultCodeEnum.FAIL);
    }

    /**
     * 判断返回是否为成功
     *
     * @return 是否成功
     */
    @JsonProperty(value = "success")
    public boolean isSuccess() {
        return this.code == 200;
    }

    /**
     * 判断返回是否为成功
     *
     * @return 是否成功
     */
    @JsonIgnore
    public boolean isNotSuccess() {
        return !isSuccess();
    }

    public void successOrThrow() {
        if (!isSuccess()) {
            Integer code = this.code;
            if (code.equals(ResultCodeEnum.USER_TOKEN_EXPIRED.getCode())) {
                throw new AuthenticationException(code);
            } else {
                throw new BussException(code);
            }
        }
    }

}
