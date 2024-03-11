package org.nhathm.user.database;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.nhathm.user.dataobject.UserDO;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {

}
