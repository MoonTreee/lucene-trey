package com.njust.lucene.service;

import com.njust.lucene.dao.MessageDao;
import com.njust.lucene.pojo.IndexField;
import com.njust.lucene.util.SolrContext;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

import java.io.IOException;

/**
 * Created by Trey Wang on 2018/5/2.
 */
public class SolrService {

    private MessageDao messageDao;


    public void commitRamIndex(){
        try {
            SolrContext.getSolrClient().commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 重构所有的索引
     * 把数据库中所有的记录查出来，构建IndexField添加到索引中
     */
  /*  public void updateReconstructorIndex(){
        try {
            //1、先删除所有的索引
            SolrContext.getSolrClient().deleteByQuery("*:*");

            //2、取出所有数据，构建indexField
            List<Message> messageList=messageDao.findAll();
            indexMessageList(messageList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /**
     * 为所有的数据构建索引
     */
/*
    private void indexMessageList(List<Message> messageList) {
        for (Message message : messageList) {
            IndexField indexField=IndexUtil.message2IndexField(message);
            addIndex(indexField);
        }
    }
*/

    /**
     * 添加索引
     */
    public void addIndex(IndexField indexField) {
        try {
            //1、添加索引到solr
            HttpSolrClient solrClient = SolrContext.getSolrClient();
            solrClient.addBean(indexField);
            //2、优化索引
            solrClient.optimize();
            //2、提交索引
            SolrContext.getSolrClient().commit();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 检索
     * @param field

    public PageBean<IndexModel> findByIndex(String keyword, String field, Integer start, int pageSize) throws SolrServerException {
        if(keyword==null)keyword="*";
        if(field==null)field="*";
        SolrQuery solrQuery=new SolrQuery(field+":"+keyword);
        //solrQuery.addSortField("last_modified", SolrQuery.ORDER.desc);
        solrQuery.setHighlight(true)//设置显示高亮
                .setHighlightSimplePre("<span class='highlighter'>")
                .setHighlightSimplePost("</span>")//设置高亮的样式
                .setParam("hl.fl", "title,content,description")//设置高亮的域
//		.setParam("hl.fl", "title,content,summary")//设置高亮的域
                .setStart(start)//设置分页
                .setRows(pageSize);

        QueryResponse queryResponse = null;//solrQuery是SolrParams的子类
        try {
            queryResponse = SolrContext.getSolrClient().query(solrQuery);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //第一个map的key是文档document的ID，第二个map的key'是要高亮显示的field
        Map<String,Map<String,List<String>>> map=queryResponse.getHighlighting();
        SolrDocumentList solrDocumentList=queryResponse.getResults();
        PageBean<IndexModel> page = new PageBean<IndexModel>(new Long(solrDocumentList.getNumFound()).intValue());
        page.setPageSize(pageSize);
        page.setStartRow(start);
        List<IndexModel> datas=new ArrayList<IndexModel>();
        for (SolrDocument sd : solrDocumentList) {
            String id = (String) sd.getFieldValue("id");
            String title = (String) sd.getFieldValue("title");
            String summary = (String) sd.getFieldValue("description");
            List<String> content = (List<String>) sd.getFieldValue("content");//multiValued="true"的field返回List

            IndexModel indexModel=new IndexModel();
            indexModel.setTitle(title);
            indexModel.setSummary(summary);
//			Map<String,List<String>> glMap=map.get(id);
//			List<String> titleList = glMap.get("title");
//			if(titleList!=null&&titleList.size()!=0){
//				indexModel.setTitle(titleList.get(0));//获取高亮
//			}
//			List<String> summaryList = glMap.get("description");
//			if(summaryList!=null&&summaryList.size()!=0){
//				indexModel.setSummary(summaryList.get(0));//获取高亮
//			}
//			List<String> content = glMap.get("content");//获取高亮
            Date addTime = (Date) sd.getFieldValue("last_modified");
            indexModel.setId(id);
            indexModel.setContent(content.get(0));
            indexModel.setAddTime(addTime);
            datas.add(indexModel);
        }
        page.setResultList(datas);
        return page;
    }

     */

    /**
     * 删除索引
     */
    public void deleteIndex(IndexField indexField) {
        try {
            SolrContext.getSolrClient().deleteById(indexField.getId());
            SolrContext.getSolrClient().commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void commitDBIndex() {
        try {
            SolrContext.getSolrClient().commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void deleteIndex() {
        try {
            SolrContext.getSolrClient().deleteByQuery("*:*");
            SolrContext.getSolrClient().commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
