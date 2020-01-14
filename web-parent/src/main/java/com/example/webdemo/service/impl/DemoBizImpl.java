package com.example.webdemo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webdemo.beans.DemoDo;
import com.example.webdemo.dao.intf.DemoDao;
import com.example.webdemo.service.biz.DemoBiz;

/**
 * demo
 *
 * @author tangaq25172@yunrong.cn
 * @since 2019/12/3 13:54
 */
@Service
public class DemoBizImpl implements DemoBiz {
    @Autowired
    private DemoDao demoDao;
    @Override
    public void testRpcConn() {
        List<DemoDo> list = new ArrayList<>();
        DemoDo a1 = new DemoDo();
        a1.setName("b1");
        a1.setAccount("123");
        list.add(a1);
        demoDao.insertList(list);
    }
}
