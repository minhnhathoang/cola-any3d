package org.nhathm.domain.hologram.database;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.nhathm.domain.hologram.dataobject.HologramDO;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author nhathm
 * @since 2024-04-06
 */
@Mapper
public interface HologramMapper extends MPJBaseMapper<HologramDO> {

    @Select("SELECT * FROM hologram WHERE content_id = #{contentId}")
    HologramDO selectByContentId(String contentId);
}
