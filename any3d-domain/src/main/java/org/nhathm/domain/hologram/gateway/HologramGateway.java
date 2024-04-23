package org.nhathm.domain.hologram.gateway;


import org.nhathm.domain.hologram.entity.Hologram;

public interface HologramGateway {

    void addHologram(Hologram hologram);

    Hologram getById(String id);
}
