package com.zy.base.exception;



import com.zy.base.domain.enums.BusinessCenterExceptionEnum;
import com.zy.base.web.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;


@ControllerAdvice
@Slf4j
@ResponseBody
public class GlobalExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public ResultData<String> commonErrorHandler(Exception e) throws Exception {
        ResultData<String> r = new ResultData<>();
        r.setMsg(BusinessCenterExceptionEnum.SERVER_ERROR.getMsg());
        r.setCode(BusinessCenterExceptionEnum.SERVER_ERROR.getCode());
        log.error("服务端异常",e);
        return r;
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResultData<String> HttpRequestMethodErrorHandler(HttpRequestMethodNotSupportedException e) throws Exception {
        //错误的method
        ResultData<String> r = new ResultData<>();
        r.setMsg(BusinessCenterExceptionEnum.HTTP_METHOD_NOT_SUPPORT.getMsg());
        r.setCode(BusinessCenterExceptionEnum.HTTP_METHOD_NOT_SUPPORT.getCode());
        return r;
    }

    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    public ResultData<String> HttpMediaTypeErrorHandler() throws Exception {
        //错误的method
        ResultData<String> r = new ResultData<>();
        r.setMsg(BusinessCenterExceptionEnum.CONTENT_TYPE_NOT_SUPPORT.getMsg());
        r.setCode(BusinessCenterExceptionEnum.CONTENT_TYPE_NOT_SUPPORT.getCode());
        return r;
    }


    @ExceptionHandler(value = BusinessCenterException.class)
    public ResultData<String> businessCenterErrorHandler(BusinessCenterException e) throws Exception {
        ResultData<String> r = new ResultData<>();
        r.setMsg(e.getErrorMsg());
        r.setCode(e.getErrorCode());
        return r;
    }



    //400 --缺少参数
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultData<Object>  handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        ResultData<Object> resultData = new ResultData<>();
        resultData.setMsg(BusinessCenterExceptionEnum.WRONG_PARAMETER.getMsg());
        resultData.setCode(BusinessCenterExceptionEnum.WRONG_PARAMETER.getCode());
        log.error("handleMissingServletRequestParameterException 请求参数错误", e);
        return resultData;
    }

    //400 --缺少参数
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultData<Object>  handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ResultData<Object> resultData = new ResultData<>();
        log.error("handleMethodArgumentNotValidException 请求参数错误", e);
        //对于参数绑定错误的异常看看能否拿到defaultMessage
        String defaultMessage = BusinessCenterExceptionEnum.WRONG_PARAMETER.getMsg();
        if(e.getBindingResult()!=null ){
            List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
            if(CollectionUtils.isNotEmpty(fieldErrors)){
                FieldError fieldError =  fieldErrors.get(0);
                if(StringUtils.isNoneBlank(fieldError.getField())){
                    defaultMessage +=(":" +fieldError.getField());
                }
                if(StringUtils.isNoneBlank(fieldError.getDefaultMessage())){
                    defaultMessage +=(","+fieldError.getDefaultMessage());
                }
            }
        }
        resultData.setMsg(defaultMessage);
        resultData.setCode(BusinessCenterExceptionEnum.WRONG_PARAMETER.getCode());

        return resultData;
    }

    // 400 参数解析失败
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultData<Object>  handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        ResultData<Object> resultData = new ResultData<>();
        resultData.setMsg(BusinessCenterExceptionEnum.WRONG_PARAMETER.getMsg());
        resultData.setCode(BusinessCenterExceptionEnum.WRONG_PARAMETER.getCode());
        log.error("handleHttpMessageNotReadableException 请求参数错误", e);
        return resultData;
    }

    //400 参数绑定失败
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BindException.class)
    public ResultData<Object>  handleBindException(BindException e) {
        ResultData<Object> resultData = new ResultData<>();
        String defaultMessage = BusinessCenterExceptionEnum.WRONG_PARAMETER.getMsg();
        if(e.getBindingResult()!=null ){
            List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
            if(CollectionUtils.isNotEmpty(fieldErrors)){
                FieldError fieldError =  fieldErrors.get(0);
                if(StringUtils.isNoneBlank(fieldError.getField())){
                    defaultMessage +=(":" +fieldError.getField());
                }
                if(StringUtils.isNoneBlank(fieldError.getDefaultMessage())){
                    defaultMessage +=(","+fieldError.getDefaultMessage());
                }
            }
        }
        resultData.setMsg(defaultMessage);
        resultData.setCode(BusinessCenterExceptionEnum.WRONG_PARAMETER.getCode());
        log.error("handleBindException 请求参数错误", e);
        return resultData;
    }




}
