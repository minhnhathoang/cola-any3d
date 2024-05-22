package org.nhathm.domain.imagetarget.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import org.nhathm.domain.arsdk.ArSdkType;
import org.nhathm.domain.content.entity.Content;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@TableName(value = "image_target", autoResultMap = true)
public class ImageTargetDO {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @TableField
    private String contentId;

    @TableField
    private String filename;

    @EnumValue
    @TableField
    private ArSdkType arSdkType;

    @TableField
    private String additionalData;
}
