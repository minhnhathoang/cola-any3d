package org.nhathm.domain.imagetarget.database;

import domain.EntityConvertor;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.nhathm.domain.imagetarget.dataobject.ImageTargetDO;
import org.nhathm.domain.imagetarget.entity.ImageTarget;


@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
@RequiredArgsConstructor
public abstract class ImageTargetConvertor implements EntityConvertor<ImageTarget, ImageTargetDO> {

}
