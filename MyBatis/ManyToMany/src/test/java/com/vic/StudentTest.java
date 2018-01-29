package com.vic;

import com.vic.dao.student.StudentMapper;
import com.vic.dao.teacher.TeacherMapper;
import com.vic.pojo.Student;
import com.vic.util.SessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;

public class StudentTest {
    SqlSession session=null;
    StudentMapper mapper=null;
    @Before
    public void getSession(){
        session= SessionFactory.getSession();
        mapper=session.getMapper(StudentMapper.class);
    }
    @After
    public void sessionConmmitAndClose(){
        if(session!=null){
            session.commit();
            session.close();
        }
    }
    @org.junit.Test
    public void selectTeachersBySid(){
        Student s=mapper.selectTeachersBySid(3);
        System.out.println(s);
    }
}
