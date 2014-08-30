package org.pistonmc.entity.builder;

import org.pistonmc.entity.Entity;

public class RegistryEntry<E extends Entity> {

    private Class<E> cls;
    private Builder<E> builder;

    public RegistryEntry(Class<E> cls, Builder<E> builder) {
        this.cls = cls;
        this.builder = builder;
    }

    public Class<E> getEntityType() {
        return cls;
    }

    public Builder<E> getBuilder() {
        return builder;
    }

}
