package com.zy.base.web;

import lombok.Data;

/**
 * 用于记录一些用户状态的封装类
 */
@Data
public class WebContext {

    private long userId;

    private long communityId;
}
