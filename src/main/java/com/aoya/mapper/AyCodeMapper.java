package com.aoya.mapper;

import com.aoya.domain.AyCode;
import org.apache.ibatis.annotations.Mapper;

public interface AyCodeMapper {
    int deleteByPrimaryKey(Integer aycId);

    int insert(AyCode record);

    int insertSelective(AyCode record);

    AyCode selectByPrimaryKey(Integer aycId);

    int updateByPrimaryKeySelective(AyCode record);

    int updateByPrimaryKey(AyCode record);
}