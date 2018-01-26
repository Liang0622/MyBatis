package com.vic;

import com.vic.dao.CountryMapper;
import com.vic.pojo.Country;
import com.vic.pojo.Provincial;
import com.vic.util.SessionFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;

public class CountryTest {
    private Logger logger=Logger.getLogger(CountryTest.class);
    SqlSession session=null;
    CountryMapper mapper=null;

    @Before
    public void getSession(){
        //通过工具类获得SqlSession的实例
        session= SessionFactory.getSession();
        //使用mapper动态代理
        mapper=session.getMapper(CountryMapper.class);
    }
    @After
    public void sessionCommitAndClose(){
        if(session!=null){
            session.commit();
            session.close();
        }
    }

    //@Test
    public void testSelectCountryByCid(){
        Integer cid=1;
        Country country=null;
        try {
            country=mapper.selectCountryByCid(cid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.debug("国家编号："+country.getcId()+"\t国家名称："+country.getcName());
        HashSet<Provincial> provincials=(HashSet<Provincial>) country.getProvincials();
        Iterator<Provincial> provincialIterator=provincials.iterator();
        while(provincialIterator.hasNext()){
            Provincial provincial=provincialIterator.next();
            logger.debug("省会编号："+provincial.getpId()+"\t省会名称："+provincial.getpName());
        }

    }
    @Test
    public void testSelectCountryByCid_delay(){
        Integer cid=1;
        Country country=null;
        try {
            country=mapper.selectCountryByCid_delay(cid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.debug("国家编号："+country.getcId()+"\t国家名称："+country.getcName());
        logger.debug(country.getProvincials());
        HashSet<Provincial> provincials=(HashSet<Provincial>) country.getProvincials();
        Iterator<Provincial> provincialIterator=provincials.iterator();
        while(provincialIterator.hasNext()){
            Provincial provincial=provincialIterator.next();
            logger.debug("省会编号："+provincial.getpId()+"\t省会名称："+provincial.getpName());
        }
    }
}
