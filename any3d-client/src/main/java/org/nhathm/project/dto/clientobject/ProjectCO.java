package org.nhathm.project.dto.clientobject;

import com.alibaba.cola.dto.ClientObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProjectCO extends ClientObject {

    private Long id;

    private Long ownerId;

    private String name;

    private String description;

    private String metadata;

    private LocalDateTime createdAt;

    private LocalDateTime lastModifiedAt;
}
