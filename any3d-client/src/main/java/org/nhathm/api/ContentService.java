package org.nhathm.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import org.nhathm.dto.clientobject.ContentCO;
import org.nhathm.dto.clientobject.ContentCreatePresignedUploadUrlCO;
import org.nhathm.dto.command.ContentCreatePresignedUrlUploadHologramCmd;
import org.nhathm.dto.command.ContentDeleteCmd;
import org.nhathm.dto.command.ContentUpdateCmd;
import org.nhathm.dto.query.ContentGetByIdQry;
import org.nhathm.dto.query.ContentListByPageQry;

public interface ContentService {

    Response updateContent(ContentUpdateCmd cmd);

    Response deleteContent(ContentDeleteCmd cmd);

    SingleResponse<ContentCreatePresignedUploadUrlCO> createPresignedUrlUploadHologram(
            ContentCreatePresignedUrlUploadHologramCmd cmd);


    SingleResponse<ContentCO> getContentById(ContentGetByIdQry qry);

    PageResponse<ContentCO> getContentListByPageQry(ContentListByPageQry qry);
}
