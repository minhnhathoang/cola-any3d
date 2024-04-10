package org.nhathm.app.userprofile.assembler;

import domain.COAssembler;
import org.mapstruct.Mapper;
import org.nhathm.domain.userprofile.entity.UserProfile;
import org.nhathm.user.dto.clientobject.UserProfileCO;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Mapper(componentModel = "spring",
        nullValueCheckStrategy = org.mapstruct.NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
public interface UserProfileAssembler extends COAssembler<UserProfileCO, UserProfile> {

}
