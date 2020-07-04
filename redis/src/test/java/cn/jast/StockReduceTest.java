package cn.jast;

import cn.jast.utils.LuaFileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.Random;

public class StockReduceTest extends JedisTestBase{

    @Before
    public void before(){
        jedis = new Jedis("localhost");
        jedis.set("stock","100");
    }

    @After
    public void after(){
//        jedis.close();
    }

    @Test
    public void reduceStock(){
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                Jedis jedis1 = new Jedis("localhost");
                String lua = LuaFileUtils.getLuaScript("reduceStock.lua");
//                System.out.println("lua:"+lua);
                Random random = new Random();
                int reduceBy = random.nextInt(10);
                Object result = jedis1.eval(lua,Collections.singletonList("stock"),Collections.singletonList(String.valueOf(reduceBy)));
                System.out.println(String.format("reduceBy=%s,result = %s",reduceBy,result));

                jedis1.close();
            }).start();
        }


        while (true){

        }
    }
}
