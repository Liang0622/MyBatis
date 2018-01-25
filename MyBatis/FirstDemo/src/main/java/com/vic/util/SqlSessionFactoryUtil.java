package com.vic.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryUtil {
    //01.创建需要单例的静态对象
    private  static SqlSessionFactory sessionFactory;

    //02.私有化构造
    private SqlSessionFactoryUtil(){}

    //03.提供对外访问的接口
    public static synchronized SqlSession getSession(){
        try {
            //把xml文件读取到内存中 并且返回一个输入流对象
            InputStream stream = Resources.getResourceAsStream("mybatis_config.xml");
            if (sessionFactory==null){
                //创建SqlSessionFactory
                sessionFactory= new SqlSessionFactoryBuilder().build(stream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         * 为什么不需要关闭输入流
         * 01.build(stream)  使用了输入流
         * 02.ctrl+左键 点击build
         *
         *  public SqlSessionFactory build(InputStream inputStream) {
         return this.build((InputStream)inputStream, (String)null, (Properties)null);
         }
         03.继续点击build
         04.inputStream.close();  底层以经关闭了
         */

        return  sessionFactory.openSession();  //返回一个 SqlSession的实现类===》DefaultSqlSession
    }
}
