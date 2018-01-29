package com.vic.dao.student;

import com.vic.pojo.Student;

import java.io.Serializable;

public interface StudentMapper {
    /**
     * 根据学生的编号查询    所有的老师信息
     */
    Student selectTeachersBySid(Serializable id);
}
