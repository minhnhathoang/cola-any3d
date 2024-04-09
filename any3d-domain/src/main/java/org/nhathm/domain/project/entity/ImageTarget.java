package org.nhathm.domain.project.entity;

import lombok.Data;
import org.nhathm.domain.arsdk.ARSdkType;

/**
 * @author nhathm
 */
@Data
public class ImageTarget {

    private Long id;

    private Long contentId;

    private String fileName;

    private ARSdkType arSdkType = ARSdkType.NONE;
}
