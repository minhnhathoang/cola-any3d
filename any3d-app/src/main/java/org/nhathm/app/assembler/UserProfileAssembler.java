package org.nhathm.app.assembler;

import domain.COAssembler;
import org.mapstruct.Mapper;
import org.nhathm.domain.userprofile.entity.UserProfile;
import org.nhathm.dto.clientobject.UserProfileCO;


@Mapper(componentModel = "spring",
        nullValueCheckStrategy = org.mapstruct.NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
public interface UserProfileAssembler extends COAssembler<UserProfileCO, UserProfile> {

}
