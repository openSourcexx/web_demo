package com.example.webdemo.service.biz;

import com.example.webdemo.beans.OperatorLog;
import com.example.webdemo.vo.request.OperatorLogRequest;
import com.example.webdemo.vo.response.OperatorLogVo;

/**
 * @author tangaq
 * @date 2019/3/21
 */
public interface OperatorLogService {
    boolean save(OperatorLog log);

    OperatorLogVo query(OperatorLogRequest log);
}
