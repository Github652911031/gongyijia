package com.zy.zuul.feign;



import com.zy.base.web.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient("service-user")
public interface UserClient {

    @GetMapping("user/validToken")
    ResultData<Map<String, Object>> validToken(@RequestParam("token") String token);

}
