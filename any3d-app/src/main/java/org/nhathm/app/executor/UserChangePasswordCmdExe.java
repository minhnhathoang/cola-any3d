package org.nhathm.app.executor;

import com.alibaba.cola.domain.DomainFactory;
import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import org.nhathm.config.MinioConfig;
import org.nhathm.domain.user.database.UserConvertor;
import org.nhathm.domain.user.database.UserMapper;
import org.nhathm.domain.user.gateway.UserGateway;
import org.nhathm.domain.userprofile.entity.UserProfile;
import org.nhathm.domain.userprofile.gateway.UserProfileGateway;
import org.nhathm.dto.command.UserChangePasswordCmd;
import org.nhathm.dto.command.UserProfileUpdateCmd;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class UserChangePasswordCmdExe {

    private final UserGateway userGateway;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final UserConvertor userConvertor;

    public Response execute(UserChangePasswordCmd cmd) {
        var user = userMapper.selectById(cmd.getUserId());
        if (!passwordEncoder.matches(cmd.getOldPassword(), user.getHashedPassword())) {
            return Response.buildFailure("404", "Old password is incorrect");
        }
        user.setHashedPassword(passwordEncoder.encode(cmd.getNewPassword()));
        userGateway.update(userConvertor.toEntity(user));
        return Response.buildSuccess();
    }
}
