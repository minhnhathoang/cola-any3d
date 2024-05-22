package org.nhathm.domain.project.dataobject;


import com.baomidou.mybatisplus.annotation.*;
import com.github.yulichang.annotation.EntityMapping;
import com.github.yulichang.annotation.FieldMapping;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.nhathm.domain.content.dataobject.ContentDO;
import org.nhathm.domain.user.dataobject.UserDO;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author nhathm
 * @since 2024-04-06
 */
@Getter
@Setter
@TableName(value = "project", autoResultMap = true)
@ToString
public class ProjectDO {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @TableField
    private String ownerId;

    @TableField
    private String name;

    @TableField
    private String description;

    @TableField
    private String metadata;

    @TableField
    private LocalDateTime createdAt;

    @TableField
    private LocalDateTime lastModifiedAt;

    @TableLogic
    private Integer deleted;


    @TableField(exist = false)
    private UserDO owner;

    @TableField(exist = false)
    private List<ContentDO> contents;
}
