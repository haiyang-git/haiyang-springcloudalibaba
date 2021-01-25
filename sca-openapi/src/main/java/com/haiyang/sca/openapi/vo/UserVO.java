package com.haiyang.sca.openapi.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wanghaiyang
 * @version 1.0.0
 * @className UserVO.java
 * @description 用户
 * @createTime 2020年12月18日 14:15:00
 */
@Data
public class UserVO implements Serializable {
    private static final long serialVersionUID = 8581557595007972659L;
    /**
     * 主键
     */
    private Integer id;

    /**
     * 身份证
     */
    private String idCard;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 联系人手机号
     */
    private LocalDateTime birthday;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
