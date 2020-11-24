package cn.jast.esapisample.javaClient;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.jupiter.api.Test;

/**
 * https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/_index_apis.html
 */
public class IndexTests extends BaseTests {

    public final static String indexName = "test";

    @Test
    public void create() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        request.settings(
            Settings.builder()
            .put("index.number_of_shards",3)
            .put("index.number_of_replicas",2)
        );
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.startObject("properties");{
                builder.startObject("message");{
                    builder.field("type","text");
                }
                builder.endObject();
            }
            builder.endObject();
        }
        builder.endObject();
        request.mapping(builder);
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        assertTrue(response.isAcknowledged());
    }

    @Test
    public void exists() throws IOException {
        GetIndexRequest getIndexRequest = new GetIndexRequest(indexName);
        getIndexRequest.local(false);
        getIndexRequest.humanReadable(true);
        getIndexRequest.includeDefaults(false);
        boolean exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        assertTrue(exists);
    }

    @Test
    public void delete() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest(indexName);
        AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
        assertTrue(response.isAcknowledged());
    }


}
