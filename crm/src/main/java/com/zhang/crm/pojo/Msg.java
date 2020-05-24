package com.zhang.crm.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Msg {
    private int code;
    private String message;
    private Map<String, Object> maps = new HashMap<>();

    public static Msg success() {
        Msg result = new Msg();
        result.code = 100;
        result.message = "处理成功";
        return result;
    }

    public static Msg fail() {
        Msg result = new Msg();
        result.code = 200;
        result.message = "处理失败";
        return result;
    }

    public  Msg add(String key,Object value){
        this.getMaps().put(key,value);
        return this;


    }

}
