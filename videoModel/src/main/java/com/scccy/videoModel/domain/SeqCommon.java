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
@TableName(value = "seq_common")
public class SeqCommon implements Serializable {
    @TableField(value = "seq_id")
    private String seqId;

    @TableField(value = "seq_count")
    private Long seqCount;

    private static final long serialVersionUID = 1L;
}