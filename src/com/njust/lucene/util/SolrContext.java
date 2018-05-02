package com.njust.lucene.util;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
/**
 * Created by Trey Wang on 2018/5/2.
 */
public class SolrContext {
    private static final String url="http://localhost:8080/solr";
    private static HttpSolrClient solrClient=null;


    static{
        try {
            solrClient=new HttpSolrClient(url);//创建solrServer
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HttpSolrClient getSolrClient(){
        return solrClient;
    }
}
