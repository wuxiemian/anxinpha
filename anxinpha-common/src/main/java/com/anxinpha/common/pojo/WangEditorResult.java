package com.anxinpha.common.pojo;

import java.util.List;

/**
 * @author 尹硕硕
 * @description KindEditor返回结果集
 **/
public class WangEditorResult {
    /**
     * errno 即错误代码，0 表示没有错误。
     * 如果有错误，errno != 0，可通过下文中的监听函数 fail 拿到该错误码进行自定义处理
     */
    private Integer errno;

    private List<String> data;

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
