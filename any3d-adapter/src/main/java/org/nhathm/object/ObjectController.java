package org.nhathm.object;

import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.constant.APIConstant;
import org.nhathm.object.api.ObjectService;
import org.nhathm.object.dto.clientobject.PresignedUrlCO;
import org.nhathm.object.dto.query.ObjectGetPresignedUrlQry;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@RequestMapping(APIConstant.WEB_API_PATH + "/objects")
@RestController
public class ObjectController {

    private final ObjectService objectService;

    @PostMapping("/getPresignedUrl")
    public SingleResponse<PresignedUrlCO> getPresignedUrl(@RequestBody ObjectGetPresignedUrlQry qry) {
        return objectService.getPresignedUrl(qry);
    }
}