package org.nhathm.dto.clientobject;

import lombok.Data;

@Data
public class ImageTargetCO {

    private String id;

    private String contentId;

    private String filename;

    private String url;

    private long size;

    private String contentType;

    private String arSdkType;

    private String additionalData;
}
