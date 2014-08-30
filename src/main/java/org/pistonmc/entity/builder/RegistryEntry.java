package org.pistonmc.entity.builder;

import org.pistonmc.entity.Entity;

public class RegistryEntry<E extends Entity> {

    private Class<E> cls;
    private Builder<? extends E> builder;

    public RegistryEntry(Class<E> cls, Builder<? extends E> builder) {
        this.cls = cls;
        this.builder = builder;
    }

    public Class<E> getEntityType() {
        return cls;
    }

    public Builder<? extends E> getBuilder() {
        return builder;
    }

}
