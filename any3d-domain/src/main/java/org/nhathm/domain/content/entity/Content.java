package org.nhathm.domain.content.entity;

import com.alibaba.cola.domain.Entity;
import lombok.Data;
import org.nhathm.domain.hologram.entity.Hologram;
import org.nhathm.domain.imagetarget.entity.ImageTarget;
import org.nhathm.domain.user.entity.User;

import java.time.LocalDateTime;

/**
 * @author nhathm
 */
@Data
@Entity
public class Content {

    private String id;

    private String projectId;

    private String name;

    private String metadata;

    private LocalDateTime createdAt;

    private LocalDateTime lastModifiedAt;

    private Hologram hologram;

    private ImageTarget imageTarget;

    private User owner;
}
