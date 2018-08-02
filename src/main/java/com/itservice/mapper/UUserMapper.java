package com.itservice.mapper;

import com.itservice.entity.UUser;

public interface UUserMapper {
    int deleteByPrimaryKey(String uid);

    int insert(UUser record);

    int insertSelective(UUser record);

    UUser selectByPrimaryKey(String uid);

    int updateByPrimaryKeySelective(UUser record);

    int updateByPrimaryKey(UUser record);
}