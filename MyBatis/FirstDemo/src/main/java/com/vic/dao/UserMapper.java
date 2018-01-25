package com.vic.dao;

import com.vic.pojo.User;

import java.io.Serializable;
import java.util.List;

public interface UserMapper {
    /**
     * 新增用户
     */
    void addUser(User user);
    /**
     * 新增用户  但是可以拿到 新增用户的id    主键回填
     */
    void addUserById(User user);

    /**
     * 删除
     */
    void  deleteUser(Serializable id);

    /**
     * 修改
     */
    void  updateUser(User user);

    /**
     * 查询指定的User
     */

    User  selectUserById(Serializable id);

    /**
     * 查询所有
     */
    List<User> selectAllUsers();

    /**
     * 根据名称进行模糊查询
     */
    List<User> selectByNamelike(String name);

    User getUserListByMap(Serializable id);
}
