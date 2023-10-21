package com.example.diet_helper.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回结果，服务端响应的数据最终都会封装成此对象
 * @param <T>
 */
@Data
public class R<T> {

    private Integer code; //编码：1成功，0和其它数字为失败

    private String msg; //错误信息

    private static String successMsg = "";

    private T data; //数据

    private Map map = new HashMap(); //动态数据

//    public R() {
//        this.code = 1; // 默认为成功
//        this.msg = ""; // 初始化为空字符串
//    }

    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static <T> R<T> error(String msg) {
        R r = new R();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

    public void appendSuccessMessage(String customMessage){
        if (customMessage != null && !customMessage.isEmpty()) {
            if (successMsg.isEmpty()) {
                successMsg += "; "; // 分隔多条消息
            }
            successMsg += customMessage;
        }
    }

}