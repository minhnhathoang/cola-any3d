package org.nhathm.domain.user.database;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.nhathm.domain.user.dataobject.UserDO;

import java.util.Optional;


@Mapper
public interface UserMapper extends MPJBaseMapper<UserDO> {

    @Select("SELECT * FROM user WHERE username = #{username}")
    Optional<UserDO> selectByUsername(String username);

    @Select("SELECT * FROM user WHERE id = #{id}")
    UserDO findById(String id);


    @Select("SELECT u.* " +
            "FROM user u " +
            "INNER JOIN project p ON u.id = p.owner_id " +
            "WHERE p.id = #{projectId}")
    UserDO selectOwnerByProjectId(@Param("projectId") String projectId);
}
