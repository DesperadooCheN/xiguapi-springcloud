package com.achen.log.mapper;

import com.achen.log.entity.ErrorLogEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ErrorLogMapper {

    @Insert("insert into error_log " +
            "values" +
            "(#{messageId},#{serviceId},#{className},#{methodName}," +
            "#{parameterContent},#{errorContent},#{createTime},#{lineNumber},#{serverIp})")
    int insertErrorLog(ErrorLogEntity errorLogEntity);
}
