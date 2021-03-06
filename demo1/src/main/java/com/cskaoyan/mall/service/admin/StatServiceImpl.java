package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.Stat;
import com.cskaoyan.mall.bean.StatGood;
import com.cskaoyan.mall.bean.StatOrder;
import com.cskaoyan.mall.bean.StatUser;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.mapper.OrderMapper;
import com.cskaoyan.mall.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

/**
 * 统计图的service
 * Author: ywx
 */
@Service
public class StatServiceImpl implements StatService {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    //这里需要Autowired各个模块的service
    //调用各个模块的service方法获取数据复制

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public Stat statUser() {
        Stat stat = new Stat();
        String columns[] = {"day","users"};
        stat.setColumns(columns);
//        StatUser statUser = new StatUser();
        //user模块提供给我user人数
//        statUser.setUsers(20);
        //user模块提供给我user创建时间
//        String format = simpleDateFormat.format(new Date());
//        statUser.setDay(format);
        //user模块把所有数据封装成statUser数组
//        StatUser rows[] = {statUser};
//        stat.setRows(rows);

        StatUser[] statUsers = userMapper.getStatUsers();
        stat.setRows(statUsers);
        return stat;
    }

    @Override
    public Stat statOrder() {
        Stat stat = new Stat();
        //"columns":["day","orders","customers","amount","pcr"]
        String columns[] = {"day","orders","customers","amount","pcr"};
        stat.setColumns(columns);

        StatOrder[] statOrders = orderMapper.getStatOrders();
        stat.setRows(statOrders);
        return stat;

    }

    @Override
    public Stat statGood() {
        Stat stat = new Stat();
        //"columns":["day","orders","products","amount"]
        String columns[] = {"day","orders","products","amount"};
        stat.setColumns(columns);

//        StatGood statGood = new StatGood();
//        statGood.setAmount(1234.00);
//        String format = simpleDateFormat.format(new Date());
//        statGood.setDay(format);
//        statGood.setOrders(13);
//        statGood.setProducts(20);
//
//        StatGood rows[] = {statGood};
//        stat.setRows(rows);
        StatGood[] statGoods = goodsMapper.getStatGoods();
        stat.setRows(statGoods);
        return stat;
    }
}
