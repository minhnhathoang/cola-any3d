package org.nhathm.domain.imagetarget.entity;

import com.alibaba.cola.domain.Entity;
import lombok.Data;
import org.nhathm.domain.arsdk.ArSdkType;


@Data
@Entity
public class ImageTarget {

    private String id;

    private String contentId;

    private String filename;

    private ArSdkType arSdkType;

    private String additionalData;
}
