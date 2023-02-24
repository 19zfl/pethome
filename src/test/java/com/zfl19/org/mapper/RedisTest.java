package com.zfl19.org.mapper;

import com.zfl19.PetHomeApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PetHomeApp.class)
public class RedisTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 操作字符串
     */
    @Test
    public void test01() {

        redisTemplate.opsForValue().set("name", "沈阳");
        // 读取对应key的值
        String name = redisTemplate.opsForValue().get("name");
        System.out.println(name);

    }

    /**
     * 操作list集合
     */
    @Test
    public void test02() {

        redisTemplate.opsForList().leftPush("list", "9");
        redisTemplate.opsForList().leftPush("list", "10");
        redisTemplate.opsForList().leftPush("list", "11");
        redisTemplate.opsForList().leftPush("list", "12");
        redisTemplate.opsForList().rightPush("list", "8");
        redisTemplate.opsForList().rightPush("list", "7");
        redisTemplate.opsForList().rightPush("list", "6");
        redisTemplate.opsForList().rightPush("list", "5");

        List<String> list = redisTemplate.opsForList().range("list", 0, -1);
        list.stream().forEach(System.out::println);

    }

    /**
     * 操作hashmap
     */
    @Test
    public void test03() {

        String stuInfo = "stuInfo";
        redisTemplate.opsForHash().put(stuInfo, "name", "张三");
        redisTemplate.opsForHash().put(stuInfo, "age", "18");
        redisTemplate.opsForHash().put(stuInfo, "sex", "男");

        String name = (String) redisTemplate.opsForHash().get(stuInfo, "name");
        System.out.println(name);

    }

    /**
     * 操作set
     */
    @Test
    public void test04() {

        redisTemplate.opsForSet().add("colors", "red");
        redisTemplate.opsForSet().add("colors", "blue");
        redisTemplate.opsForSet().add("colors", "pink");
        redisTemplate.opsForSet().add("colors", "yellow");
        redisTemplate.opsForSet().add("colors", "green");

        Set<String> colors = redisTemplate.opsForSet().members("colors");
        for (String color : colors) {
            System.out.println(color);
        }

    }

}
