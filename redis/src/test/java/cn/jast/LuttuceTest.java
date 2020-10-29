package cn.jast;

import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;

public class LuttuceTest {

    public static void main(String[] args) {
        RedisClusterClient redisClusterClient = RedisClusterClient.create("redis://localhost:7000");
        StatefulRedisClusterConnection<String, String> connection = redisClusterClient.connect();
        RedisAdvancedClusterCommands<String, String> syncCommands = connection.sync();
        syncCommands.set("hello","word");
        System.out.println(syncCommands.get("foo"));
        connection.close();
        redisClusterClient.shutdown();
    }
}
