package org.nhathm.domain.project.entity;

import com.alibaba.cola.domain.Entity;
import lombok.Data;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Data
@Entity
public class Project {

    private Long userId;

    private String name;

    private String metadata;
}
