package org.nhathm.controller;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.nhathm.APIConstant;
import org.nhathm.api.ContentService;
import org.nhathm.dto.clientobject.ContentCO;
import org.nhathm.dto.clientobject.ContentCreatePresignedUploadUrlCO;
import org.nhathm.dto.command.ContentCreatePresignedUrlUploadHologramCmd;
import org.nhathm.dto.command.ContentDeleteCmd;
import org.nhathm.dto.command.ContentUpdateCmd;
import org.nhathm.dto.query.ContentGetByIdQry;
import org.nhathm.dto.query.ContentListByPageQry;
import org.nhathm.event.EventPublisher;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping(APIConstant.WEB_API_PATH + "/contents")
@RestController
public class ContentController {

    private final ContentService contentService;

    @DeleteMapping("/{id}")
    public Response deleteContent(@PathVariable String id, ContentDeleteCmd cmd) {
        cmd.setId(id);
        return contentService.deleteContent(cmd);
    }

    @PutMapping("/{id}")
    public Response updateContent(@PathVariable String id, @RequestBody ContentUpdateCmd cmd) {
        cmd.setId(id);
        return contentService.updateContent(cmd);
    }

    @PostMapping("/create-presigned-url-upload-hologram")
    public SingleResponse<ContentCreatePresignedUploadUrlCO> createPresignedUrlUploadHologram(@RequestBody ContentCreatePresignedUrlUploadHologramCmd cmd) {
        return contentService.createPresignedUrlUploadHologram(cmd);
    }

    @GetMapping("/{id}")
    public SingleResponse<ContentCO> getContentById(@PathVariable String id) {
        return contentService.getContentById(ContentGetByIdQry.builder().id(id).build());
    }

    @GetMapping
    public PageResponse<ContentCO> getContentListByPageQry(
            @Param("projectId") String projectId,
            @Param("pageIndex") Integer pageIndex,
            @Param("pageSize") Integer pageSize,
            @Param("searchKey") String searchKey) {
        ContentListByPageQry qry = new ContentListByPageQry();
        qry.setProjectId(projectId);
        qry.setPageIndex(pageIndex);
        qry.setPageSize(pageSize);
        qry.setSearchKey(searchKey == null ? "" : searchKey);
        return contentService.getContentListByPageQry(qry);
    }
}
