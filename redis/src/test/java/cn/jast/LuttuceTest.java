package cn.jast;

import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;

public class LuttuceTest {

    public static void main(String[] args) {
        RedisClusterClient redisClusterClient = RedisClusterClient.create("redis://localhost:7005");
        StatefulRedisClusterConnection<String, String> connection = redisClusterClient.connect();
        RedisAdvancedClusterCommands<String, String> syncCommands = connection.sync();
        try{

            syncCommands.set("hello","word");
            System.out.println(syncCommands.get("foo"));
            System.out.println(syncCommands.get("hello"));
            System.out.println(syncCommands.keys("*"));//当集群中有一个节点下线时，该命令会报异常

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            connection.close();
            redisClusterClient.shutdown();
        }

    }
}
