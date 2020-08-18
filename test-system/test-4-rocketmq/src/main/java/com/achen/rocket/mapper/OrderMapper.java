package com.achen.rocket.mapper;

import com.achen.rocket.entity.OrderEntity;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrderMapper {

    @Insert(value = "INSERT INTO `order_info` VALUES (#{id}, " +
            "#{name}, #{orderCreatetime}, #{orderMoney}, #{orderState}, #{commodityId},#{orderId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int addOrder(OrderEntity orderEntity);


    @Select("SELECT * from order_info where order_id=#{orderId};")
    public OrderEntity findOrderId(@Param("orderId") String orderId);

}
