package com.achen.rocketmq2.mapper;

import com.achen.rocketmq2.entity.DispatchEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DispatchMapper {

    /**
     * 新增派单任务
     */
    @Insert("INSERT into platoon values (null,#{orderId},#{userId})")
    int insertDistribute(DispatchEntity distributeEntity);


}
