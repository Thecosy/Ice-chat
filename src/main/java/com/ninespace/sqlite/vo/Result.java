package com.ninespace.sqlite.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Data
@Accessors(chain = true)
public class Result {

    private Integer code; //响应码
    private String message; //响应消息
    private Map<String, Object> data = new HashMap<>();

    public static Result ok(){
        Result result = new Result();
        result.setCode(400);
        result.setMessage("成功");
        return result;
    }

    public static Result error(){
        Result result = new Result();
        result.setCode(401);
        result.setMessage("失败");
        return result;
    }

    public static Result other(){
        Result result = new Result();
        result.setCode(402);
        result.setMessage("其他");
        return result;
    }

    public Result data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

}
