package org.nhathm.domain.project.gateway;


import org.nhathm.domain.project.entity.Content;

public interface ContentGateway {

    boolean isExists(Long id);

    Long createContent(Content content);

    Content getContent(Long id);
}
