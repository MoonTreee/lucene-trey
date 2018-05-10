package com.njust.lucene.service;


import com.njust.lucene.dao.IndexDao;
import com.njust.lucene.domain.IndexData;
import com.njust.lucene.util.PropertiesUtil;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class IndexService {


    private Directory dir;
    private IndexDao indexDao;

    public IndexDao getIndexDao() {
        return indexDao;
    }

    public void setIndexDao(IndexDao indexDao) {
        this.indexDao = indexDao;
    }

    /**
     * ��ȡIndexWriterʵ��
     *
     * @return
     * @throws Exception
     */
    private IndexWriter getWriter() throws Exception {
        //Analyzer analyzer  =  new StandardAnalyzer(); // ��׼�ִ���
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        return new IndexWriter(dir, iwc);
    }

    /**
     * ��������
     *
     * @throws
     */
    public void index() {
        String indexDir = PropertiesUtil.get("indexDir", "indexDir");
        System.out.println("index ��ʼ�������� in " + indexDir);
        List<IndexData> indexList = indexDao.findAll();
        try {
            index(indexDir, indexList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ��������
     *
     * @param indexDir  ������Ŀ¼
     * @param indexList ��������������
     */
    private void index(String indexDir, List<IndexData> indexList) throws Exception {
        dir = FSDirectory.open(Paths.get(indexDir));
        IndexWriter writer = getWriter();
        for (IndexData indexData : indexList) {
            Document doc = new Document();
            // StringField�����ִ� �� TextField ���ִ�
            doc.add(new StringField("id", indexData.getId(), Field.Store.YES));
            doc.add(new StringField("fieldCode", indexData.getFieldCode(), Field.Store.YES));
            doc.add(new TextField("title", indexData.getTitle(), Field.Store.YES));
            doc.add(new TextField("keyWord", indexData.getKeyWord(), Field.Store.YES));
            doc.add(new StringField("projectCode", indexData.getProjectCode(), Field.Store.YES));
            doc.add(new TextField("organization", indexData.getOrganization(), Field.Store.YES));
            doc.add(new StringField("funds", indexData.getFunds(), Field.Store.YES));
            doc.add(new StringField("year", indexData.getYear(), Field.Store.YES));
            writer.addDocument(doc); // ����ĵ�
        }
        writer.close();
    }

    /**
     * ɾ����������
     */
    public void deleteAll() throws Exception {
        String indexDir = PropertiesUtil.get("indexDir", "indexDir");
        System.out.println(indexDir);
        dir = FSDirectory.open(Paths.get(indexDir));
        IndexWriter writer = getWriter();
        writer.deleteAll();
        writer.commit();
        writer.close();
    }


    /**
     * ��ȡ��Ҫ������������Դ�ļ� : ���ı���
     *
     * @param path ��Ҫ��ȡ���ļ�·��
     */
    private static List<String[]> readFromFile(String path) {
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

    public int count() {
        return indexDao.count();
    }



}
