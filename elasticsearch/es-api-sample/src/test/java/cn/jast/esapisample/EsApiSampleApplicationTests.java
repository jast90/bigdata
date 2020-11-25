package cn.jast.esapisample;

import java.io.IOException;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;

import cn.jast.esapisample.csdn.modal.Article;
import cn.jast.esapisample.csdn.service.CsdnSpilder;
import cn.jast.esapisample.util.Jsons;

@SpringBootTest(classes = { EsApiSampleApplication.class })
public class EsApiSampleApplicationTests {

    @Autowired
    private CsdnSpilder csdnSpilder;

    @Autowired
    private  ElasticsearchOperations elasticsearchOperations;

    @Test
    public void totalPage() throws IOException {
        // System.out.println(csdnSpilder.totalPage());
    }

    @Test
    public void getArticleListItems() throws IOException {
        csdnSpilder.getArticleListItems(9l);
    }

    @Test
    public void saveCsdnArticleToES() throws IOException {
        long start = System.currentTimeMillis(),end;
        List<Article> articles = csdnSpilder.getArticleListItems(9l);
        end = System.currentTimeMillis();
        System.out.println("获取文章列表消耗时间"+(end-start));
        start = System.currentTimeMillis();
        for(Article article:articles){
            csdnSpilder.getById(article);
        }
        end = System.currentTimeMillis();
        System.out.println("获取文章内容消耗时间"+(end-start));

        elasticsearchOperations.indexOps(Article.class);
        elasticsearchOperations.save(articles);
    }


    @Test
    public void search(){
        String keyword = "mysql";

        QueryBuilder queryBuilder = QueryBuilders.termQuery("content", keyword);
        NativeSearchQuery query = new NativeSearchQuery(queryBuilder);
        SearchHits<Article> hits = elasticsearchOperations.search(query, Article.class);
        List<SearchHit<Article>> list = hits.getSearchHits();
        list.forEach(i->{
            System.out.println(i.getContent().getUrl());
            System.out.println(i.getContent().getTitle());
        });
    }

    
}
