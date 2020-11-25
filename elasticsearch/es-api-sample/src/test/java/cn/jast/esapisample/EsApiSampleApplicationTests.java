package cn.jast.esapisample;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Date;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import cn.jast.esapisample.modal.Post;
import cn.jast.esapisample.util.Jsons;

@SpringBootTest
public class EsApiSampleApplicationTests {

    @Autowired
    private RestHighLevelClient highLevelClient;
    
    @Autowired
    private  ElasticsearchOperations elasticsearchOperations;

    

    @Test
    public void deleteIndex(){
        assertTrue(elasticsearchOperations.indexOps(Post.class).delete());
    }

    @Test
    public void createIndex(){
        assertTrue(elasticsearchOperations.indexOps(Post.class).create());

        
    }

    @Test
    public void existsIndex(){
        assertTrue(elasticsearchOperations.indexOps(Post.class).exists());
    }

    @Test
    public void createDoc() throws IOException {
        IndexRequest request = new IndexRequest("spring-date");
        request.id("1");
        Post post = new Post();
        post.setDate(new Date());
        post.setUser("jast");
        post.setMessage("hello");
        request.source(Jsons.objectToJSON(post), XContentType.JSON);
        IndexResponse response = highLevelClient.index(request,RequestOptions.DEFAULT);
        System.out.println(response);

       
    }

    @Test
    public void createDoc1(){
        Post post = new Post();
        post.setId(1);
        post.setUser("jast");
        post.setMessage("Hello world");
        post = elasticsearchOperations.save(post);
        System.out.println(Jsons.objectToJSON(post));
    }
}
