package org.pistonmc.entity.builder;

import org.pistonmc.entity.Entity;

public abstract class Builder<E extends Entity> {

    public abstract E build(BuilderArguments arguments);

}
