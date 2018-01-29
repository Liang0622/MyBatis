package com.vic;

import com.vic.dao.student.StudentMapper;
import com.vic.pojo.Student;
import com.vic.util.SessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class StudentTest {
    private Logger logger=Logger.getLogger(StudentTest.class);
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

    /**
     * 验证缓存的依据
     * 01:xml中select节点的id(即mapper中方法名)
     * 02:传入的参数(参数不同,数据不同)
     */
    @Test
    public void selectStudentBySid(){
        Student s1=mapper.selectStudentBySid(1);
        //此次查询在日志记录中显示相应的查询语句,即执行SQL语句
        logger.debug("第一次查询:"+s1);
        Student s2=mapper.selectStudentBySid(1);
        //日志记录中并没有显示Sql查询语句,走的是一级缓存
        logger.debug("第二次查询:"+s2);
    }
    @Test
    public void selectStudentBySid2(){
        Student s1=mapper.selectStudentBySid(1);
        //此次查询在日志记录中显示相应的查询语句,即执行SQL语句
        logger.debug("第一次查询:"+s1);
        Student s2=mapper.selectStudentBySid(2);
        //日志记录中再次显示Sql查询语句,因为参数不同,一级缓存中没有此记录
        logger.debug("第二次查询:"+s2);
    }
    @Test
    public void selectStudentBySid3(){
        Student s1=mapper.selectStudentBySid(1);
        //此次查询在日志记录中显示相应的查询语句,即执行SQL语句
        logger.debug("第一次查询:"+s1);
        Student s2=mapper.selectStudentBySid2(1);
        //日志记录再次显示查询语句,因为方法不同,即xml中的select的id不同
        logger.debug("第二次查询:"+s2);
    }
    @Test
    public void selectStudentBySid4(){
        List<Student> stus=mapper.selectAllStudent();
        //此次查询在日志记录中显示相应的查询语句,即执行SQL语句
        for (Student s:stus) {
            logger.debug("第一次查询:"+s);
        }
        Student s2=mapper.selectStudentBySid2(1);
        //日志记录再次显示查询语句,还是方法不同,即便首次查询的集合中包含了此条记录
        logger.debug("第二次查询:"+s2);
    }

    /**
     * 增删改对一级缓存的影响
     * 增删改操作会清空一级缓存
     */
    @Test
    public void selectStudentBySid5(){
        Student s1=mapper.selectStudentBySid(1);
        //此次查询在日志记录中显示相应的查询语句,即执行SQL语句
        logger.debug("第一次查询:"+s1);
        //测试增加数据对一级缓存的影响
       /* Student student=new Student(8,"小黑",3);
        Integer count=mapper.addStudent(student);
        logger.debug("添加学生记录条数:"+count);*/

       //测试删除数据对一级缓存的影响
        /*Integer count=mapper.delStudent(8);
        logger.debug("删除学生记录条数:"+count);*/
        //测试修改数据对一级缓存的影响
        Student student=new Student(7,"小黑",3);
        Integer count=mapper.changeStudent(student);
        logger.debug("修改学生记录条数:"+count);

        Student s2=mapper.selectStudentBySid(1);
        //日志记录中再次显示Sql查询语句,增删改操作清空了一级缓存
        logger.debug("第二次查询:"+s2);
    }
}
