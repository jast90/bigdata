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
        // configuration.set("df.defaultFS", "hdfs://hadoop102:9000");//  不指定用户会失败
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:9000"),configuration,"jast");
        Path path = new Path("/", "test");
        assertTrue(fs.mkdirs(path));
        fs.close();

    }

    @Test
    public void copyFromLocalFile() throws IOException, InterruptedException, URISyntaxException {
        Configuration configuration = new Configuration();
        // configuration.set("df.defaultFS", "hdfs://hadoop102:9000");
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:9000"),configuration,"jast");
        fs.copyFromLocalFile(new Path("/Users/zhangzhiwen/Desktop/小学三年级数学人教版/7.三年级数学(人教版)《第7课 口算除法（一）》.mp4")
        , new Path("/jast"));
    
        fs.deleteOnExit(new Path("/jast/7.三年级数学(人教版)《第7课 口算除法（一）》.mp4"));
        fs.close();
    }

    @Test
    public void deleteOnExit() throws IOException, InterruptedException, URISyntaxException {
        Configuration configuration = new Configuration();
        // configuration.set("df.defaultFS", "hdfs://hadoop102:9000");
        FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:9000"),configuration,"jast");
        boolean result = fs.deleteOnExit(new Path("/jast/7.三年级数学(人教版)《第7课 口算除法（一）》.mp4"));
        fs.close();

        assertTrue(result);
    }
}
