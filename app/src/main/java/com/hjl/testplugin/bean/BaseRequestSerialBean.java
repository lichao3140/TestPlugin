package com.hjl.testplugin.bean;

import java.io.Serializable;

/**
 * @ClassName: BaseRequestSerialBean
 * @Author: lbgong
 * @CreateDate: 2022/9/13
 * @Email: 347667260@qq.com
 * @Description: 数据流向 安卓->柜控
 */
public class BaseRequestSerialBean implements Serializable {
    public int msgType;
    public int result;
    public BaseRequestSerialBean(int msgType, int result) {
        this.msgType = msgType;
        this.result = result;
    }
    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "BaseRequestSerialBean{" +
                "msgType=" + msgType +
                ", result=" + result +
                '}';
    }
}
