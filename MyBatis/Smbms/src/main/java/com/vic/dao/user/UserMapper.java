package com.vic.dao.user;

import com.vic.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface UserMapper {
    /**
     * 获得用户列表的方法
     */
    List<User> getUserList();

    /**
     * 根据用户名查询用户列表，模糊查询，
     * 如果数据库表中的字段名和属性名不一致，需手动映射
     */
    List<User> getUserListByUserName(String username);

    /**
     * 多条件查询
     * 条件参数封装成User对象进行入参
     */
    List<User> getUserListByUser(User user);

    /**
     * 条件参数封装成Map进行入参
     * @param map
     * @return
     */
    List<User> getUserListByMap(Map<String,String> map);

    /**
     * resultType自动映射展示角色名称
     */
    List<User> getUserListByResultType(User user);

    /**
     * resultMap手动映射展示角色名称
     * @param user
     * @return
     */
    List<User> getUserListByResultMap(User user);

    /**
     * 添加用户
     * @param user
     * @return
     */
    int add(User user);

    /**
     * 修改用户
     */
    int modify(User user);

    /**
     * 使用@Param注解实现多参数入参
     * @param pwd 传入的密码，经过@param注解加工，对应xml中#{}内的参数名
     * @param id
     * @return
     */
    int updatePwd(@Param("userPassword")String pwd,@Param("id")Integer id);

    int deleteUserById(Integer id);

    List<User> getUserListByRoleId(Serializable userRole);

    List<User> getAddressListByUserId(Serializable id);
}
