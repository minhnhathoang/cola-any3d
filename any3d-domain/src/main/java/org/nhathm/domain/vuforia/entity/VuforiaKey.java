package org.nhathm.domain.vuforia.entity;

import com.alibaba.cola.domain.Entity;
import lombok.Data;

@Data
@Entity
public class VuforiaKey {

    private String id;

    private String projectId;

    private String accessKey;

    private String secretKey;
}
