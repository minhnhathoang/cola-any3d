package org.nhathm.content.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import org.nhathm.content.dto.clientobject.ContentCO;
import org.nhathm.content.dto.command.ContentAddCmd;
import org.nhathm.content.dto.command.ContentDeleteCmd;
import org.nhathm.content.dto.command.query.ContentListByPageQry;

/**
 * @author nhathm
 */
public interface ContentService {

    MultiResponse<ContentCO> getContentListByPageQry(ContentListByPageQry qry);

    Response addContent(ContentAddCmd cmd);

    Response deleteContent(ContentDeleteCmd cmd);
}
