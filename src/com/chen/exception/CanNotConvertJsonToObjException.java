package com.chen.exception;

/**
 * Created by chen on 2017/6/15.
 */
public class CanNotConvertJsonToObjException extends Exception {
    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public CanNotConvertJsonToObjException(String ObjName) {
        super("不能将json转化为指定对象"+ObjName);
    }
}
