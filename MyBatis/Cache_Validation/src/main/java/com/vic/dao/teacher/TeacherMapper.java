package com.vic.dao.teacher;

import com.vic.pojo.Teacher;

import java.io.Serializable;

public interface TeacherMapper {
/**
 * 二级缓存的使用
 * 01:引入两个jar包  一个ehcache   一个是mybatis-ehcache
 * 02:需要缓存的类   必须实现(implements)一个接口(Serializable)
 * 03:如果不设置其他参数,默认增删改会清空二级缓存中的数据
 */

    Teacher selectTeacherById(Serializable id);
    Integer addTeacher(Teacher teacher);
}
