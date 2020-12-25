package com.example.webdemo.xxljob.core.execute;

import com.example.webdemo.common.utils.GsonUtil;
import com.example.webdemo.common.utils.HttpRequestUtil;
import com.example.webdemo.xxljob.core.biz.ExecutorBiz;
import com.example.webdemo.xxljob.core.biz.model.LogParam;
import com.example.webdemo.xxljob.core.biz.model.ReturnT;

/**
 * @author tangaq
 * @date 2020/12/23
 */
public class ExecutorBizClient implements ExecutorBiz {
    private String addressUrl;


    public ExecutorBizClient(String addressUrl) {
        this.addressUrl = addressUrl;
    }

    public String getAddressUrl() {
        return addressUrl;
    }

    public void setAddressUrl(String addressUrl) {
        this.addressUrl = addressUrl;
    }

    @Override
    public ReturnT<String> log(LogParam logParam) {
        try {
            String ret = HttpRequestUtil.sendPostWithoutLog(addressUrl + "/log", GsonUtil.obj2Json(logParam));
            return GsonUtil.json2Obj(ret, ReturnT.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ReturnT.FAIL;
    }
}
