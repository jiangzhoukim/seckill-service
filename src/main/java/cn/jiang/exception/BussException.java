package cn.jiang.exception;

import cn.jiang.result.ResultCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wangyiwen
 * @version 1.0 Created in 2019-10-11 16:32
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BussException extends RuntimeException {

    private ResultCodeEnum resultCode;

    public BussException(ResultCodeEnum resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    /**
     * 使用自定义的消息
     *
     * @param resultCode
     * @param message
     */
    public BussException(ResultCodeEnum resultCode, String message) {
        super(message);
        this.resultCode = resultCode;
    }

    public BussException(int code) {
        resultCode = ResultCodeEnum.valueOfEnum(code);
    }

    public BussException(String message) {
        super(message);
    }
}
