package com.zy.base.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class WebFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;

        WebContext webContext = new WebContext();
        String userIdStr = req.getHeader("h_userId");
        String communityId = req.getHeader("h_communityId");


        if(!StringUtils.isEmpty(userIdStr)) {
            webContext.setUserId(Long.parseLong(userIdStr));
        }

        if(!StringUtils.isEmpty(communityId)) {
            webContext.setCommunityId(Long.parseLong(communityId));
        }

        WebContextUtil.setWebContext(webContext);
        try{
            chain.doFilter(servletRequest, servletResponse);
        } finally {
            WebContextUtil.removeWebContext();
        }
    }

    @Override
    public void destroy() {

    }
}
