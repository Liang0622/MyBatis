package com.vic;

import com.vic.dao.UserMapper;
import com.vic.pojo.User;
import com.vic.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class UserTest {
    UserMapper mapper=null;  // User对应的接口
    SqlSession session=null;

    @Before
    public  void  before(){
        //通过工具类获取session
        session= SqlSessionFactoryUtil.getSession();
        //使用mapper动态代理
        mapper= session.getMapper(UserMapper.class);
    }

    @After
    public   void  after(){
        session.commit();
    }



    /**
     * 新增用户
     */
    @Test
    public void addUser(){
        User user=new User("小黑黑","admin");
        mapper.addUser(user);
        //没有id是因为数据库中已经设置了 主键自增  我们能不能获取新增的id
        System.out.println(user.getId());
    }

    /**
     * 我们需要新增用户之后，接着对此用户进行操作！
     * 就是需要获取用户的id
     */
    @Test
    public void addUserById(){
        User user=new User("G小调","admin");
        mapper.addUserById(user);
        //没有id是因为数据库中已经设置了 主键自增  我们能不能获取新增的id
        System.out.println(user.getId());
    }


    /**
     * 删除
     */
    @Test
    public  void  deleteUser(){
        mapper.deleteUser(20);
    }
    /**
     * 修改
     */
    @Test
    public  void  updateUser(){
        User  user=new User();
        user.setId(16); //需要修改的id
        user.setUserName("大笨鸟");
        user.setPassword("adminssss");
        mapper.updateUser(user);
    }

    /**
     * 查询指定的User
     */
    @Test
    public  void   selectById(){
        User user=   mapper.selectUserById(9);
        System.out.println(user);
    }
    /**
     * 查询所有的User
     */
    @Test
    public  void   selectAll(){
        List<User> user=   mapper.selectAllUsers();
        System.out.println(user);
    }

    /**
     * 模糊查询
     */
    @Test
    public void  selectLike(){
        List<User> user=  mapper.selectByNamelike("a");
        System.out.println(user);
    }

    @Test
    public void testGetUserListByMap(){
        User user=mapper.getUserListByMap(19);
        System.out.println(user);
    }
}
