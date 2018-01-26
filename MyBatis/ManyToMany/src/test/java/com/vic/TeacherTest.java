package com.vic;

import com.vic.dao.teacher.TeacherMapper;
import com.vic.pojo.Student;
import com.vic.pojo.Teacher;
import com.vic.util.SessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TeacherTest {
    SqlSession session=null;
    TeacherMapper mapper=null;
    @Before
    public void getSession(){
        session= SessionFactory.getSession();
        mapper=session.getMapper(TeacherMapper.class);
    }
    @After
    public void sessionConmmitAndClose(){
        if(session!=null){
            session.commit();
            session.close();
        }
    }
    @Test
    public void selectTeachersBySid(){
        Student s=mapper.selectTeachersBySid(3);
        System.out.println(s);
    }

    @Test
    public void selectStudentsByTid(){
        Teacher t=mapper.selectStudentsByTid(1);
        System.out.println(t.getStudents());
    }
}
