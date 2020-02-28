package com.zy.zuul.feign;



import com.zy.base.web.ResultData;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class UserClientService {
    @Autowired
    private UserClient userClient;



    public Map<String, Object> validToken(String token) {
        ResultData<Map<String, Object>> resultData = userClient.validToken(token);
        if (resultData.isSuccess()) {
            return resultData.getData();
        }
        return null;
    }






}
