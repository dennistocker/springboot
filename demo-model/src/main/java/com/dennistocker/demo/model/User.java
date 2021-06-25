package com.dennistocker.demo.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @date 2021/5/24 3:34 下午
 */

@Data
@TableName(value = "tbl_user_info")
public class User {
    @TableId
    private Integer userId;

    private String userName;
    private String headImage;
    private String walletAddress;
    private Integer walletType;
    private String introduction;
    private String mobile;
}
