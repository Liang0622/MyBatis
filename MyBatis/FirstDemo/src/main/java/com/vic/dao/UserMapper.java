package com.vic.dao;

import com.vic.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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

    /**
     * 使用别名，解决字段名和对象属性不一致
     * @return
     */
    List<User> getAllUsers();

    /**
     * resultMap 手动映射，解决属性名和字段名不一致
     * @param id
     * @return
     */
    User getUserListByMap(Serializable id);

    /**
     * 使用User对象实现多条件查询
     * @param user
     * @return
     */
    List<User> findUserByParams1(User user);
    /**
     * 使用Map实现多条件查询
     * @param conditionMap
     * @return
     */
    List<User> findUserByParams2(Map<String,Object> conditionMap);
    /**
     * 使用索引实现多条件查询
     * @param name
     * @param id
     * @return
     */
    List<User> findUserByParams3(String name,Integer id);/**
     * 使用索引实现多条件查询
     * @param name
     * @param id
     * @return
     */
    List<User> findUserByParams4(@Param("a") String name, @Param("b") Integer id);

    List<User> findUsers_Map_User(Map<String,User> conditionMap);


}
