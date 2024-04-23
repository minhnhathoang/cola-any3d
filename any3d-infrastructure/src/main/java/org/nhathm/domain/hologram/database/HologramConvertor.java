package org.nhathm.domain.hologram.database;

import domain.EntityConvertor;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.nhathm.domain.hologram.dataobject.HologramDO;
import org.nhathm.domain.hologram.entity.Hologram;


@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HologramConvertor extends EntityConvertor<Hologram, HologramDO> {

}
