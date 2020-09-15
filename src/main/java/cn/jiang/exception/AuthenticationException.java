package cn.jiang.exception;

import cn.jiang.result.ResultCodeEnum;

/**
 * 401
 *
 * @author wangyiwen
 * @version 1.0 Created in 2019-10-28 17:16
 */
public class AuthenticationException extends BussException {
    public AuthenticationException(ResultCodeEnum resultCode) {
        super(resultCode);
    }

    public AuthenticationException(int code) {
        super(code);
    }
}
