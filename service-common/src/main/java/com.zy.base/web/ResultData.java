package com.zy.base.web;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
@SuppressWarnings("unused")
public class ResultData<T> {

    private int code;

    private String msg;

    private T data;


    public ResultData() {
        this(null);
    }

    public ResultData(T data){
        this.code = 100000;
        this.msg = "success";
        this.data = data;
    }


    public ResultData(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultData(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    @JSONField(serialize = false)
    public boolean isSuccess() {
        return code == 100000;
    }
}
