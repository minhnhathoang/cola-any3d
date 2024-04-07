package org.nhathm.domain.userprofile.database;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
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
public interface UserProfileMapper extends BaseMapper<UserProfileDO> {

}
