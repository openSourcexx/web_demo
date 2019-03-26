package com.example.webdemo.common;

import com.example.webdemo.common.exception.DBException;
import com.example.webdemo.common.exception.ServiceException;
import com.example.webdemo.common.vo.BaseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 统一异常捕获处理器
 * @author tangaq
 * @date 2019/3/25
 */
@ControllerAdvice
public class ExceptionCatchHandler {
    private Logger logger = LoggerFactory.getLogger(ExceptionCatchHandler.class);

    /**
     * 捕获其它异常
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public BaseVo handleException(Exception e) throws Exception {
        logger.info("网络异常:{}",e);
        return buildResponseBody(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),"网络异常");
    }

    /**
     * 捕获数据库异常
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler({DBException.class})
    @ResponseBody
    public BaseVo handleDBException(DBException e) throws Exception {
        logger.info("数据库操作异常:{}",e);
        return buildResponseBody(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage());
    }


    /**
     * 捕获业务异常
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler({ServiceException.class})
    @ResponseBody
    public BaseVo handleServiceException(ServiceException e) throws Exception {
        logger.info("业务异常:{}",e);
        return buildResponseBody(String.valueOf(HttpStatus.UNAUTHORIZED.value()),e.getMessage());
    }

    /**
     * 捕获spring参数校验异常
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler({BindException.class})
    @ResponseBody
    public BaseVo handleBindException(BindException e) throws Exception {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        if (!CollectionUtils.isEmpty(allErrors)) {
            logger.info("validation参数校验错误:{}",allErrors.get(0).getDefaultMessage());
            return buildResponseBody(String.valueOf(HttpStatus.BAD_REQUEST.value()),
                    allErrors.get(0).getDefaultMessage());
        }
        logger.info("validation参数校验错误:{}",e.getMessage());
        return buildResponseBody(String.valueOf(HttpStatus.BAD_REQUEST.value()),e.getMessage());
    }

    /**
     * 捕获spring参数校验异常
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public BaseVo handleMethodArgumentNotValidException(MethodArgumentNotValidException e) throws Exception {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        if (!CollectionUtils.isEmpty(allErrors)) {
            logger.info("validation参数校验错误:{}",allErrors.get(0).getDefaultMessage());
            return buildResponseBody(String.valueOf(HttpStatus.BAD_REQUEST.value()),
                    allErrors.get(0).getDefaultMessage());
        }
        logger.info("validation参数校验错误:{}",e.getMessage());
        return buildResponseBody(String.valueOf(HttpStatus.BAD_REQUEST.value()),e.getMessage());
    }

    private BaseVo buildResponseBody(String code, String errMsg) {
        return new BaseVo(false,code,errMsg);
    }
}
