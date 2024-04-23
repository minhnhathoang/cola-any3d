package org.nhathm.domain.imagetarget.dataobject;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import org.nhathm.domain.arsdk.ArSdkType;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@TableName("image_target")
public class ImageTargetDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    private String contentId;

    private String filename;

    @EnumValue
    private ArSdkType arSdkType;

    private String additionalData;
}
