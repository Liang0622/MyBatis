package com.vic;

import com.vic.dao.ProvincialMapper;
import com.vic.pojo.Country;
import com.vic.pojo.Provincial;
import com.vic.util.SessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProvincialTest {
    private Logger logger=Logger.getLogger(CountryTest.class);
    SqlSession session=null;
    ProvincialMapper mapper=null;

    @Before
    public void getSession(){
        //通过工具类获得SqlSession的实例
        session= SessionFactory.getSession();
        //使用mapper动态代理
        mapper=session.getMapper(ProvincialMapper.class);
    }
    @After
    public void sessionCommitAndClose(){
        if(session!=null){
            session.commit();
            session.close();
        }
    }

    @Test
    public void selectProvincialByPid(){
       Provincial provincial=mapper.selectProvincialByPid(2);
       logger.debug("pName:"+provincial.getpName()+"pId:"+provincial.getpId());
       //Country country=provincial.getCountry();
       logger.debug("国家编号:"+provincial.getCountry().getcId()+"国家名称:"+provincial.getCountry().getcName());
    }
}
