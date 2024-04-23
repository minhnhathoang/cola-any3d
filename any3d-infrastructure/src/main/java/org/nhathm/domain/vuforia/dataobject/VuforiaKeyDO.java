package org.nhathm.domain.vuforia.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author nhathm
 */
@Data
@TableName("vuforia_key")
public class VuforiaKeyDO {

    @TableId(type = IdType.ASSIGN_UUID)
    private String projectId;

    private String accessKey;

    private String secretKey;
}
