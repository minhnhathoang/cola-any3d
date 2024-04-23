package org.nhathm.dto.command;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class ImageTargetAddCmd {

    private String contentId;

    private String arSdkType;

    private MultipartFile imageFile;
}
