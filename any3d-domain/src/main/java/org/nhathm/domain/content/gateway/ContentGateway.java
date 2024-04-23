package org.nhathm.domain.content.gateway;


import org.nhathm.domain.content.entity.Content;

public interface ContentGateway {

    void addContent(Content content);

    Content getById(String id);
}
