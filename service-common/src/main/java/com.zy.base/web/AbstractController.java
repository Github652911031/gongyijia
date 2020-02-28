package com.zy.base.web;

import com.zy.base.domain.enums.BusinessCenterExceptionEnum;

import com.zy.base.exception.BusinessCenterException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class  AbstractController {
    public <T> ResultData<T> buildResultData() {
        return new ResultData();
    }
    public <T> ResultData<T> buildResultData(T data) {
        if(data instanceof BusinessCenterExceptionEnum) {
            return this.buildResultData((BusinessCenterExceptionEnum)data, null);
        }
        return new ResultData<T>(data);
    }

    public <T> ResultData<T> buildResultData(BusinessCenterExceptionEnum bcee, T data){
        return new ResultData<T>(bcee.getCode(), bcee.getMsg(), data);
    }


    /**
     * 获取用户登录时的当前用户Id
     * @return
     */
    public long getCurrentUserId() {
        long userId = WebContextUtil.getWebContext().getUserId();
        log.info("当前登录用户id为{}",userId);
        if(userId<= 0) {
            log.error("getCurrentUserId info error,获取不到WebContextUtil的人员信息");
            throw new BusinessCenterException(BusinessCenterExceptionEnum.PERMISSION_DENIED);
        }
        return userId;
    }

    public long getCurrentCommunityId() {
        long communityId = WebContextUtil.getWebContext().getCommunityId();
        if(communityId <= 0) {
            log.error("getCurrentUserGroupId info error,获取不到WebContextUtil的人员信息");
            throw new BusinessCenterException(BusinessCenterExceptionEnum.PERMISSION_DENIED);
        }
        return communityId;
    }

}
