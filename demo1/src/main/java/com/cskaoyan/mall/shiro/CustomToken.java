package com.cskaoyan.mall.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @Description:
 * @Author: zhou
 * @Date: 2019/10/6
 * @Time 16:06
 */
public class CustomToken extends UsernamePasswordToken {
    private String type;

    public CustomToken(String username, String password, String type) {
        super(username, password);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
