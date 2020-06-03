package cn.jast;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private static final String hadoopHost = "hdfs://hadoop100:9000";
    /**
     * Rigorous Test :-)
     * 
     * @throws IOException
     * @throws URISyntaxException
     * @throws InterruptedException
     */
    @Test
    public void mkdirs() throws IOException, InterruptedException, URISyntaxException
    {
        Configuration configuration = new Configuration();
        // configuration.set("df.defaultFS", hadoopHost);//  不指定用户会失败
        FileSystem fs = FileSystem.get(new URI(hadoopHost),configuration,"jast");
        Path path = new Path("/", "test");
        assertTrue(fs.mkdirs(path));
        fs.close();

    }

    @Test
    public void copyFromLocalFile() throws IOException, InterruptedException, URISyntaxException {
        Configuration configuration = new Configuration();
        // configuration.set("df.defaultFS", hadoopHost);
        FileSystem fs = FileSystem.get(new URI(hadoopHost),configuration,"jast");
        fs.copyFromLocalFile(new Path("/Users/zhangzhiwen/Desktop/天龙八部.jpeg")
        , new Path("/test/天龙八部.jpeg"));
    
        fs.close();
    }

    @Test
    public void deleteOnExit() throws IOException, InterruptedException, URISyntaxException {
        Configuration configuration = new Configuration();
        // configuration.set("df.defaultFS", hadoopHost);
        FileSystem fs = FileSystem.get(new URI(hadoopHost),configuration,"jast");
        boolean result = fs.deleteOnExit(new Path("/hbase"));
        fs.close();

        assertTrue(result);
    }
}
