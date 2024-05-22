package org.nhathm.app.executor;

import com.alibaba.cola.domain.DomainFactory;
import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nhathm.domain.content.entity.Content;
import org.nhathm.domain.content.gateway.ContentGateway;
import org.nhathm.dto.command.ContentUpdateCmd;
import org.springframework.stereotype.Component;


@Slf4j
@RequiredArgsConstructor
@Component
public class ContentUpdateCmdExe {

    private final ContentGateway contentGateway;

    public Response execute(ContentUpdateCmd cmd) {
        Content content = DomainFactory.create(Content.class);
        content.setId(cmd.getId());
        content.setName(cmd.getName());
        content.setMetadata(cmd.getMetadata());
        contentGateway.updateContent(content);
        return Response.buildSuccess();
    }
}
