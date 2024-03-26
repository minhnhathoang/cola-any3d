package org.nhathm.object.api;


import com.alibaba.cola.dto.SingleResponse;
import org.nhathm.object.dto.clientobject.PresignedUrlCO;
import org.nhathm.object.dto.query.ObjectGetPresignedUrlQry;


/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
public interface ObjectService {

    SingleResponse<PresignedUrlCO> getPresignedUrl(ObjectGetPresignedUrlQry qry);
}
