package domain;

import org.mapstruct.MappingTarget;


/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
public interface EntityConvertor<Entity, DO> {

    Entity toEntity(DO dataObject);

    DO toDataObject(Entity entity);

    void updateDOFromEntity(Entity entity, @MappingTarget DO dataObject);
}