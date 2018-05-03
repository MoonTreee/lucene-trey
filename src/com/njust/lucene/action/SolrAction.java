package com.njust.lucene.action;

import com.njust.lucene.pojo.PageBean;
import com.njust.lucene.pojo.IndexModel;
import com.njust.lucene.service.SolrService;

/**
 * Created by Trey Wang on 2018/5/2.
 */
public class SolrAction {
    private static final long serialVersionUID = 1L;
    private String keyword;
    private String field;
    private SolrService solrService;
    private PageBean<IndexModel> page;
    private final Integer pageSize=5;
    private int pageIndex;

/*
    *//**
     * 从索引中检索关键词
     *//*
    public String list() throws Exception {
        page = solrService.findByIndex(keyword,field,pageIndex,pageSize);
        return SUCCESS;
    }



    public String commitRamIndex() throws Exception {
        solrService.commitRamIndex();
        return LIST;
    }


    public String commitDBIndex() throws Exception {
        solrService.commitDBIndex();
        return LIST;
    }


    public String reCreCommitIndex() throws Exception {
        solrService.updateReconstructorIndex();
        return LIST;
    }


    public String deleteIndex() throws Exception {
        solrService.deleteIndex();
        return LIST;
    }

    //get set
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public String getField() {
        return field;
    }
    public void setField(String field) {
        this.field = field;
    }
    public int getPageIndex() {
        return pageIndex;
    }
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
    public PageBean<IndexModel> getPage() {
        return page;
    }
    public void setPage(PageBean<IndexModel> page) {
        this.page = page;
    }*/
}
