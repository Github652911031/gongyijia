package com.zy.base.domain.enums;

public enum BusinessCenterExceptionEnum {

    /**枚举异常类型**/
    SERVER_ERROR(100001, "server error"),
    HTTP_METHOD_NOT_SUPPORT(100002, "http method not support"),
    CONTENT_TYPE_NOT_SUPPORT(100003, "content-type not support"),
    WRONG_PARAMETER(100004, "wrong parameter" ),
    PERMISSION_DENIED(100005, "permission denied"),
    NOT_LOGIN(100006, "not login"),
    /**用户相关**/
    WRONG_PASSWORD(100010, "wrong password"),


    ;


    private int code;
    private String msg;

    BusinessCenterExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }




}
