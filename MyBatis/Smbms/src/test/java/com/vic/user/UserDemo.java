package com.vic.user;

import com.vic.dao.user.UserMapper;
import com.vic.pojo.Address;
import com.vic.pojo.User;
import com.vic.util.SqlSessionFactorySingleUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;


import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserDemo {
    private Logger logger=Logger.getLogger(UserDemo.class);
    @Test//测试count映射
    public void testCount(){
        String resource="mybatis_config.xml";
        SqlSession sqlSession=null;
        int count;
        try {
            //将核心配置文件加载到流中
            InputStream is=Resources.getResourceAsStream(resource);
            //获得SqlSessionFactory函数的一个工厂实例
            SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(is);
            //用工厂获得一个会话
            sqlSession=sqlSessionFactory.openSession();
            //会话执行查询的sql语句
            count=sqlSession.selectOne("com.vic.user.UserMapper.count");
            //log日志记录数据
            logger.debug("count=====>"+count);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //如果会话不为空，那么关闭会话
            if(sqlSession != null)
                sqlSession.close();
        }

    }

    UserMapper mapper=null;
    SqlSession session=null;

    @Before
    public void getSession(){
        //通过工具类获得session
        session= SqlSessionFactorySingleUtil.getSqlSession();
        //使用mapper动态代理
        mapper=session.getMapper(UserMapper.class);
    }
    @After
    public void SessionCommit(){
        session.commit();
    }
    @Test//测试getUserList方法，查询所有用户的信息
    public void testGetUserList(){
        List<User> userList=mapper.getUserList();
        Iterator<User> iterator=userList.iterator();
        while(iterator.hasNext()){
            User user=iterator.next();
            logger.debug("UserName====>"+user.getUserName()+"\tID====>"+user.getId());
        }
    }
    @Test//根据用户名查询相应的用户（模糊查询）
    public void testGetUserListByUserName(){
        List<User> userList=mapper.getUserListByUserName("孙");
        Iterator<User> iterator=userList.iterator();
        while(iterator.hasNext()){
            User user=iterator.next();
            logger.debug("UserName====>"+user.getUserName()+"\tID====>"+user.getId());
        }
    }

    @Test//多条件封装成User对象进行入参
    public void testGetUserListByUser(){
        User user=new User();
        user.setUserName("孙");
        user.setUserRole(3);
        List<User> userList=mapper.getUserListByUser(user);
        Iterator<User> iterator=userList.iterator();
        while(iterator.hasNext()){
            User aUser=iterator.next();
            logger.debug("UserName====>"+aUser.getUserName()+"\tID====>"+aUser.getId());
        }
    }

    @Test//多条件参数封装成map进行入参
    public void testGetUserListByMap(){
       Map<String,String> map=new HashMap<String,String>();
       map.put("uName","赵");//key值对应UserMapper.xml中#{}的参数`
       map.put("uRole","3");//key值对应UserMapper.xml中#{}的参数
        List<User> userList=mapper.getUserListByMap(map);
        Iterator<User> iterator=userList.iterator();
        while(iterator.hasNext()){
            User aUser=iterator.next();
            logger.debug(aUser);
        }
    }

    /*@Test//测试resultType自动映射
    public void testGetUserListByResultType(){
        User user=new User();
        user.setUserName("孙");
        user.setUserRole(3);
        List<User> userList=mapper.getUserListByResultType(user);
        Iterator<User> iterator=userList.iterator();
        while(iterator.hasNext()){
            User aUser=iterator.next();
            logger.debug("UserName====>"+aUser.getUserName()+"\tID====>"+aUser.getId()+"\troleName====>"+aUser.getUserRoleName());
        }
    }*/
    /*@Test//测试resultMap手动映射
    public void testGetUserListByResultMap(){
        User user=new User();
        user.setUserName("邓");
        user.setUserRole(3);
        List<User> userList=mapper.getUserListByResultMap(user);
        Iterator<User> iterator=userList.iterator();
        while(iterator.hasNext()){
            User aUser=iterator.next();
            logger.debug(aUser);
        }
    }*/

    @Test//测试添加用户
    public void testAdd(){
        int count=0;
        try {
            User user=new User();
            user.setUserName("孙二娘");
            user.setUserCode("testUser001");
            user.setUserPassword("1234567");
            Date birthday=new SimpleDateFormat("yyyy-MM-dd").parse("2018-1-25");
            user.setBirthday(birthday);
            user.setAddress("测试地址");
            user.setGender(1);
            user.setCreatedBy(1);
            user.setUserRole(3);
            user.setCreationDate(new Date());
            count=mapper.add(user);
        } catch (ParseException e) {
            if(session!=null){
                session.rollback();
                count=0;
            }
            logger.error(e);
        } finally {
            logger.debug("添加用户测试：count====>"+count);
        }
    }

    @Test//测试修改用户的方法
    public void testModify(){
        int count=0;
        try {
            User user=new User();
            user.setUserName("扈三娘");
            user.setId(25);
            user.setUserCode("testUser001");
            user.setUserPassword("1234567");
            Date birthday=new SimpleDateFormat("yyyy-MM-dd").parse("2013-1-25");
            user.setBirthday(birthday);
            user.setAddress("修改后地址");
            user.setGender(0);
            user.setUserRole(3);
            user.setModifyBy(1);
            user.setModifyDate(new Date());
            count=mapper.modify(user);
        } catch (ParseException e) {
            if(session!=null){
                session.rollback();
                count=0;
            }
            logger.error(e);
        } finally {
            logger.debug("添加用户测试：count====>"+count);
        }
    }

    @Test
    public void testUpdatePwd(){
        String pwd="666666";
        Integer id=25;
        int count=0;

        try {
            count=mapper.updatePwd(pwd,id);
        } catch (Exception e) {
            if(session!=null){
                session.rollback();
                count=0;
            }
            e.printStackTrace();
        }finally {
            logger.debug("count=====>"+count);
        }


    }

    @Test//测试删除方法
    public void testDeleteUserById(){
        int count=0;
        try {
            count=mapper.deleteUserById(25);
        } catch (Exception e) {
            if(session!=null){
                session.rollback();
            }
            logger.error(e);
        } finally {
            logger.debug("删除用户count=====>"+count);
        }
    }

    @Test//测试association 内嵌一个对象
    public void terstGetUserListByRoleId(){
        List<User> userList=mapper.getUserListByRoleId(3);
        Iterator<User> iterator=userList.iterator();
        while(iterator.hasNext()){
            User aUser=iterator.next();
            logger.debug("userName===>"+aUser.getUserName()+"\tuserRoleName"+aUser.getRole().getRoleName()
                    +"\tuserRoleCode"+aUser.getRole().getRoleCode());
        }
    }

    @Test//测试collection
    public void testGetAddressListByUserId(){
        //遍历用户
        List<User> userList=mapper.getAddressListByUserId(1);
        Iterator<User> iterator=userList.iterator();
        while(iterator.hasNext()){
            User aUser=iterator.next();
            logger.debug("userName===>"+aUser.getUserName()+"\n");
            //遍历用户地址
            List<Address> addressesList=aUser.getAddressList();
            Iterator<Address> addressIterator=addressesList.iterator();
            while(addressIterator.hasNext()){
                Address address=addressIterator.next();
                logger.debug("address===>id:"+address.getId()
                        +",concat:"+address.getContact()
                        +",addressDesc:"+address.getAddressDesc()
                        +",tel:"+address.getTel()
                        +",postCode:"+address.getPostCode());

            }
        }

    }

    @Test//测试if条件判断
    public void testGetUserListTestIf(){
        User user=new User();
        //user.setUserRole(3);
        user.setUserName("赵");
        List<User> userList=mapper.getUserListTestIf(user);
        Iterator<User> userIterator=userList.iterator();
        while(userIterator.hasNext()){
            User u=userIterator.next();
            logger.debug(u.getUserName()+"\t"+u.getUserRoleName());
        }

    }

    @Test//单表查询，if条件判断测试
    public void testGetUserListTestIfOne(){
        User user=new User();
        //user.setUserRole(3);
        user.setUserName("赵");
        List<User> userList=mapper.getUserListTestIfOne(user);
        Iterator<User> userIterator=userList.iterator();
        while(userIterator.hasNext()){
            User u=userIterator.next();
            logger.debug(u);
        }
    }

    @Test//where简化条件怕判断
    public void testGetUserListTestWhere(){
        User user=new User();
        //user.setUserRole(3);
        //user.setUserName("赵");
        List<User> userList=mapper.getUserListTestWhere(user);
        Iterator<User> userIterator=userList.iterator();
        while(userIterator.hasNext()){
            User u=userIterator.next();
            logger.debug(u);
        }
    }

    @Test//if+trim实现多条件判断
    public void testGetUserListTestIfAndTrim(){
        User user=new User();
        user.setUserRole(3);
        //user.setUserName("赵");
        List<User> userList=mapper.getUserListTestIfAndTrim(user);
        Iterator<User> userIterator=userList.iterator();
        while(userIterator.hasNext()){
            User u=userIterator.next();
            logger.debug(u);
        }
    }

    @Test//if+set改造修改操作
    public void testModifyTestIfAndSet(){
        int count=0;
        try {
            User user=new User();
            user.setUserPassword("7777777");
            user.setModifyDate(new Date());
            user.setId(15);
            count=mapper.modifyTestIfAndSet(user);
        } catch (Exception e) {
            if (session!=null){
                session.rollback();
                count=0;
            }
            logger.error(e);
        } finally {
            logger.debug("修改用户记录数："+count);
        }
    }
    @Test//if+set改造修改操作
    public void testModifyTestIfAndTrim(){
        int count=0;
        try {
            User user=new User();
            user.setUserPassword("7777777");
            user.setModifyDate(new Date());
            user.setId(2);
            count=mapper.modifyTestIfAndTrim(user);
        } catch (Exception e) {
            if (session!=null){
                session.rollback();
                count=0;
            }
            logger.error(e);
        } finally {
            logger.debug("修改用户记录数："+count);
        }
    }

    @Test//foreach迭代测试,数组入参
    public void testGetUserListByRoleId_foreach_array(){
        List<User> userList= null;
        try {
            Integer[] roleIds=new Integer[]{1,2};
            userList = mapper.getUserListByRoleId_foreach_array(roleIds);
        } catch (Exception e) {
            if(session!=null){
                session.rollback();
            }
        }
        Iterator<User> userIterator=userList.iterator();
        while(userIterator.hasNext()){
            User u=userIterator.next();
            logger.debug(u);
        }
    }
    @Test//foreach迭代测试，List入参
    public void testGetUserListByRoleId_foreach_list(){
        List<Integer> roleList=new ArrayList<Integer>();
        roleList.add(1);
        roleList.add(2);
        List<User> userList=mapper.getUserListByRoleId_foreach_list(roleList);
        Iterator<User> userIterator=userList.iterator();
        while(userIterator.hasNext()){
            User u=userIterator.next();
            logger.debug(u);
        }
    }
    @Test//foreach迭代测试，Map入参
    public void testGetUserListByConditionMap_foreach_map(){
        List<User> userList= null;
        try {
            Map<String,Object> conditionMap=new HashMap<String, Object>();
            List<Integer> roleList=new ArrayList<Integer>();
            roleList.add(1);
            roleList.add(2);
            conditionMap.put("gender",1);
            conditionMap.put("roleIds",roleList);
            userList = mapper.getUserListByConditionMap_foreach_map(conditionMap);
        } catch (Exception e) {
            if(session!=null){
                session.rollback();
            }
            logger.error(e);
        }
        Iterator<User> userIterator=userList.iterator();
        while(userIterator.hasNext()){
            User u=userIterator.next();
            logger.debug(u);
        }
    }

    @Test//测试choose元素
    public void testGetUserList_choose(){
        String userName="赵";
        String userCode="";
        Integer userRole=2;
        List<User> userList=null;
        try {
            Date creationDate=new SimpleDateFormat("yyyy-MM-dd").parse("2016-01-01");
            userList=mapper.getUserList_choose(userName,userCode,userRole,creationDate);
        } catch (ParseException e) {
            if(session!=null)
                session.rollback();
            e.printStackTrace();
        }
        Iterator<User> userIterator=userList.iterator();
        while(userIterator.hasNext()){
            User u=userIterator.next();
            logger.debug(u);
        }
    }

    @Test
    public void testGetUserListByPage(){
        String userName="";
        Integer roleId=null;
        Integer pageSize=5;
        Integer currentPage=0;
        List<User> userList= null;
        try {
            userList = mapper.getUserListByPage(userName,roleId,currentPage,pageSize);
        } catch (Exception e) {
            if(session!=null)
                session.rollback();
            logger.error(e);
        }
        Iterator<User> userIterator=userList.iterator();
        while(userIterator.hasNext()){
            User u=userIterator.next();
            logger.debug("分页展示======》id:"+u.getId()+
            "and userCode"+u.getUserCode()+
            "and userName"+u.getUserName()+
            "and userRoleName"+u.getUserRoleName()+
            "and phone"+u.getPhone()+
            "and gender"+u.getGender()+
            "and creationDate"+u.getCreationDate());
        }
    }
}
