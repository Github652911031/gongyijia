package com.zy.base.exception;

import com.zy.base.domain.enums.BusinessCenterExceptionEnum;
import lombok.Data;

@Data
public class BusinessCenterException extends RuntimeException{
    private int errorCode;

    private String errorMsg;

    public BusinessCenterException(int errorCode, String errorMsg){
        super();
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BusinessCenterException(BusinessCenterExceptionEnum enm) {
        this(enm.getCode(), enm.getMsg());
    }
    public BusinessCenterException(BusinessCenterExceptionEnum enm,String str) {
        this(enm.getCode(), enm.getMsg() + "," + str);
    }
}
