package org.nhathm.domain.imagetarget.database;

import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.nhathm.domain.imagetarget.dataobject.ImageTargetDO;


@Mapper
public interface ImageTargetMapper extends MPJBaseMapper<ImageTargetDO> {

    @Select("SELECT * FROM image_target WHERE content_id = #{contentId}")
    ImageTargetDO selectByContentId(String contentId);
}
