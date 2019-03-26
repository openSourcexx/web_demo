package com.example.webdemo.service.impl;

import com.example.webdemo.beans.OperatorLog;
import com.example.webdemo.common.Page;
import com.example.webdemo.dao.OperatorLogMapper;
import com.example.webdemo.enums.SysCodeEnum;
import com.example.webdemo.exception.DBException;
import com.example.webdemo.service.OperatorLogService;
import com.example.webdemo.utils.JSONUtil;
import com.example.webdemo.vo.request.OperatorLogRequest;
import com.example.webdemo.vo.response.OperatorLogVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author tangaq
 * @date 2019/3/21
 */
@Service
public class OperatorLogServiceImpl implements OperatorLogService {
    private static Logger logger = LoggerFactory.getLogger(OperatorLogServiceImpl.class);

    @Autowired
    private OperatorLogMapper operatorLogMapper;

    @Override
    @Transactional
    public boolean save(OperatorLog log) {
        try {
            log.setCreateTime(new Date());
            log.setUpdateTime(new Date());
            return operatorLogMapper.insertSelective(log) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBException(SysCodeEnum.DB_ERR.getCode(),SysCodeEnum.DB_ERR.getDesc());
        }
    }

    @Override
    public OperatorLogVo query(OperatorLogRequest request) {
        OperatorLogVo vo = new OperatorLogVo(true);
        OperatorLog log = new OperatorLog();
        BeanUtils.copyProperties(request,log);
        List<OperatorLog> list = operatorLogMapper.listBySelective(log, Page.getPageStart(request.getPageNo(),request.getPageSize()),request.getPageSize());
        long total = operatorLogMapper.countBySelective(log);
        vo.setList(list);
        vo.setTotal((int) total);
        logger.info("query{}", JSONUtil.toJSONString(vo));
        return vo;
    }
}
