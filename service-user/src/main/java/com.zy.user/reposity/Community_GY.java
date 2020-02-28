package com.zy.user.reposity;

import lombok.Data;

import javax.persistence.*;

/**
 * 社区信息表
 */
@Entity
@Table(name = "community_GY")
@Data
public class Community_GY {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    //用户名
    @Column(length = 32)
    private String userName;
    //密码
    @Column
    private String pwd;

    /**
     * todo:一些基本信息字段
     */

}
