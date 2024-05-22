package org.nhathm.domain.project.database;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.mapping.FetchType;
import org.nhathm.domain.project.dataobject.ProjectDO;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author nhathm
 * @since 2024-04-06
 */
@Mapper
public interface ProjectMapper extends MPJBaseMapper<ProjectDO> {

    @Select("SELECT * FROM project WHERE id = #{id} AND deleted = 0")
    @Results(id = "projectDOResultMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "ownerId", column = "owner_id"),
            @Result(property = "owner", column = "owner_id", one = @One(select = "org.nhathm.domain.user.database.UserMapper.selectById")),
            @Result(property = "contents", column = "id", many = @Many(select = "org.nhathm.domain.content.database.ContentMapper.selectByProjectId"))
    })
    ProjectDO getById(@Param("id") String id);

    @ResultMap("projectDOResultMap")
    @Select("SELECT * FROM project WHERE owner_id = #{ownerId} AND deleted = 0")
    List<ProjectDO> selectByOwnerId(String ownerId);
}
