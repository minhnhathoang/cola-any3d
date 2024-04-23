package org.nhathm.content;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.APIConstant;
import org.nhathm.api.ContentService;
import org.nhathm.dto.clientobject.ContentCO;
import org.nhathm.dto.clientobject.ContentCreatePresignedUploadUrlCO;
import org.nhathm.dto.command.ContentAddCmd;
import org.nhathm.dto.command.ContentCreatePresignedUrlUploadHologramCmd;
import org.nhathm.dto.command.ContentDeleteCmd;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping(APIConstant.WEB_API_PATH + "/contents")
@RestController
public class ContentController {

    private final ContentService contentService;

    @PostMapping
    public Response addContent(@RequestBody ContentAddCmd cmd) {
        return contentService.addContent(cmd);
    }

    @DeleteMapping
    public Response deleteContent(@RequestBody ContentDeleteCmd cmd) {
        return contentService.deleteContent(cmd);
    }


    @GetMapping("/{contentId}")
    public SingleResponse<ContentCO> getContentById(@PathVariable String contentId) {
        return contentService.getContentById(contentId);
    }

    @PostMapping("/create-presigned-url-upload-hologram")
    public SingleResponse<ContentCreatePresignedUploadUrlCO> createPresignedUrlUploadHologram(@RequestBody ContentCreatePresignedUrlUploadHologramCmd cmd) {
        return contentService.createPresignedUrlUploadHologram(cmd);
    }
}
