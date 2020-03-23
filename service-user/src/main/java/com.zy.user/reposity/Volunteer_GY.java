package com.zy.user.reposity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "volunteer_GY")
public class Volunteer_GY {
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

    @Column
    private String identificationNum;

    @Column
    private String name;

    @Column
    private String sex;

    @Column
    private String birthDate;

    @Column
    private String address;
}
