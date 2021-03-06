package com.aoya.mapper;

import com.aoya.domain.AyCode;
import com.aoya.domain.AyUser;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;


public interface AyUserMapper {
    int deleteByPrimaryKey(Integer ayuId);

    int insert(AyUser record);

    int insertSelective(AyUser record);

    AyUser selectByPrimaryKey(Integer ayuId);

    int updateByPrimaryKeySelective(AyUser record);

    int updateByPrimaryKey(AyUser record);

    int selectUserByPhone(String phone);

    List<Map<String, Object>> userList();
}