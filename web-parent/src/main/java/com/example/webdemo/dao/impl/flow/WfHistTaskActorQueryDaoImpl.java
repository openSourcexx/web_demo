package com.example.webdemo.dao.impl.flow;

import org.springframework.stereotype.Repository;

import com.example.webdemo.beans.WfHistTaskActor;
import com.example.webdemo.dao.intf.flow.WfHistTaskActorQueryDao;
import com.example.webdemo.dao.mapper.flow.WfHistTaskActorMapper;
import com.example.webdemo.mybatis.common.AbstractBaseDaoImpl;

/**
 * @author admin
 * @since 2020/1/10 16:21
 */
@Repository
public class WfHistTaskActorQueryDaoImpl extends AbstractBaseDaoImpl<WfHistTaskActor, WfHistTaskActorMapper>
    implements WfHistTaskActorQueryDao {
}
