package org.nhathm.domain.content.dataobject;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import org.nhathm.domain.hologram.dataobject.HologramDO;
import org.nhathm.domain.imagetarget.dataobject.ImageTargetDO;
import org.nhathm.domain.project.dataobject.ProjectDO;
import org.nhathm.domain.user.dataobject.UserDO;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author nhathm
 * @since 2024-04-06
 */
@Getter
@Setter
@TableName(value = "content", autoResultMap = true)
public class ContentDO {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @TableField
    private String projectId;

    @TableField
    private String name;

    @TableField
    private String metadata;

    @TableField
    private LocalDateTime createdAt;

    @TableField
    private LocalDateTime lastModifiedAt;

    @TableLogic
    private Integer deleted;

    @TableField(exist = false)
    private HologramDO hologram;

    @TableField(exist = false)
    private ImageTargetDO imageTarget;

    @TableField(exist = false)
    private UserDO owner;
}
