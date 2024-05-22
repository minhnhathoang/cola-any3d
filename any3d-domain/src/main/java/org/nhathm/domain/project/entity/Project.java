package org.nhathm.domain.project.entity;

import com.alibaba.cola.domain.Entity;
import lombok.Data;
import org.nhathm.domain.content.entity.Content;
import org.nhathm.domain.user.entity.User;
import org.nhathm.domain.vuforia.entity.VuforiaKey;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
public class Project {

    private String id;

    private User owner;

    private String name;

    private String description;

    private String metadata;

    private LocalDateTime createdAt;

    private LocalDateTime lastModifiedAt;

    private List<Content> contents;

    private VuforiaKey vuforiaKey;
}
