package domain;

import org.mapstruct.MappingTarget;


public interface EntityConvertor<Entity, DO> {

    Entity toEntity(DO dataObject);

    DO toDataObject(Entity entity);

    void updateDOFromEntity(Entity entity, @MappingTarget DO dataObject);
}