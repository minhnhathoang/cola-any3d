package org.nhathm.app.executor;

import com.alibaba.cola.dto.MultiResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.app.assembler.ProjectAssembler;
import org.nhathm.domain.content.database.ContentMapper;
import org.nhathm.domain.content.entity.Content;
import org.nhathm.domain.project.gateway.ProjectGateway;
import org.nhathm.domain.vuforia.database.VuforiaKeyConverter;
import org.nhathm.domain.vuforia.database.VuforiaKeyMapper;
import org.nhathm.dto.clientobject.ProjectCO;
import org.nhathm.dto.clientobject.VuforiaKeyCO;
import org.nhathm.dto.query.ProjectListByOwnerQry;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@RequiredArgsConstructor
@Component
public class ProjectListByOwnerQryExe {

    private final ProjectGateway projectGateway;

    private final ProjectAssembler projectAssembler;

    private final ContentMapper contentMapper;

    private final VuforiaKeyMapper vuforiaKeyMapper;

    public MultiResponse<ProjectCO> execute(ProjectListByOwnerQry qry) {
        var projectList = projectGateway.getProjectListByOwnerId(qry.getOwnerId());
        return MultiResponse.of(projectList.stream()
                .map(projectAssembler::toCO)
                .peek(projectCO -> {
                    var contentCount = contentMapper.countByProjectId(projectCO.getId());
                    projectCO.setContentCount(contentCount);
                    var keyDO = vuforiaKeyMapper.selectByProjectId(projectCO.getId());
                    if (keyDO != null) {
                        VuforiaKeyCO keyCO = new VuforiaKeyCO();
                        BeanUtils.copyProperties(keyDO, keyCO);
                        projectCO.setVuforiaKey(keyCO);
                    }
                })
                .collect(Collectors.toList()));
    }
}
