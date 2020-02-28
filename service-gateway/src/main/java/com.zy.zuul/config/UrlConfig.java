package com.zy.zuul.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "url")
@Component
@Data
public class UrlConfig {
    /*白名单url列表*/
    private List<String> whiteList = new ArrayList<>();
}
