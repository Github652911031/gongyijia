package com.zy.zuul.filter;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.HttpServletRequestWrapper;
import com.zy.base.domain.enums.BusinessCenterExceptionEnum;


import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import com.zy.zuul.feign.UserClientService;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Map;

import static com.zy.base.domain.enums.TypeEnum.ACCOUNT_COMMUNITY;
import static com.zy.base.domain.enums.TypeEnum.ACCOUNT_USER;


/**
 * token校验
 */
@Component
@Slf4j
public class AccessFilter extends BaseFilter {



    @Autowired
    private UserClientService userClientService;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        if (this.isInWhiteUrlList()) {
            //如果是白名单url，则直接放过
            ctx.set(CHECKED_KEY);
        }else {
            String token = request.getHeader(ACCESS_TOKEN);
            if (StringUtils.isEmpty(token)) {
                this.handleErrorResponse(ctx, BusinessCenterExceptionEnum.PERMISSION_DENIED);
                return null;
            }

            long userId = 0;
            long communityId = 0;
            Map<String, Object> data = userClientService.validToken(token);
            if(MapUtils.isEmpty(data) || !data.containsKey("type")) {
                this.handleErrorResponse(ctx, BusinessCenterExceptionEnum.NOT_LOGIN);
                return null;
            }
            if(data.get("type").equals(ACCOUNT_USER.getDesc())) {
                userId = Long.parseLong((String)data.get("id"));
            }else if(data.get("type").equals(ACCOUNT_COMMUNITY.getDesc())) {
                communityId = Long.parseLong((String)data.get("id"));
            }

            ctx.addZuulRequestHeader(USER_ID_KEY, String.valueOf(userId));
            ctx.addZuulRequestHeader(COMMUNITY_ID, String.valueOf(communityId));
        }

        return null;

    }


}
