package com.vic.dao.user;

import com.vic.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Date;
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

    /**
     * 根据用户id删除用户
     * @param id
     * @return
     */
    int deleteUserById(Integer id);

    /**
     * 测试association的方法
     * @param userRole
     * @return
     */
    List<User> getUserListByRoleId(Serializable userRole);

    /**
     * 测试collectiong的方法
     * @param id
     * @return
     */
    List<User> getAddressListByUserId(Serializable id);

    /**
     * 测试if元素的条件判断
     * @return
     */
    List<User> getUserListTestIf(User user);

    /**
     * if条件判断测试，单表查询，采用where 1=1
     * @param user
     * @return
     */
    List<User> getUserListTestIfOne(User user);

    /**
     * where简化条件判断
     * @param user
     * @return
     */
    List<User> getUserListTestWhere(User user);

    /**
     * if+tirm实现多条件查询
     * @param user
     * @return
     */
    List<User> getUserListTestIfAndTrim(User user);

    /**
     * if+set改造修改操作
     * @param user
     * @return
     */
    int modifyTestIfAndSet(User user);

    /**
     * if+trim改造修改操作
     * @param user
     * @return
     */
    int modifyTestIfAndTrim(User user);

    /**
     * foreach迭代，入参为数组
     * @param roleIds
     * @return
     */
    List<User> getUserListByRoleId_foreach_array(Integer[] roleIds);

    /**
     *  foreach迭代，入参为List
     * @param roleList
     * @return
     */
    List<User> getUserListByRoleId_foreach_list(List<Integer> roleList);

    /**
     * foreach迭代，入参为Map
     * @param conditionMap
     * @return
     */
    List<User> getUserListByConditionMap_foreach_map(Map<String,Object> conditionMap);

    /**
     * choose元素测试方法
     * @param userName
     * @param userCode
     * @param userRole
     * @param creationDate
     * @return
     */
    List<User> getUserList_choose(@Param("userName")String userName,
                                  @Param("userCode")String userCode,
                                  @Param("userRole")Integer userRole,
                                  @Param("creationDate")Date creationDate);


    /**
     * MyBatis分页
     * @param userName
     * @param userRole
     * @param currentPageNo 当前页码
     * @param pageSize  页面容量
     * @return
     */
    List<User> getUserListByPage(@Param("userName")String userName,
                                 @Param("userRole")Integer userRole,
                                 @Param("from")Integer currentPageNo,
                                 @Param("pageSize")Integer pageSize);

}
