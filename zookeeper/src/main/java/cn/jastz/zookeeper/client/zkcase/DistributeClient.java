package cn.jastz.zookeeper.client.zkcase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class DistributeClient
{
    private static String connectString = "hadoop102:2181,hadoop103:2181,hadoop104:2181";
    private static int sessionTimeout = 2000;
    private ZooKeeper zk = null;
    private String parentNode = "/servers";
  
    // 创建到zk的客户端连接
    public void getConnect() throws IOException {
       zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
          @Override
          public void process(WatchedEvent event) {
             // 再次启动监听
             try {
                getServerList();
             } catch (Exception e) {
                e.printStackTrace();
             }
          }
       });
  
    }
  
    // 获取服务器列表信息
    public void getServerList() throws Exception {
       // 1获取服务器子节点信息，并且对父节点进行监听
       List<String> children = zk.getChildren(parentNode, true);
         // 2存储服务器信息列表
  
       ArrayList<String> servers = new ArrayList<>();
  
         // 3遍历所有节点，获取节点中的主机名称信息
       for (String child : children) {
          byte[] data = zk.getData(parentNode + "/" + child, false, null);
          servers.add(new String(data));
       }
         // 4打印服务器列表信息
       System.out.println(servers);
    }
    // 业务功能
    public void business() throws Exception{
       System.out.println("client is working ...");
      Thread.sleep(Long.MAX_VALUE);
    }
  
    public static void main(String[] args) throws Exception {
       // 1获取zk连接
       DistributeClient client = new DistributeClient();
       client.getConnect();
       // 2获取servers的子节点信息，从中获取服务器信息列表
       client.getServerList();
       // 3业务进程启动
       client.business();
    }

}