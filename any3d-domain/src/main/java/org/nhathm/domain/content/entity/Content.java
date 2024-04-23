package org.nhathm.domain.content.entity;

import com.alibaba.cola.domain.Entity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author nhathm
 */
@Data
@Entity
public class Content {

    private String id;

    private String projectId;

    private String metadata;

    private LocalDateTime createdAt;

    private LocalDateTime lastModifiedAt;

}
