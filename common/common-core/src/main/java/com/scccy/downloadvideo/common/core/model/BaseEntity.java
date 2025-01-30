package com.scccy.downloadvideo.common.core.model; 

import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseEntity implements Serializable {
    
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    
    @TableField(fill = FieldFill.INSERT)
    private String createNode;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateNode;
    
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer delFlag;
} 