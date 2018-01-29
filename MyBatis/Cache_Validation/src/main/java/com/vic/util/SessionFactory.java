package com.vic.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SessionFactory {
    /**
     * 创建需要单例的对象
     */
    private static SqlSessionFactory sqlSessionFactory;
    /**
     * 私有化构造函数
     */
    private SessionFactory(){

    }
    /**
     * 提供公共访问接口
     */
    public static SqlSession getSession(){
        try {
            InputStream is= Resources.getResourceAsStream("mybatis_config.xml");
            if(sqlSessionFactory==null){
                sqlSessionFactory=new SqlSessionFactoryBuilder().build(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlSessionFactory.openSession();
    }
}
