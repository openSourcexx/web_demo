package com.example.webdemo.dao.impl;


import org.springframework.stereotype.Repository;

import com.example.webdemo.beans.DemoDo;
import com.example.webdemo.dao.intf.DemoDao;
import com.example.webdemo.dao.mapper.DemoMapper;
import com.example.webdemo.mybatis.common.AbstractBaseDaoImpl;

/**
 * @author tangaq
 * @since 2020/1/10 16:21
 */
@Repository
public class DemoDaoImpl extends AbstractBaseDaoImpl<DemoDo, DemoMapper> implements DemoDao {
}
