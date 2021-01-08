package com.example.webdemo.service.biz;

import com.example.webdemo.WebDemoApplication;
import com.example.webdemo.beans.DemoDo;
import com.example.webdemo.beans.example.DemoDoExample;
import com.example.webdemo.common.utils.GsonUtil;
import com.example.webdemo.dao.mapper.DemoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebDemoApplication.class)
public class UserMapperTest {
    @Autowired
    private DemoMapper demoMapper;

    @Test
    public void queryTest() {
        DemoDo demoDo = new DemoDo();
        demoDo.setId(1);
        demoDo.setName("a");
        demoDo.setAccount("b");
        demoDo.setCreateTime(new Date());
        demoDo.setUpdateTime(new Date());
        demoMapper.insert(demoDo);

        DemoDoExample example = new DemoDoExample();
        DemoDoExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(1);
        List<DemoDo> list = demoMapper.selectByExample(example);
        System.out.println(GsonUtil.obj2Json(list));

    }

    @Test
    public void batchUpdate() {
        List<DemoDo> updateList = new ArrayList<>();
        DemoDo d1 = new DemoDo();
        d1.setId(1);
        d1.setAccount("x1");
        updateList.add(d1);
        DemoDo d2 = new DemoDo();
        d2.setId(2);
        d2.setAccount("x2");
        d2.setUpdateTime(new Date());
        updateList.add(d2);
        int i = demoMapper.updateByPrimaryKeySelectiveList(updateList);
        System.out.println(">>>>>>>>>>>>>>>>>>>result:" + (i == updateList.size()));
    }
}