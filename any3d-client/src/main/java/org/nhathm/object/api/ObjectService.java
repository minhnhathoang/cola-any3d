package org.nhathm.object.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import org.nhathm.object.dto.command.ObjectDeleteCmd;
import org.nhathm.object.dto.command.ObjectUpdateCmd;
import org.nhathm.object.dto.command.ObjectUploadCmd;
import org.nhathm.object.dto.query.ObjectByIdQry;
import org.nhathm.object.dto.query.ObjectListByProjectAndPageQry;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
public interface ObjectService {

    SingleResponse uploadObject(ObjectUploadCmd cmd);

    Response deleteObject(ObjectDeleteCmd cmd);

    Response updateObject(ObjectUpdateCmd cmd);

    MultiResponse listObjectBy(ObjectListByProjectAndPageQry qry);

    SingleResponse getObjectBy(ObjectByIdQry qry);
}
