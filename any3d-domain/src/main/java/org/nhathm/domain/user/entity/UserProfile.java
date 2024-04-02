package org.nhathm.domain.user.entity;

import com.alibaba.cola.domain.Entity;
import org.nhathm.domain.project.entity.Project;

import java.util.List;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Entity
public class UserProfile {

    private Long id;

    private String email;

    private String phoneNumber;

    private String username;

    private List<Project> projects;
}
