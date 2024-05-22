package org.nhathm.domain.project.database;

import domain.EntityConvertor;
import lombok.RequiredArgsConstructor;
import org.mapstruct.*;
import org.nhathm.domain.project.dataobject.ProjectDO;
import org.nhathm.domain.project.entity.Project;
import org.nhathm.domain.user.database.UserConvertor;
import org.nhathm.domain.user.database.UserMapper;
import org.nhathm.domain.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
@RequiredArgsConstructor
public abstract class ProjectConvertor implements EntityConvertor<Project, ProjectDO> {

    @Autowired
    protected UserMapper userMapper;

    @Autowired
    protected UserConvertor userConvertor;

    @Mappings({
            @Mapping(target = "ownerId", source = "owner.id")
    })
    public abstract ProjectDO toDataObject(Project project);
}
