package com.scccy.videoModel.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Data
@Accessors(chain = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "`user`")
public class User implements Serializable {
    @TableField(value = "id")
    private Integer id;

    @TableField(value = "lasttime")
    private String lasttime;

    @TableField(value = "`password`")
    private String password;

    @TableField(value = "username")
    private String username;

    private static final long serialVersionUID = 1L;
}