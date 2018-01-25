package com.vic.util;

import com.vic.dao.user.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactorySingleUtil {
    //创建单例模式的唯一实例
    private static Logger logger=Logger.getLogger(SqlSessionFactorySingleUtil.class);
    private static SqlSessionFactory sqlSessionFactory;//懒汉式
    //私有化构造函数
    private SqlSessionFactorySingleUtil(){

    }
    //提供共有的接口
    public static synchronized SqlSession getSqlSession(){
        String resource="mybatis_config.xml";
        try {
            InputStream is= Resources.getResourceAsStream(resource);
            if(sqlSessionFactory==null){
                //创建sqlSessionFactory
                sqlSessionFactory=new SqlSessionFactoryBuilder().build(is);
                //sqlSessionFactory.getConfiguration().addMapper(UserMapper.class);
            }

        } catch (IOException e) {
            logger.error(e);
        }
        return sqlSessionFactory.openSession(); //默认不提交autoCommit=false
    }


}
