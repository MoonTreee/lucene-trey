package com.njust.lucene.dao;

import java.util.List;

import com.njust.lucene.pojo.TempIndex;
import org.springframework.stereotype.Repository;



@Repository
public class TempIndexDao extends BaseDao<TempIndex, String>{

	public void deleteAll() {
		List<TempIndex> tempIndexList=this.findAll();
		for (TempIndex tempIndex : tempIndexList) {
			this.del(tempIndex);
		}
	}

}
