package org.nhathm.dto.command;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;


@Data
public class ImageTargetAddCmd {

    @NotNull
    private String contentId;

    @NotNull
    private String arSdkType;

    @NotNull
    private MultipartFile imageFile;
}
