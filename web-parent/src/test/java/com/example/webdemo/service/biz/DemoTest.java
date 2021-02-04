package com.example.webdemo.service.biz;

import com.example.webdemo.beans.DemoDo;
import com.example.webdemo.common.redis.RedisCore;
import com.example.webdemo.common.utils.Demo;
import com.example.webdemo.dao.mapper.DemoMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author tangaq
 * @date 2021/1/15
 */
public class DemoTest extends BaseTest {
    @Autowired
    private RedisCore redisCore;
    @Test
    public void a() {
        // 1.setString
        redisCore.setString("a1", "xxx", 60000);
        // getString
        redisCore.getString("a1");

        // 2.setObject
        Demo demo = new Demo();
        demo.setAge(0);
        redisCore.set("b1", demo, 60000);
        // getObject
        Object bbb = redisCore.get("b1");

        System.out.println();
    }

    @Autowired
    private DemoMapper demoMapper;
    @Test
    public void b(){
        DemoDo example = new DemoDo();
        example.setId(2);
        example.setName("xxx");

        DemoDo demo2 = new DemoDo();
        demo2.setAccount("dd");
        demo2.setUpdateTime(new Date());
        // int i = demoMapper.updateByRecordSelective(demo);
        int i = demoMapper.updateByRecordSelective(demo2, example);
        System.out.println();
    }


}
