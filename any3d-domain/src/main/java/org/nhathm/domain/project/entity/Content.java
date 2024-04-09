package org.nhathm.domain.project.entity;

import com.alibaba.cola.domain.Entity;
import lombok.Data;

/**
 * @author nhathm
 */
@Data
@Entity
public class Content {

    private Long id;

    private Long projectId;

    private String metadata;
}
