package com.njust.lucene.service;


import com.njust.lucene.pojo.IndexModel;
import com.njust.lucene.util.PropertiesUtil;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 提供搜索服务*/
public class LuceneService {
    /**
     * 返回的最大的搜索结果数量*/
    private static int HIT_MAX = 10;

    /**
     * index的相对地址*/
    private static String INDEX_DIR = "indexDir" ;

    public  List<IndexModel> search(String q) throws Exception{
        String indexDir = PropertiesUtil.get(INDEX_DIR,INDEX_DIR);
        return search(indexDir, q);
    }
    private static List<IndexModel> search(String indexDir, String q)throws Exception{
        List<IndexModel> result = new ArrayList<>();
        Directory dir = FSDirectory.open(Paths.get(indexDir));
        // todo 近实时搜索
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher is = new IndexSearcher(reader);
        // Analyzer analyzer = new StandardAnalyzer(); // 标准分词器
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
        //查询解析器：使用和索引同样的语言分析器 -- 这里只对 title 进行检索
//
//        QueryParser parser = new QueryParser("title", analyzer);
//        Query query = parser.parse(q);
        // 多域查询
        String[] fields = {"title", "keyWord"};
        Map<String, Float> boost = new HashMap<>();
        boost.put("title", 2.0f);
        boost.put("keyWord", 1.0f);
        MultiFieldQueryParser multiFieldQueryParser = new MultiFieldQueryParser(fields, analyzer, boost);
        Query query = multiFieldQueryParser.parse(q);
        long start = System.currentTimeMillis();
        // 保存搜索结果
        TopDocs hits = is.search(query, HIT_MAX);
        long end = System.currentTimeMillis();
        System.out.println("Search "+q+" ，Time "+(end-start)+" ms！"+" And got "+hits.scoreDocs.length+" hits");

        QueryScorer scorer = new QueryScorer(query);
        Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
        // 高亮显示匹配字符
        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color = 'blue'>","</font></b>");
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);
        highlighter.setTextFragmenter(fragmenter);
        for(ScoreDoc scoreDoc:hits.scoreDocs){
            IndexModel indexModel = new IndexModel();
            Document doc = is.doc(scoreDoc.doc);
            indexModel.setId(doc.get("id"));
            String keyWord = doc.get("keyWord");
            if (keyWord != null) {
                String hlKeyWord = highlighter.getBestFragment(analyzer, "keyWord", keyWord);
                if (hlKeyWord != null) {
                    indexModel.setKeyWord(hlKeyWord);
                }else {
                    indexModel.setKeyWord(keyWord);
                }
            }
            String title = doc.get("title");
            if(title != null){
                String hlTitle = highlighter.getBestFragment(analyzer, "title", title);
                if (hlTitle != null) {
                    indexModel.setTitle(hlTitle);
                }else {
                    indexModel.setTitle(title);
                }
            }
            indexModel.setFieldCode(doc.get("fieldCode"));
            indexModel.setProjectCode(doc.get("projectCode"));
            indexModel.setOrganization(doc.get("organization"));
            indexModel.setFunds(doc.get("funds"));
            indexModel.setYear(doc.get("year"));
            result.add(indexModel);
        }
        reader.close();
        return result;
    }

    public static void main1(String[] args) throws ParseException {
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
        //查询解析器：使用和索引同样的语言分析器 -- 这里只对 title 进行检索
        String[] fields = {"title", "keyWord"};
        Map<String, Float> boost = new HashMap<>();
        boost.put("title", 2.0f);
        boost.put("keyWord", 1.0f);
        MultiFieldQueryParser multiFieldQueryParser = new MultiFieldQueryParser(fields, analyzer, boost);
        Query query = multiFieldQueryParser.parse("机器学习");
        System.out.println(query.toString());
    }


}
