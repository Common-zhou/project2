package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.Stat;

public interface StatService {
    Stat statUser();
    Stat statOrder();
    Stat statGood();
}
