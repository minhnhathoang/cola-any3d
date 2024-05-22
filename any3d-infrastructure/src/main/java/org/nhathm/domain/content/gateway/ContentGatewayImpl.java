package org.nhathm.domain.content.gateway;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nhathm.domain.content.database.ContentConvertor;
import org.nhathm.domain.content.database.ContentMapper;
import org.nhathm.domain.content.dataobject.ContentDO;
import org.nhathm.domain.content.entity.Content;
import org.nhathm.dto.domainevent.ContentCreatedEvent;
import org.nhathm.dto.domainevent.ContentDeletedEvent;
import org.nhathm.event.DomainEventPublisher;
import org.nhathm.event.EventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author nhathm
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class ContentGatewayImpl
        extends ServiceImpl<ContentMapper, ContentDO> implements ContentGateway {

    private final ContentConvertor contentConvertor;

    private final EventPublisher eventPublisher;

    @Override
    public void addContent(Content content) {
        ContentDO contentDO = contentConvertor.toDataObject(content);
        this.save(contentDO);

        content.setId(contentDO.getId());

        // publish ContentCreatedEvent
        ContentCreatedEvent event = new ContentCreatedEvent();
        event.setContentId(contentDO.getId());
        event.setProjectId(contentDO.getProjectId());
        eventPublisher.publish(event);
    }

    @Override
    public void updateContent(Content content) {
        ContentDO contentDO = contentConvertor.toDataObject(content);
        log.info("updateContent: {}", contentDO);
        this.baseMapper.updateById(contentDO);
    }

    @Override
    public void deleteContent(String id) {
        this.removeById(id);

        // publish ContentDeletedEvent
        ContentDeletedEvent event = new ContentDeletedEvent();
        event.setContentId(id);
        eventPublisher.publish(event);
    }

    @Override
    public Content getById(String id) {
        return contentConvertor.toEntity(this.baseMapper.selectById(id));
    }

    @Override
    public Page<Content> listByPage(String projectId, int pageIndex, int pageSize, String searchKey) {
        Page<ContentDO> page = new Page<>(pageIndex, pageSize);
        page.setOrders(List.of(new OrderItem().setAsc(true).setColumn("created_at")));
        IPage<ContentDO> contentPage = this.baseMapper.selectContentPageWithHologramAndImageTargetAndOwner(page, projectId, searchKey);

        List<Content> contentList = contentPage.getRecords().stream()
                .map(contentConvertor::toEntity)
                .collect(Collectors.toList());

        Page<Content> resultPage = new Page<>();
        resultPage.setRecords(contentList);
        resultPage.setCurrent(contentPage.getCurrent());
        resultPage.setSize(contentPage.getSize());
        resultPage.setTotal(contentPage.getTotal());
        resultPage.setPages(contentPage.getPages());

        return resultPage;
    }
}
