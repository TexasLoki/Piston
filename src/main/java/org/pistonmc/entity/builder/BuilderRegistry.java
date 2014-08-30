package org.pistonmc.entity.builder;

import org.pistonmc.entity.Entity;

public interface BuilderRegistry {

    public <E extends Entity> E build(Class<E> type, BuilderArguments arguments);

    public <E extends Entity> Builder<E> get(Class<E> type);

    public <E extends Entity> void register(Class<E> cls, Builder<E> builder);

}
