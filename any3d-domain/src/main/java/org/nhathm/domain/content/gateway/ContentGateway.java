package org.nhathm.domain.content.gateway;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.nhathm.domain.content.entity.Content;


public interface ContentGateway {

    void addContent(Content content);

    void updateContent(Content content);

    void deleteContent(String id);

    Content getById(String id);

    Page<Content> listByPage(String projectId, int pageIndex, int pageSize, String searchKey);
}
