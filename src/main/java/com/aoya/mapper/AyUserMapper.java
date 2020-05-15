package com.aoya.mapper;

import com.aoya.domain.AyCode;
import com.aoya.domain.AyUser;


public interface AyUserMapper {
    int deleteByPrimaryKey(Integer ayuId);

    int insert(AyUser record);

    int insertSelective(AyUser record);

    AyCode selectByPrimaryKey(Integer ayuId);

    int updateByPrimaryKeySelective(AyUser record);

    int updateByPrimaryKey(AyUser record);
}