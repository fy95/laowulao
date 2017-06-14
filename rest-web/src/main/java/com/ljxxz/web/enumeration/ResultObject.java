package com.ljxxz.web.enumeration;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by fuzhao on 2015/9/21.
 */
@XmlRootElement
public class ResultObject {

    private ResultEnum status;

    private String otherMsg;

    private Object data;

    public ResultObject(ResultEnum status) {
        this.status = status;
    }

    public ResultObject(ResultEnum status, String otherMsg, Object data) {
        this.status = status;
        this.otherMsg = otherMsg;
        this.data = data;
    }

    public int getCode() {
        return status.getCode();
    }

    public String getMsg() {
        return status.getMsg();
    }

    public void setStatus(ResultEnum status) {
        this.status = status;
    }

    public String getOtherMsg() {
        return otherMsg;
    }

    public void setOtherMsg(String otherMsg) {
        this.otherMsg = otherMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
