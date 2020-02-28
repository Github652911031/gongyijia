package com.zy.user.reposity;

import lombok.Data;

import javax.persistence.*;

/**
 * 用户表
 */
@Entity
@Table(name = "account_GY")
@Data
public class Account_GY {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    //用户名，也就是手机号码
    @Column(length = 32, unique = true)
    private String accountName;

    //密码
    @Column(length = 255)
    private String pwd;

}
