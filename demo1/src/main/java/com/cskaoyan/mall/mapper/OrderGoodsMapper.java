package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.OrderGoods;
import com.cskaoyan.mall.vo.CheckOrderGood;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderGoods record);

    int insertSelective(OrderGoods record);

    OrderGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderGoods record);

    int updateByPrimaryKey(OrderGoods record);

    List<OrderGoods> selectByOrderId(int id);

    List<OrderGoods> selectOrderByGoodsId(Integer goodsId);

    List<OrderGoods> selectOrderGoodsByOrderId(Integer orderId);

    OrderGoods selectOrderGoodsByOrderIdAndGoodsId(@Param("orderId")int orderId,@Param("goodsId")int goodsId);

    void updateComment(@Param("orderGoodsId") Integer orderGoodsId, @Param("comment") int comment);

    void insertSelectiveCustom(CheckOrderGood checkOrderGood);
}
