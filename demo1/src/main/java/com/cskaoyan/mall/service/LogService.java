package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.LogListInfo;

public interface LogService {
    LogListInfo selectAllLog(int page, int limit, String name, String sort, String order);
}
