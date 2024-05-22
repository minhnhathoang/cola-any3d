package org.nhathm.domain.userprofile.database;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.yulichang.base.MPJBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.nhathm.domain.userprofile.dataobject.UserProfileDO;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author nhathm
 * @since 2024-04-06
 */
@Mapper
public interface UserProfileMapper extends MPJBaseMapper<UserProfileDO> {


    @Select("select * from user_profile where user_id = #{userId}")
    UserProfileDO selectByUserId(String userId);
}
