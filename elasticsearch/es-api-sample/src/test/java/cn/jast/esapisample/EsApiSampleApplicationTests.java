package cn.jast.esapisample;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EsApiSampleApplicationTests {

	protected RestHighLevelClient client;

	@BeforeEach
	public void setup() {
		client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
	}

	@AfterEach
	public void after() throws IOException {
		client.close();
	}
}
