package com.vic.dao.student;

import com.vic.pojo.Student;

import java.io.Serializable;
import java.util.List;

public interface StudentMapper {
    /**
     * 一级缓存 需要注意的三点
     * 01:一级缓存一直存在,不能删除,也不需要自己配置
     * 02:一级缓存依据sql语句的id+sql语句
     * 03:增删改操作会清空内存
     */
    Student selectStudentBySid(Integer sid);
    Student selectStudentBySid2(Integer sid);
    List<Student> selectAllStudent();
    Integer addStudent(Student student);
    Integer delStudent(Serializable sid);
    Integer changeStudent(Student student);
}
