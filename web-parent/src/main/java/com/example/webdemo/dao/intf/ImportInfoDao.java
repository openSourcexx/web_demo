package com.example.webdemo.dao.intf;

import com.example.webdemo.beans.ImportInfo;
import com.example.webdemo.mybatis.common.IBaseDao;

/**
 * @author safe
 * @date 2021/2/19
 */
public interface ImportInfoDao extends IBaseDao<ImportInfo> {
    /**
     * 新增导入业务主信息
     *
     * @param importInfo 条件
     * @return int>
     */
    int insertSelective(ImportInfo importInfo);

    /**
     * 修改导入业务主信息
     *
     * @param importInfo
     * @return
     */
    int updateByPrimaryKeySelective(ImportInfo importInfo);
}
