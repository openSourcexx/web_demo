package com.example.webdemo.vo.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author tangaq
 * @date 2019/3/28
 */
public class UserDelRequest {

    /**
     * 批量删除
     */
    @NotNull(message = "用户id为空")
    @NotEmpty(message = "用户id为空")
    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
