package com.zy.user.service;

import com.zy.base.domain.enums.BusinessCenterExceptionEnum;
import com.zy.base.exception.BusinessCenterException;
import com.zy.base.req.user.AccountReq;
import com.zy.user.dao.AccountDao;
import com.zy.base.domain.enums.TypeEnum;
import com.zy.user.dao.AccountRoleDao;
import com.zy.user.enums.RoleEnums;
import com.zy.user.reposity.AccountRole_GY;
import com.zy.user.reposity.Account_GY;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    AccountDao accountDao;

    @Autowired
    AccountRoleDao accountRoleDao;

    @Transactional
    public void accountRegister(AccountReq accountReq) {
        Account_GY account_gy = new Account_GY();
        account_gy.setAccountName(accountReq.getAccountName());
        account_gy.setPwd(accountReq.getPassword());
        accountDao.save(account_gy);
        //在用户角色对应表中插入信息
        AccountRole_GY accountRole_gy = new AccountRole_GY();
        accountRole_gy.setAccountId(account_gy.getId());
        accountRole_gy.setRoleId(RoleEnums.ORDINARY.getCode());
        accountRoleDao.save(accountRole_gy);
    }

    public String accountLogin(AccountReq accountReq) {
        Account_GY account_gy = accountDao.findByAccountNameAndPwd(accountReq.getAccountName(), accountReq.getPassword());
        if(account_gy != null) {
            /**
             * 当用户名和密码匹配的时候，创建token.
             * 步骤如下：
             *  1.填充Payload. payload中包含登录类型（个人还是社区）、id等信息
             *  2.利用payload调用JWTService创建token
             */
            Map<String, Object> payload = new HashMap<>();
            payload.put("type", TypeEnum.ACCOUNT_USER.getDesc());
            payload.put("id", account_gy.getId()+"");
            payload.put("state", TokenState.VALID);
            //设定60分钟过期时间
            payload.put("expire", new Date().getTime() + 60 * 60 * 1000);
            String token = JWTService.createToken(payload);
            return token;
        }
        throw new BusinessCenterException(BusinessCenterExceptionEnum.WRONG_PASSWORD);
    }
}
