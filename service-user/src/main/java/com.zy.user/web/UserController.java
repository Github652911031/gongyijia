package com.zy.user.web;

import com.zy.base.domain.enums.BusinessCenterExceptionEnum;

import com.zy.base.req.user.AccountReq;

import com.zy.base.exception.BusinessCenterException;
import com.zy.base.req.user.VolunteerRegisterReq;
import com.zy.base.web.ResultData;
import com.zy.user.service.JWTService;
import com.zy.user.service.TokenState;
import com.zy.user.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

/**
 * 与用户相关
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController extends BaseController{


    @Autowired
    UserService userService;

    /**
     * 普通用户注册
     */
    @PostMapping("/accountRegister")
    public ResultData<String> accountRegister(@Validated @RequestBody AccountReq accountReq){

        userService.accountRegister(accountReq);
        return this.buildResultData();
    }

    @PostMapping("/accountLogin")
    public ResultData<String> accountLogin(@Validated @RequestBody AccountReq accountReq) {
        String token = userService.accountLogin(accountReq);
        return this.buildResultData(token);
    }

    /**
     * 志愿者注册
     * @param
     * @return
     */
    @PostMapping("/volunteerRegister")
    public ResultData<String> volunteerRegister(@Validated @RequestBody VolunteerRegisterReq volunteerRegisterReq){
        //todo:实现
        return this.buildResultData();
    }

    /**
     * 验证token
     * @param token
     * @return
     */
    @GetMapping("/validToken")
    public ResultData<Map<String, Object>> validToken(@RequestParam String token) {
        if(StringUtils.isEmpty(token)) {
            throw new BusinessCenterException(BusinessCenterExceptionEnum.NOT_LOGIN);
        }
        Map<String, Object> result = JWTService.validToken(token);
        String state = (String)result.get("state");
        switch (TokenState.getTokenState(state)) {
            case EXPIRED:
                throw new BusinessCenterException(BusinessCenterExceptionEnum.NOT_LOGIN);
            case INVALID:
                throw new BusinessCenterException(BusinessCenterExceptionEnum.NOT_LOGIN);
            case VALID:
                return this.buildResultData((Map<String,Object>)result.get("data"));

        }
        return this.buildResultData();
    }

    /**
     * 登录获取用户信息示例
     */
    @GetMapping("/example-user")
    public ResultData<Long> exampleUser() {
        return this.buildResultData(this.getCurrentUserId());
    }

    @GetMapping("/example-community")
    public ResultData<Long> exampleCommunity() {
        return this.buildResultData(this.getCurrentCommunityId());
    }




}
