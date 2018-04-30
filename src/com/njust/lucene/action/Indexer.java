package com.njust.lucene.action;


import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Indexer {


	private Directory dir;
	
	/**
	 * 获取IndexWriter实例
	 * @return
	 * @throws Exception
	 */
	private IndexWriter getWriter()throws Exception{
		//Analyzer analyzer  =  new StandardAnalyzer(); // 标准分词器
		SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        return new IndexWriter(dir, iwc);
	}
	
	/**
	 * 生成索引
	 * @param indexDir
	 * @throws Exception
	 */
	private void index(String indexDir, List<String[]> datas)throws Exception{
		dir = FSDirectory.open(Paths.get(indexDir));
		IndexWriter writer = getWriter();
		for (String[] data : datas) {
			System.out.println(data[5]);
			Document doc = new Document();
			doc.add(new StringField("id",data[0],Field.Store.YES));
			doc.add(new StringField("index_word",data[2],Field.Store.YES));
			doc.add(new TextField("title",data[3],Field.Store.YES));
			doc.add(new TextField("key_word",data[5],Field.Store.YES));
			writer.addDocument(doc); // 添加文档
		}
		writer.close();
	}

	/**
	 * 读取文件
	 * @param path 需要读取的文件路径*/
	private static List<String[]> readFromFile(String path){
		List<String[]> Ids = new ArrayList<>();
		File file = new File(path);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				Ids.add(line.replace("\n", "").split("\t"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return Ids;
	}
	
	
//	public static void main(String[] args) throws Exception {
//		new Indexer().index("F:\\java\\lucene-trey\\resources\\index", readFromFile("F:\\java\\lucene-trey\\resources\\data.txt"));
//	}
	
}
