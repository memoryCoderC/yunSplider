package com.chen.exception;

import java.io.IOException;

/**
 * Created by chen on 2017/6/14.
 * 爬取的状态码不是成功的异常
 */
public class NetStateNotOKException extends IOException{
    /**
     * Constructs an {@code IOException} with {@code null}
     * as its error detail message.
     */
    public NetStateNotOKException(String url,int status) {
        super("访问url："+url+"时不成功,状态码为"+status);
    }
}
