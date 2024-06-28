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
@TableName(value = "sqlite_master")
public class SqliteMaster implements Serializable {
    @TableField(value = "`type`")
    private String type;

    @TableField(value = "`name`")
    private String name;

    @TableField(value = "tbl_name")
    private String tblName;

    @TableField(value = "rootpage")
    private Integer rootpage;

    @TableField(value = "`sql`")
    private String sql;

    private static final long serialVersionUID = 1L;
}