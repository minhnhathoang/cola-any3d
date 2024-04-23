package org.nhathm.domain.hologram.gateway;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.nhathm.domain.hologram.database.HologramConvertor;
import org.nhathm.domain.hologram.database.HologramMapper;
import org.nhathm.domain.hologram.dataobject.HologramDO;
import org.nhathm.domain.hologram.entity.Hologram;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class HologramGatewayImpl extends ServiceImpl<HologramMapper, HologramDO> implements HologramGateway {

    private final HologramConvertor hologramConvertor;

    @Override
    public void addHologram(Hologram hologram) {
        HologramDO hologramDO = hologramConvertor.toDataObject(hologram);
        this.save(hologramDO);

        log.info("Hologram added: {}", hologramDO);
    }

    @Override
    public Hologram getById(String id) {
        HologramDO hologramDO = super.getById(id);
        return hologramConvertor.toEntity(hologramDO);
    }
}
