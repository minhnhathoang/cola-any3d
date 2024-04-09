package org.nhathm.domain.project.entity;

import lombok.Data;
import org.nhathm.domain.objectstorage.entity.HologramType;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Data
public class Hologram {

    private Long id;

    private Long contentId;

    private Long userId;

    private String fileName;

    private String url;

    private Double size;

    private HologramType type;
}
