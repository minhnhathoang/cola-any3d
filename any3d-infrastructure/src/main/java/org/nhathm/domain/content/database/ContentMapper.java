package org.nhathm.domain.content.database;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.*;
import org.nhathm.domain.content.dataobject.ContentDO;

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
public interface ContentMapper extends MPJBaseMapper<ContentDO> {

    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "id", property = "hologram",
                    one = @One(select = "org.nhathm.domain.hologram.database.HologramMapper.selectByContentId")),
            @Result(column = "id", property = "imageTarget",
                    one = @One(select = "org.nhathm.domain.imagetarget.database.ImageTargetMapper.selectByContentId")),
    })
    @Select("SELECT * FROM content WHERE project_id = #{projectId} AND deleted = 0")
    List<ContentDO> selectByProjectId(String projectId);

    @Select("SELECT * FROM content WHERE project_id = #{projectId} AND deleted = 0 AND name LIKE CONCAT('%', #{searchKey}, '%')")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "metadata", property = "metadata"),
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "last_modified_at", property = "lastModifiedAt"),
            @Result(column = "project_id", property = "projectId"),
            @Result(column = "id", property = "hologram",
                    one = @One(select = "org.nhathm.domain.hologram.database.HologramMapper.selectByContentId")),
            @Result(column = "id", property = "imageTarget",
                    one = @One(select = "org.nhathm.domain.imagetarget.database.ImageTargetMapper.selectByContentId")),
            @Result(column = "project_id", property = "owner",
                    one = @One(select = "org.nhathm.domain.user.database.UserMapper.selectOwnerByProjectId"))

    })
    IPage<ContentDO> selectContentPageWithHologramAndImageTargetAndOwner(
            Page<?> page,
            @Param("projectId") String projectId,
            @Param("searchKey") String searchKey
    );

    @Select("SELECT COUNT(*) FROM content WHERE project_id = #{projectId} AND deleted = 0")
    int countByProjectId(String projectId);

    @Select("SELECT * FROM content WHERE id = #{id}")
    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "metadata", property = "metadata"),
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "last_modified_at", property = "lastModifiedAt"),
            @Result(column = "project_id", property = "projectId"),
            @Result(column = "id", property = "hologram",
                    one = @One(select = "org.nhathm.domain.hologram.database.HologramMapper.selectByContentId")),
            @Result(column = "id", property = "imageTarget",
                    one = @One(select = "org.nhathm.domain.imagetarget.database.ImageTargetMapper.selectByContentId")),
            @Result(column = "project_id", property = "owner",
                    one = @One(select = "org.nhathm.domain.user.database.UserMapper.selectOwnerByProjectId")),
            @Result(column = "deleted", property = "deleted"),
    })
    ContentDO selectById(String id);
}
