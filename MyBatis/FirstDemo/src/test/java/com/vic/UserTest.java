package com.vic;

import com.vic.dao.UserMapper;
import com.vic.pojo.User;
import com.vic.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class UserTest {
    private Logger logger=Logger.getLogger(UserTest.class);
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
        if(session!=null){
            session.commit();
            session.close();
        }

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
        try {
            mapper.updateUser(user);
        } catch (Exception e) {
            if(session!=null)
                session.rollback();
            logger.error(e);
        }
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

    @Test//使用别名解决字段名和属性名不一致
    public void testGetAllUsers(){
        List<User> userList=mapper.getAllUsers();
        Iterator<User> userIterator=userList.iterator();
        while(userIterator.hasNext()){
            User u=userIterator.next();
            logger.debug(u);
        }
    }

    @Test//使用resultMap，手动映射解决字段名和属性名不一致
    public void testGetUserListByMap(){
        User user=mapper.getUserListByMap(19);
        System.out.println(user);
    }

    @Test//封装成User对象实现多条件查询
    public void testFindUserByParams1(){
        User user=new User();
        user.setUserName("小");
        user.setId(17);
        List<User> userList=mapper.findUserByParams1(user);
        Iterator<User> userIterator=userList.iterator();
        while(userIterator.hasNext()){
            User u=userIterator.next();
            logger.debug(u);
        }
    }
    @Test//封装成Map实现多条件查询
    public void testFindUserByParams2(){
        Map<String,Object> conditionMap=new HashMap<String, Object>();
        String name="小";
        Integer id=17;

        conditionMap.put("key1",name);
        conditionMap.put("key2",id);
        List<User> userList=mapper.findUserByParams2(conditionMap);
        Iterator<User> userIterator=userList.iterator();
        while(userIterator.hasNext()){
            User u=userIterator.next();
            logger.debug(u);
        }
    }
    @Test//使用索引实现多条件查询
    public void testFindUserByParams3(){
        String name="小";
        Integer id=17;
        List<User> userList=mapper.findUserByParams3(name,id);
        Iterator<User> userIterator=userList.iterator();
        while(userIterator.hasNext()){
            User u=userIterator.next();
            logger.debug(u);
        }
    }
    @Test//使用Param注解实现多条件查询
    public void testFindUserByParams4(){
        String name="小";
        Integer id=17;
        List<User> userList=mapper.findUserByParams4(name,id);
        Iterator<User> userIterator=userList.iterator();
        while(userIterator.hasNext()){
            User u=userIterator.next();
            logger.debug(u);
        }
    }

    @Test//封装成Map实现多条件查询
    public void testFindUsers_Map_User(){
        Map<String,User> conditionMap=new HashMap<String, User>();
        User user=new User();
        user.setUserName("小");
        user.setId(17);
        conditionMap.put("mapKey",user);
        List<User> userList=mapper.findUsers_Map_User(conditionMap);
        Iterator<User> userIterator=userList.iterator();
        while(userIterator.hasNext()){
            User u=userIterator.next();
            logger.debug(u);
        }
    }


}
