package com.njust.lucene.dao;

import com.njust.lucene.domain.IndexData;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * Created by Trey Wang on 2018/5/2.
 */
public class IndexDao extends HibernateDaoSupport {
    public List<IndexData> findAll(){
        String hql = "from IndexData";
        List<IndexData> result = null;
        result = this.getHibernateTemplate().find(hql);
        return result;
    }

    public int count(){
        String hql = "select count(*) from IndexData" ;
        List<Long> list = this.getHibernateTemplate().find(hql);
        if(list.size()>0){
            return list.get(0).intValue();
        }
        return 0;
    }

//    public static void main1(String[] args) {
//        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        IndexDao indexDao= (IndexDao) ctx.getBean("indexDao");
//        indexDao.count();
//    }
}
