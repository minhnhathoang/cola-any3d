package org.nhathm.domain.user.database;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.nhathm.domain.user.dataobject.UserDO;

import java.util.Optional;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {

    @Select("SELECT * FROM user WHERE username = #{username}")
    Optional<UserDO> selectByUsername(String username);
}
