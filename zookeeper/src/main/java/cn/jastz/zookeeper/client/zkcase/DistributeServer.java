package cn.jastz.zookeeper.client.zkcase;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;

public class DistributeServer {
    private static String connectString = "hadoop102:2181,hadoop103:2181,hadoop104:2181";
    private static int sessionTimeout = 2000;
    private ZooKeeper zkClient = null;
    private String parentNode = "/servers";

    public void getConnection() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {

            @Override
            public void process(WatchedEvent event) {

            }
        });
    }

    public void registServer(String hostname)
            throws UnsupportedEncodingException, KeeperException, InterruptedException
    {
        String create = zkClient.create(String.format("%s/server", parentNode),hostname.getBytes("utf-8") , Ids.OPEN_ACL_UNSAFE
        , CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(hostname+"is online "+create);
    }

    public void business(String hostname) throws InterruptedException
    {
        System.out.println(hostname+" is working...");
        Thread.sleep(Long.MAX_VALUE);
    }

    public static void main(String[] args) throws KeeperException, InterruptedException, IOException {
        DistributeServer server = new DistributeServer();
        server.getConnection();
        server.registServer("hadoop102");
        server.business("hadoop102");

    }
    

}