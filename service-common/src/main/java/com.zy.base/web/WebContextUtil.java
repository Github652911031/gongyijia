package com.zy.base.web;

public class WebContextUtil {
    private static final ThreadLocal<WebContext> threadLocal = new ThreadLocal<>();

    public static void setWebContext(WebContext webContext) {
        threadLocal.set(webContext);
    }
    public static WebContext getWebContext() {
        return threadLocal.get();
    }
    public static void removeWebContext() {
        threadLocal.remove();
    }
}
