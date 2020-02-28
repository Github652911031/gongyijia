package com.zy.user.dao;

import com.zy.user.reposity.AccountRole_GY;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRoleDao extends JpaRepository<AccountRole_GY, Long> {
}
