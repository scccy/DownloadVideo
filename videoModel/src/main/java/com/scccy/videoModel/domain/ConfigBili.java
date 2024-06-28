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
@TableName(value = "config_bili")
public class ConfigBili implements Serializable {
    @TableField(value = "id")
    private Integer id;

    @TableField(value = "bigmember")
    private String bigmember;

    @TableField(value = "bilicookies")
    private String bilicookies;

    @TableField(value = "bitstream")
    private String bitstream;

    private static final long serialVersionUID = 1L;
}