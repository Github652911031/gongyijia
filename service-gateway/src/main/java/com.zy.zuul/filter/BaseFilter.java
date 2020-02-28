package com.zy.zuul.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;

import com.netflix.zuul.context.RequestContext;
import com.zy.base.domain.enums.BusinessCenterExceptionEnum;

import com.zy.base.web.ResultData;
import com.zy.zuul.config.UrlConfig;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

import java.util.HashSet;
import java.util.Set;

public abstract class BaseFilter extends ZuulFilter {

    protected static final String ACCESS_TOKEN = "accessToken";
    protected static final String CHECKED_KEY = "t_checked";
    protected static final String ERROR_HANDLED = "t_errorhandled";
    protected static final String USER_ID_KEY = "h_userId";
    protected static final String COMMUNITY_ID = "h_communityId";


    private Set<String> whiteUrlSet;

    @Autowired
    private UrlConfig urlConfig;

    protected void handleErrorResponse(RequestContext ctx, BusinessCenterExceptionEnum gwee) {
        this.handleErrorResponse(ctx, gwee.getCode(), gwee.getMsg());
    }

    /**
     * 处理异常
     *
     * @param ctx
     */
    protected void handleErrorResponse(RequestContext ctx, Integer code, String msg) {
        ctx.setSendZuulResponse(false);
        //请求被确认过
        ctx.set(ERROR_HANDLED);
        ctx.setResponseStatusCode(401);// 返回错误码
        ResultData<String> resultData = new ResultData<>();
        resultData.setCode(code);
        resultData.setMsg(msg);
        ctx.setResponseBody(JSON.toJSONString(resultData));// 返回错误内容

    }

    protected boolean isInWhiteUrlList() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        // 白名单处理
        if (whiteUrlSet == null) {
            whiteUrlSet = new HashSet<>(urlConfig.getWhiteList());
        }
        if (whiteUrlSet != null) {
            String path = request.getRequestURI();
            return whiteUrlSet.contains(path);
        }
        return false;
    }
}
