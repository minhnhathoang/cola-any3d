package org.nhathm.domain.project.entity;

import com.alibaba.cola.domain.Entity;
import lombok.Getter;
import lombok.Setter;
import org.nhathm.domain.user.entity.User;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Entity
public class Project {

    private String id;

    private String name;

    @Getter
    @Setter
    protected User owner;
}
