package com.vic.dao.teacher;

import com.vic.pojo.Teacher;

import java.io.Serializable;
import java.util.List;

public interface TeacherMapper {

    /**
     * 根据导师的编号查询出 所有老师的信息
     */
    List<Teacher> selectTeachersByTid(Serializable id);

    /**
     * 根据老师的编号，查询自身信息和所有的导师信息
     */
    Teacher selectByTeacherId(Serializable id);

    Teacher selectByTeacherId_association(Serializable id);

}
