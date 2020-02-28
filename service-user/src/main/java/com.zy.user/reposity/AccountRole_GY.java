package com.zy.user.reposity;

import lombok.Data;

import javax.persistence.*;

/**
 * 用户角色对应表
 */
@Data
@Entity
@Table(name = "account_role_GY")
public class AccountRole_GY {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    //角色id
    @Column
    private Long roleId;

    //账户id
    @Column
    private Long accountId;
}
