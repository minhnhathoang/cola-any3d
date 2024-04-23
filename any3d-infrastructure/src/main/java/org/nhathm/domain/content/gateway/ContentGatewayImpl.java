package org.nhathm.domain.content.gateway;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nhathm.domain.content.database.ContentConvertor;
import org.nhathm.domain.content.database.ContentMapper;
import org.nhathm.domain.content.dataobject.ContentDO;
import org.nhathm.domain.content.entity.Content;
import org.nhathm.event.DomainEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author nhathm
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class ContentGatewayImpl
        extends ServiceImpl<ContentMapper, ContentDO> implements ContentGateway {

    private final ContentConvertor contentConvertor;

    private final DomainEventPublisher domainEventPublisher;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addContent(Content content) {
        ContentDO contentDO = contentConvertor.toDataObject(content);
        this.save(contentDO);

        log.info("Content created: {}", contentDO.getId());

//        // publish event
//        ContentCreatedEvent event = new ContentCreatedEvent();
//        event.setContentId(contentDO.getId());
//        domainEventPublisher.publish(content);
    }

    @Override
    public Content getById(String id) {
        return contentConvertor.toEntity(super.getById(id));
    }
}
