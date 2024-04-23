package org.nhathm.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import org.nhathm.dto.clientobject.ContentCO;
import org.nhathm.dto.clientobject.ContentCreatePresignedUploadUrlCO;
import org.nhathm.dto.command.ContentAddCmd;
import org.nhathm.dto.command.ContentCreatePresignedUrlUploadHologramCmd;
import org.nhathm.dto.command.ContentDeleteCmd;
import org.nhathm.dto.query.ContentListByPageQry;

public interface ContentService {

    Response addContent(ContentAddCmd cmd);

    Response deleteContent(ContentDeleteCmd cmd);

    SingleResponse<ContentCreatePresignedUploadUrlCO> createPresignedUrlUploadHologram(
            ContentCreatePresignedUrlUploadHologramCmd cmd);


    SingleResponse<ContentCO> getContentById(String contentId);

    MultiResponse<ContentCO> getContentListByPageQry(ContentListByPageQry qry);
}
