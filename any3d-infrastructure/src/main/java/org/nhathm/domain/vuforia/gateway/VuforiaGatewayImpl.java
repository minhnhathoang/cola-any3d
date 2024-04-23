package org.nhathm.domain.vuforia.gateway;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.nhathm.domain.vuforia.database.VuforiaKeyMapper;
import org.nhathm.domain.vuforia.dataobject.VuforiaKeyDO;
import org.nhathm.domain.vuforia.entity.VuforiaKey;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Log4j2
@RequiredArgsConstructor
@Component
public class VuforiaGatewayImpl extends ServiceImpl<VuforiaKeyMapper, VuforiaKeyDO> implements VuforiaGateway {

    @Override
    public void addKey(VuforiaKey key) {
        var keyDO = new VuforiaKeyDO();
        BeanUtils.copyProperties(key, keyDO);
        this.save(keyDO);
    }

    @Override
    public void updateKey(VuforiaKey key) {
        var keyDO = new VuforiaKeyDO();
        BeanUtils.copyProperties(key, keyDO);
        this.updateById(keyDO);
    }

    @Override
    public VuforiaKey findKeyByProjectId(String projectId) {
        var keyDO = this.lambdaQuery()
                .eq(VuforiaKeyDO::getProjectId, projectId)
                .one();
        if (keyDO == null) {
            return null;
        }
        var key = new VuforiaKey();
        BeanUtils.copyProperties(keyDO, key);
        return key;
    }
}
