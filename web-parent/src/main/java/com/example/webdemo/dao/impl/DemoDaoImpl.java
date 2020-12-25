package com.example.webdemo.dao.impl;


import com.example.webdemo.beans.DemoDo;
import com.example.webdemo.dao.intf.DemoDao;
import com.example.webdemo.dao.mapper.DemoMapper;
import com.example.webdemo.mybatis.common.AbstractBaseDaoImpl;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tangaq
 * @since 2020/1/10 16:21
 */
@Repository
public class DemoDaoImpl extends AbstractBaseDaoImpl<DemoDo, DemoMapper> implements DemoDao {

    @Override
    public List<DemoDo> queryBatch() {
        int querySize = 3;
        int startNo = 0;
        List<DemoDo> list = new ArrayList<>();
        while (true) {
            List<DemoDo> sub = this.getMapper().queryBatch(startNo, querySize);
            list.addAll(sub);
            if (CollectionUtils.isEmpty(sub) || sub.size() < querySize) {
                break;
            }
            startNo++;
            startNo = startNo * querySize;
        }
        return list;
    }

    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        list.add("asd");
        List<Object> l2 = new ArrayList<>();

        list.addAll(l2);
    }
}
