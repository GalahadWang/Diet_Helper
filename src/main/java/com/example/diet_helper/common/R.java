package com.example.diet_helper.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * The generic return result, server response data will eventually be encapsulated into this object
 * @param <T>
 */
@Data
public class R<T> {

    private Integer code; //Code: 1 success, 0 and other numbers are failures

    private String msg; //Error message

    private static String successMsg = "";

    private T data; //data

    private Map map = new HashMap(); //Dynamic data


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
                successMsg += "; "; // Separate multiple messages
            }
            successMsg += customMessage;
        }
    }

}