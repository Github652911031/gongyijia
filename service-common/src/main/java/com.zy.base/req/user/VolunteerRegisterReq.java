package com.zy.base.req.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class VolunteerRegisterReq {
    @NotNull
    private String accountName;

    @NotNull
    private String password;

    @NotNull
    private String identificationNum;

    @NotNull
    private String name;

    @NotNull
    private String sex;

    @NotNull
    private String birthDate;

    @NotNull
    private String address;
}