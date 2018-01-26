package com.vic;

import com.vic.dao.teacher.TeacherMapper;
import com.vic.pojo.Teacher;
import com.vic.util.SessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TeacherTest {
    SqlSession session=null;
    TeacherMapper mapper=null;
    @Before
    public void getSession(){
        session= SessionFactory.getSession();
        mapper=session.getMapper(TeacherMapper.class);
    }
    @After
    public void SessionCommitAndClose(){
        if(session!=null){
            session.commit();
            session.close();
        }
    }
    @Test
    public void selectTeachersByTid(){
        List<Teacher> teacherList=mapper.selectTeachersByTid(0);
        System.out.println(teacherList);
    }

    //@Test
    public void selectByTeacherId(){
        Teacher teacher=mapper.selectByTeacherId(6);
        System.out.println(teacher);
    }

    @Test
    public void selectByTeacherId_association(){
        Teacher teacher=mapper.selectByTeacherId_association(6);
        System.out.println(teacher);
    }

}
