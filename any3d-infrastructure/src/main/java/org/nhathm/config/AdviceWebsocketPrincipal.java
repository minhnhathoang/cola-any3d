package org.nhathm.config;

import java.security.Principal;

/**
 * @author nhathm
 */
public class AdviceWebsocketPrincipal implements Principal {
    private String id;

    public AdviceWebsocketPrincipal(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return this.id;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
