package com.haiyang.sca.openapi.vo;

import java.io.Serializable;

/**
 * @author wanghaiyang
 * @version 1.0.0
 * @className UserVO.java
 * @description 用户
 * @createTime 2020年12月18日 14:15:00
 */
public class UserVO implements Serializable {

    private String test;
    private boolean isMyTest;

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public boolean isMyTest() {
        return isMyTest;
    }

    public void setMyTest(boolean myTest) {
        isMyTest = myTest;
    }

    public UserVO(String test, boolean isMyTest) {
        this.test = test;
        this.isMyTest = isMyTest;
    }
}
