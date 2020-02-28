package com.zy.user.reposity;


import lombok.Data;

import javax.persistence.*;

/**
 * 社工表
 */
@Entity
@Table(name = "social_worker_GY")
@Data
public class SocialWorker_GY {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    //对应的账户id
    @Column
    private Long accountId;

    /**
     * todo:
     * 下面是一些基本信息
     *  包括身份证号码，姓名，性别，出生日期，居住地
     */

}
