package com.vic.dao.teacher;

import com.vic.pojo.Student;
import com.vic.pojo.Teacher;

import java.io.Serializable;

public interface TeacherMapper {


    /**
     * 根据老师编号   查询所有学生信息
     */
    Teacher selectStudentsByTid(Serializable id);
}
