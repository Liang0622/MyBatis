package com.vic;

import com.vic.dao.teacher.TeacherMapper;
import com.vic.pojo.Teacher;
import com.vic.util.SessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TeacherTest {
    private Logger logger=Logger.getLogger(StudentTest.class);
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
    public void selectTeacherById(){
        Teacher teacher=mapper.selectTeacherById(1);
        logger.debug("第一次查询:"+teacher);
        session.close();//session关闭,

        session=SessionFactory.getSession();//再次获得session
        mapper=session.getMapper(TeacherMapper.class);
        teacher=mapper.selectTeacherById(1);
        logger.debug("第二次查询:"+teacher);
    }
    @Test
    public void selectTeacherById_add(){
        Teacher teacher=mapper.selectTeacherById(1);
        logger.debug("第一次查询:"+teacher);
        session.close();//session关闭,

        session=SessionFactory.getSession();//再次获得session
        mapper=session.getMapper(TeacherMapper.class);
        Integer count=mapper.addTeacher(new Teacher(8,"老师7",1));
        logger.debug("添加老师记录数："+count);
        teacher=mapper.selectTeacherById(1);
        logger.debug("第二次查询:"+teacher);
    }
}
