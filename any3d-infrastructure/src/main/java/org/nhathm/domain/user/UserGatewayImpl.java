package org.nhathm.domain.user;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nhathm.domain.user.entity.User;
import org.nhathm.domain.user.gateway.UserGateway;
import org.nhathm.domain.user.database.UserMapper;
import org.nhathm.domain.user.dataobject.UserDO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nhathm
 * @since 2024-04-05
 */
@Service
public class UserGatewayImpl extends ServiceImpl<UserMapper, UserDO> implements UserGateway {

    @Override
    public boolean existsByUsername(String username) {
        return false;
    }

    @Override
    public void create(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
