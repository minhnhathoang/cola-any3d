package org.nhathm.domain.project.gateway;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.nhathm.domain.project.database.ContentConvertor;
import org.nhathm.domain.project.database.ContentMapper;
import org.nhathm.domain.project.dataobject.ContentDO;
import org.nhathm.domain.project.entity.Content;
import org.springframework.stereotype.Component;

/**
 * @author nhathm
 */
@RequiredArgsConstructor
@Component
public class ContentGatewayImpl
        extends ServiceImpl<ContentMapper, ContentDO> implements ContentGateway {

    private final ContentConvertor contentConvertor;

    @Override
    public boolean isExists(Long id) {
        return this.lambdaQuery()
                .eq(ContentDO::getId, id)
                .count() > 0;
    }

    @Override
    public Long createContent(Content content) {
        ContentDO contentDO = contentConvertor.toDataObject(content);
        this.save(contentDO);
        return contentDO.getId();
    }

    @Override
    public Content getContent(Long id) {
        return contentConvertor.toEntity(this.getById(id));
    }
}
