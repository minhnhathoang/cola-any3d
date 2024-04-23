package org.nhathm.app.content.service;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.nhathm.api.ContentService;
import org.nhathm.app.content.executor.command.ContentCreatePresignedUploadUrlCmdExe;
import org.nhathm.dto.clientobject.ContentCO;
import org.nhathm.dto.clientobject.ContentCreatePresignedUploadUrlCO;
import org.nhathm.dto.command.ContentAddCmd;
import org.nhathm.dto.command.ContentCreatePresignedUrlUploadHologramCmd;
import org.nhathm.dto.command.ContentDeleteCmd;
import org.nhathm.dto.query.ContentListByPageQry;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ContentServiceImpl implements ContentService {

    ContentCreatePresignedUploadUrlCmdExe contentCreatePresignedUploadUrlCmdExe;


    @Override
    public Response addContent(ContentAddCmd cmd) {
        return null;
    }

    @Override
    public Response deleteContent(ContentDeleteCmd cmd) {
        return null;
    }

    @Override
    public SingleResponse<ContentCreatePresignedUploadUrlCO> createPresignedUrlUploadHologram(ContentCreatePresignedUrlUploadHologramCmd cmd) {
        return contentCreatePresignedUploadUrlCmdExe.execute(cmd);
    }

    @Override
    public SingleResponse<ContentCO> getContentById(String contentId) {
        return null;
    }

    @Override
    public MultiResponse<ContentCO> getContentListByPageQry(ContentListByPageQry qry) {
        return null;
    }
}
