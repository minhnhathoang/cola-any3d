package org.nhathm.app.executor;

import com.alibaba.cola.dto.PageResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.minio.StatObjectResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nhathm.app.assembler.ContentAssembler;
import org.nhathm.config.MinioConfig;
import org.nhathm.domain.content.entity.Content;
import org.nhathm.domain.content.gateway.ContentGateway;
import org.nhathm.dto.clientobject.ContentCO;
import org.nhathm.dto.clientobject.HologramCO;
import org.nhathm.dto.clientobject.ImageTargetCO;
import org.nhathm.dto.query.ContentListByPageQry;
import org.nhathm.objectstorage.ObjectStorageService;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class ContentListByPageQryExe {

    private final ContentGateway contentGateway;

    private final ContentAssembler contentAssembler;

    private final ObjectStorageService objectStorageService;

    public PageResponse<ContentCO> execute(ContentListByPageQry qry) {
        Page<Content> page = contentGateway.listByPage(
                qry.getProjectId(),
                qry.getPageIndex(),
                qry.getPageSize(),
                qry.getSearchKey()
        );
        List<ContentCO> coList = page.getRecords().stream().map(contentAssembler::toCO).toList();

        for (ContentCO contentCO : coList) {
            HologramCO hologramCO = contentCO.getHologram();
            if (hologramCO != null) {
                String presignedUrl = objectStorageService.getPresignedGetUrl(MinioConfig.COMMON_BUCKET_NAME, hologramCO.getId());
                StatObjectResponse hologramObject = objectStorageService.getObjectInfo(MinioConfig.COMMON_BUCKET_NAME, hologramCO.getId());
                hologramCO.setUrl(presignedUrl);
                hologramCO.setSize(hologramObject.size());
                hologramCO.setContentType(hologramObject.contentType());
            }
            ImageTargetCO imageTargetCO = contentCO.getImageTarget();
            if (imageTargetCO != null) {
                String presignedUrl = objectStorageService.getPresignedGetUrl(MinioConfig.COMMON_BUCKET_NAME, imageTargetCO.getId());
                StatObjectResponse imageTargetObject = objectStorageService.getObjectInfo(MinioConfig.COMMON_BUCKET_NAME, imageTargetCO.getId());
                imageTargetCO.setUrl(presignedUrl);
                imageTargetCO.setSize(imageTargetObject.size());
                imageTargetCO.setContentType(imageTargetObject.contentType());
            }
        }
        return PageResponse.of(coList, (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
    }
}
