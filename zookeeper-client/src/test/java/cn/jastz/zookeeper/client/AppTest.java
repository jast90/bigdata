package cn.jastz.zookeeper.client;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private ZooKeeper zkClient = null;
    private String connectString = "hadoop102:2181,hadoop103:2181,hadoop104:2181";
    private static int sessionTimeout = 2000;

    @Before
    public void inti() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {

            @Override
            public void process(WatchedEvent event) {
                System.out.println(event.getType() + "--" + event.getPath());
                try {
                    zkClient.getChildren("/", true);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @After
    public void after() throws InterruptedException {
        if (zkClient != null) {
            zkClient.close();
        }
    }

    /**
     * 创建子节点 通过CreateMode 指定节点类型 create -e -s
     * 
     * @throws InterruptedException
     * @throws KeeperException
     */
    @Test
    public void create() throws KeeperException, InterruptedException {
        String nodeCreated = zkClient.create("/jast", "hello".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    @Test
    public void getData() throws KeeperException, InterruptedException, UnsupportedEncodingException
    {
        Stat stat = new Stat();
        byte[] result = zkClient.getData("/jast", true , stat);
        String string = new String(result,"utf-8");
        System.out.println(string);
    }

    /**
     * 获取子节点
     * ls 
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void getChildren() throws KeeperException, InterruptedException {
        List<String> children = zkClient.getChildren("/", true);
        children.forEach(child->{
            System.out.println(child);
        });
        assertTrue(children.size()>0);
        
    }

    /**
     * 
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void exist() throws KeeperException, InterruptedException {
       Stat stat = zkClient.exists("/eclipse", false);
       assertTrue(stat==null);
    }
}
