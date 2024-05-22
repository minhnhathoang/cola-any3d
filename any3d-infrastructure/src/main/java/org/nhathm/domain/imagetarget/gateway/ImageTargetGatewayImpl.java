package org.nhathm.domain.imagetarget.gateway;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nhathm.domain.arsdk.ArSdkType;
import org.nhathm.domain.imagetarget.database.ImageTargetConvertor;
import org.nhathm.domain.imagetarget.database.ImageTargetMapper;
import org.nhathm.domain.imagetarget.dataobject.ImageTargetDO;
import org.nhathm.domain.imagetarget.entity.ImageTarget;
import org.nhathm.domain.userprofile.domainservice.VuforiaApiService;
import org.springframework.stereotype.Component;


@Slf4j
@RequiredArgsConstructor
@Component
public class ImageTargetGatewayImpl
        extends ServiceImpl<ImageTargetMapper, ImageTargetDO> implements ImageTargetGateway {

    private final ImageTargetConvertor imageTargetConvertor;

    private final VuforiaApiService vuforiaApiService;

    @Override
    public void addImageTarget(ImageTarget imageTarget) {
        ImageTargetDO imageTargetDO = imageTargetConvertor.toDataObject(imageTarget);
        this.save(imageTargetDO);
    }

    @Override
    public void deleteImageTarget(String id) {
        this.removeById(id);
    }
}
