package org.nhathm.user.database;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.nhathm.domain.user.entity.User;
import org.nhathm.user.dataobject.UserDO;
import org.ylzl.eden.cola.domain.EntityConvertor;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserConvertor extends EntityConvertor<User, UserDO> {
}