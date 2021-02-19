package com.example.webdemo.dao.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.webdemo.beans.ImportInfo;
import com.example.webdemo.dao.intf.ImportInfoDao;
import com.example.webdemo.dao.mapper.ImportInfoMapper;
import com.example.webdemo.mybatis.common.AbstractBaseDaoImpl;

/**
 * @author safe
 * @date 2021/2/19
 */
@Service
public class ImportInfoDaoImpl extends AbstractBaseDaoImpl<ImportInfo, ImportInfoMapper> implements ImportInfoDao {

    @Override
    public int deleteByPrimaryKey(Object key) {
        return super.deleteByPrimaryKey(key);
    }

    /**
     * 新增导入业务主信息
     *
     * @param importInfo 条件
     * @return int>
     */
    @Override
    public int insertSelective(ImportInfo importInfo) {
        if (importInfo == null) {
            return -1;
        }
        importInfo.setCreateTime(new Date());
        importInfo.setUpdateTime(new Date());
        return getMapper().insertSelective(importInfo);
    }

    /**
     * 修改导入业务主信息
     *
     * @param creditImportInfo
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(ImportInfo creditImportInfo) {
        if (creditImportInfo == null) {
            return -1;
        }
        creditImportInfo.setUpdateTime(new Date());
        return getMapper().updateByPrimaryKeySelective(creditImportInfo);
    }
}
