package org.nhathm.imagetarget;

import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import org.nhathm.APIConstant;
import org.nhathm.api.ImageTargetService;
import org.nhathm.dto.command.ImageTargetAddCmd;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;


@RequiredArgsConstructor
@RequestMapping(APIConstant.WEB_API_PATH + "/image-targets")
@RestController
public class ImageTargetController {

    private final ImageTargetService imageTargetService;

    @PostMapping
    public Response addImageTarget(@Valid @ModelAttribute ImageTargetAddCmd cmd) throws IOException {
        return imageTargetService.addImageTarget(cmd);
    }
}
