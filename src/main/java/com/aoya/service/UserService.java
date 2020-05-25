package com.aoya.service;

import com.aoya.domain.AyCode;
import com.aoya.domain.AyUser;
import com.aoya.mapper.AyCodeMapper;
import com.aoya.mapper.AyUserMapper;
import com.aoya.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program:aoya_qrcode
 * @Author:SL
 * @Date:2020-5-12 11:19
 */
@Service
public class UserService {
    @Autowired
    private AyUserMapper userMapper;
    @Autowired
    private AyCodeMapper codeMapper;

    public Result queryImgById(int id, String phone) {
        Result result = new Result();
        Map<String, Object> map = new HashMap<>();
        int i = userMapper.selectUserByPhone(phone);
        if(i == 0) {
            result.setMsg("无法查看！");
            return result;
        }
        AyCode code = codeMapper.selectByPrimaryKey(id);
        if (code != null) {
            map.put("imgUrl", code.getAycCodeUrl());
        }
        result.addElement("map", map);
        return result.changeSuccess();
    }

    public Result insert(String name, String phone) {
        Result result = new Result();
        if (userMapper.selectUserByPhone(phone) == 1) {
            result.setMsg("该用户已存在");
            return result;
        }
        AyUser user = new AyUser(name, phone);
        user.setAyuUpdateTime(new Date());
        Integer i = userMapper.insertSelective(user);
        if (i == 1) {
            result.setMsg("添加成功");
            result.changeSuccess();
        }
        return result;
    }

    public Result queryCode(Integer id, String phone) {
        Result result = new Result();
        if (userMapper.selectUserByPhone(phone) <= 0) {
            result.setMsg("没有权限无法查看");
            return result;
        }
        AyCode code = codeMapper.selectByPrimaryKey(id);
        result.addElement("codeUrl", code.getAycImgUrl());
        return result.changeSuccess();
    }
}
