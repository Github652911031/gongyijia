package com.zy.base.domain.enums;

/**
 * 登录枚举类型
 * 分为用户和社区
 * 用户可以分为志愿者、社工、和仅仅是普通用户三种角色
 */
public enum TypeEnum {
    ACCOUNT_USER("用户"),
    ACCOUNT_COMMUNITY("社区"),
    ;

    private String desc;

    TypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
