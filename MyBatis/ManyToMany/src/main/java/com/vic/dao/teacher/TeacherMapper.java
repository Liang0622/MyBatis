package com.vic.dao.teacher;

import com.vic.pojo.Student;
import com.vic.pojo.Teacher;

import java.io.Serializable;

public interface TeacherMapper {
    /**
     * 根据学生的编号查询    所有的老师信息
     */
    Student selectTeachersBySid(Serializable id);

    /**
     * 根据老师编号   查询所有学生信息
     */
    Teacher selectStudentsByTid(Serializable id);
}
