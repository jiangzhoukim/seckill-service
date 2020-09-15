package cn.jiang.result;

import lombok.Data;

import java.util.Date;

/**
 * @author wangyiwen
 * @version 1.0 Created in 2019-10-17 14:35
 */
@Data
public class ErrorInfo {

    /**
     * 异常发生时间
     */
    private Date date;

    /**
     * 异常类名
     */
    private String type;

    /**
     * 异常描述
     */
    private String message;

    /**
     * 异常堆栈
     */
    private String stackTrace;
}
