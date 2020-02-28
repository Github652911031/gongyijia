package com.zy.user.dao;

import com.zy.user.reposity.Account_GY;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public  interface AccountDao extends JpaRepository<Account_GY, Long> {
    Account_GY findByAccountNameAndPwd(String accountName, String pwd);
}
