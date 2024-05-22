package org.nhathm.domain.vuforia.database;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.nhathm.domain.vuforia.dataobject.VuforiaKeyDO;
import org.nhathm.domain.vuforia.entity.VuforiaKey;
import org.nhathm.dto.clientobject.VuforiaKeyCO;

@Mapper
public interface VuforiaKeyMapper extends MPJBaseMapper<VuforiaKeyDO> {

    @Select("SELECT * FROM vuforia_key WHERE project_id = #{projectId}")
    VuforiaKeyDO selectByProjectId(String projectId);
}
