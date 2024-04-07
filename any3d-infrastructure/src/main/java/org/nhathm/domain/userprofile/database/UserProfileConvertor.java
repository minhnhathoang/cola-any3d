package org.nhathm.domain.userprofile.database;

import domain.EntityConvertor;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.nhathm.domain.userprofile.dataobject.UserProfileDO;
import org.nhathm.domain.userprofile.entity.UserProfile;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserProfileConvertor extends EntityConvertor<UserProfile, UserProfileDO> {

}