package com.chen.exception;

import java.io.IOException;

/**
 * Created by chen on 2017/6/14.
 */
public class GetReponseObjExceoption extends IOException {
    /**
     * Constructs an {@code IOException} with {@code null}
     * as its error detail message.
     */
    public GetReponseObjExceoption(String url) {
        super("请求对象获取响应对象时发生错误,请求路径为：" + url);
    }
}
