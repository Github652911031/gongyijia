package com.zy.user.enums;

public enum RoleEnums {
    ORDINARY(1,"普通用户"),
    SOCIALWORKER(2, "社工"),
    VOLUNTEER(3, "志愿者"),
    ADMIN(4, "管理员")
    ;

    private long code;
    private String desc;
    RoleEnums(long code, String description){
        this.code = code;
        this.desc = description;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
