package org.pistonmc.entity.builder;

import org.pistonmc.entity.Entity;

public interface Builder<E extends Entity> {

    public E build(BuilderArguments arguments);

}
