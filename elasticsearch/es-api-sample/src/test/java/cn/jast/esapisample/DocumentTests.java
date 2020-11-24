package cn.jast.esapisample;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Date;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.Test;

import cn.jast.esapisample.modal.Post;
import cn.jast.esapisample.util.Jsons;

/**
 * https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high-supported-apis.html
 */
public class DocumentTests extends EsApiSampleApplicationTests {
    private final String postIndex = "post";

    @Test
    public void index() throws IOException {
        IndexRequest request = new IndexRequest(postIndex);
        request.id("1");
        Post post = new Post();
        post.setDate(new Date());
        post.setUser("jast");
        post.setMessage("hello");
        request.source(Jsons.objectToJSON(post), XContentType.JSON);
        IndexResponse reponse = client.index(request, RequestOptions.DEFAULT);
        System.out.println(reponse);
    }

    @Test
    public void get() throws IOException {
        GetRequest getRequest = new GetRequest(postIndex, "1");
        // String[] includes = Strings.EMPTY_ARRAY;
        // String[] excludes = new String[]{"message"};
        // FetchSourceContext fetchSourceContext =
        //         new FetchSourceContext(true, includes, excludes);
        // getRequest.fetchSourceContext(fetchSourceContext); 

        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(getResponse.getSourceAsString());
    }

    @Test
    public void exists() throws IOException {
        GetRequest getRequest = new GetRequest("post","1");
        boolean exists  = client.exists(getRequest, RequestOptions.DEFAULT);
        assertTrue(exists);
    }

    @Test
    public void delete() throws IOException {
        DeleteRequest request = new DeleteRequest("post","1");
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        System.out.println(response);
    }

    @Test
    public void update() throws IOException {
        UpdateRequest request = new UpdateRequest("post","1");
        Post post = new Post();
        post.setDate(new Date());
        post.setUser("jast");
        post.setMessage("hello");
        post.setUpdateDate(new Date());
        System.out.println(Jsons.objectToJSON(post));
        request.doc(Jsons.objectToJSON(post),XContentType.JSON);
        UpdateResponse updateResponse = client.update(request, RequestOptions.DEFAULT);
        System.out.println(updateResponse);
    }
}
