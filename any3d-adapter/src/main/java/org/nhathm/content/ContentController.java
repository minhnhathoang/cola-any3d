package org.nhathm.content;

import com.alibaba.cola.dto.MultiResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.APIConstant;
import org.nhathm.content.dto.clientobject.ContentCO;
import org.nhathm.content.dto.command.query.ContentListByPageQry;
import org.nhathm.project.api.ProjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@RequestMapping(APIConstant.WEB_API_PATH + "/contents")
@RestController
public class ContentController {

    private final ProjectService projectService;

    @GetMapping
    public MultiResponse<ContentCO> getContentListByPageQry(@ModelAttribute ContentListByPageQry qry) {
        return projectService.getContentListByPageQry(qry);
    }
}
