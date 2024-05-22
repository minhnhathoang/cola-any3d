package org.nhathm.domain.vuforia.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.nhathm.domain.project.dataobject.ProjectDO;

/**
 * @author nhathm
 */
@Data
@TableName(value = "vuforia_key", autoResultMap = true)
public class VuforiaKeyDO {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @TableField
    private String projectId;

    @TableField
    private String accessKey;

    @TableField
    private String secretKey;
}
