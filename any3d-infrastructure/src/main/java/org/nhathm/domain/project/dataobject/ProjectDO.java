package org.nhathm.domain.project.dataobject;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

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
@TableName("project")
public class ProjectDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    private String ownerId;

    private String name;

    private String description;

    private String metadata;

    private LocalDateTime createdAt;

    private LocalDateTime lastModifiedAt;
}
