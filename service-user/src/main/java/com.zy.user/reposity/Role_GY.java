package com.zy.user.reposity;

import javax.persistence.*;

/**
 * 角色表
 */
@Entity
@Table(name = "role_GY")
public class Role_GY {
    @Id
    private Long id;

    //角色描述
    @Column(length = 32)
    private String description;


}
