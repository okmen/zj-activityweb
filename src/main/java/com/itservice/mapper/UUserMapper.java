package com.itservice.mapper;

import org.apache.ibatis.annotations.Param;

import com.itservice.entity.UUser;

public interface UUserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(UUser record);

    int insertSelective(UUser record);

    UUser selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(UUser record);

    int updateByPrimaryKey(UUser record);
    /**
     * 通过username 获取用户信息
     * @author max
     * @date:   2018年8月17日
     * @Desc :
     * @param username
     * @return
     */
    UUser getUserByUsername(@Param("username")String username);
}