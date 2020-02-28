package com.zy.base.req.user;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AccountReq {
    @NotNull
    private String accountName;

    @NotNull
    private String password;

}
