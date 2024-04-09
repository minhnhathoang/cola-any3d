package org.nhathm.domain.project.database;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.nhathm.domain.project.dataobject.ContentDO;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author nhathm
 * @since 2024-04-06
 */
@Mapper
public interface ContentMapper extends BaseMapper<ContentDO> {

}
