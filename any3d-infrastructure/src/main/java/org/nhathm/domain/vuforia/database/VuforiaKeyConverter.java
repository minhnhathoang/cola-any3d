package org.nhathm.domain.vuforia.database;

import domain.EntityConvertor;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.nhathm.domain.vuforia.dataobject.VuforiaKeyDO;
import org.nhathm.domain.vuforia.entity.VuforiaKey;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface VuforiaKeyConverter extends EntityConvertor<VuforiaKey, VuforiaKeyDO> {

}