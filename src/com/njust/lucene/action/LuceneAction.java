package com.njust.lucene.action;

import com.njust.lucene.ov.IndexModel;
import com.njust.lucene.service.IndexService;
import com.njust.lucene.service.LuceneService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.util.List;


//@Scope("prototype")
//@Namespace("/lucene")
//@Result(name="list",type="redirectAction",location="search.action")
public class LuceneAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private String keyword;
	private String field;

	public LuceneService getLuceneService() {
		return luceneService;
	}

	public void setLuceneService(LuceneService luceneService) {
		this.luceneService = luceneService;
	}

	private LuceneService luceneService;
	private IndexService indexService;

	public IndexService getIndexService() {
		return indexService;
	}

	public void setIndexService(IndexService indexService) {
		this.indexService = indexService;
	}

	@Override
	public String list() throws Exception {
		List<IndexModel> luceneList = luceneService.search(keyword);
		ActionContext.getContext().put("luceneList", luceneList);
		return SUCCESS;
	}

	public String index() {
		indexService.index();
		return "INDEX";
	}
	
//	@Action(value="commitRamIndex")
//	public String commitRamIndex() throws Exception {
//		luceneService.commitRamIndex();
//		return LIST;
//	}
//
//	@Action(value="commitDBIndex")
//	public String commitDBIndex() throws Exception {
//		luceneService.commitDBIndex();
//		return LIST;
//	}
//
//	@Action(value="reCreCommitIndex")
//	public String reCreCommitIndex() throws Exception {
//		luceneService.updateReconstructorIndex();
//		return LIST;
//	}
//
//	@Action(value="deleteIndex")
//	public String deleteIndex() throws Exception {
//		luceneService.deleteIndex();
//		return LIST;
//	}

	@Override
	public String execute() throws Exception {
		System.out.println(keyword);
		return list();
	}
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
}
