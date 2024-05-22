package org.nhathm.app.serviceimpl;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.nhathm.api.ContentService;
import org.nhathm.app.executor.*;
import org.nhathm.dto.clientobject.ContentCO;
import org.nhathm.dto.clientobject.ContentCreatePresignedUploadUrlCO;
import org.nhathm.dto.command.ContentCreatePresignedUrlUploadHologramCmd;
import org.nhathm.dto.command.ContentDeleteCmd;
import org.nhathm.dto.command.ContentUpdateCmd;
import org.nhathm.dto.query.ContentGetByIdQry;
import org.nhathm.dto.query.ContentListByPageQry;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ContentServiceImpl implements ContentService {

    ContentCreatePresignedUploadUrlCmdExe contentCreatePresignedUploadUrlCmdExe;

    ContentUpdateCmdExe contentUpdateCmdExe;

    ContentDeleteCmdExe contentDeleteCmdExe;

    ContentGetByIdQryExe contentGetByIdQryExe;

    ContentListByPageQryExe contentListByPageQryExe;


    @Override
    public Response updateContent(ContentUpdateCmd cmd) {
        return contentUpdateCmdExe.execute(cmd);
    }

    @Override
    public Response deleteContent(ContentDeleteCmd cmd) {
        return contentDeleteCmdExe.execute(cmd);
    }

    @Override
    public SingleResponse<ContentCreatePresignedUploadUrlCO> createPresignedUrlUploadHologram(ContentCreatePresignedUrlUploadHologramCmd cmd) {
        return contentCreatePresignedUploadUrlCmdExe.execute(cmd);
    }

    @Override
    public SingleResponse<ContentCO> getContentById(ContentGetByIdQry qry) {
        return contentGetByIdQryExe.execute(qry);
    }

    @Override
    public PageResponse<ContentCO> getContentListByPageQry(ContentListByPageQry qry) {
        return contentListByPageQryExe.execute(qry);
    }
}
