package org.nhathm.domain.hologram.entity;

import com.alibaba.cola.domain.Entity;
import lombok.Data;


@Data
@Entity
public class Hologram {

    private String id;

    private String contentId;

    private String filename;

    private HologramType type;
}
