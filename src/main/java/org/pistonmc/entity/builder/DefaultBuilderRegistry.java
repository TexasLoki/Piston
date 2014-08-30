package org.pistonmc.entity.builder;

import org.pistonmc.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class DefaultBuilderRegistry implements BuilderRegistry {

    private List<RegistryEntry> entries;

    public DefaultBuilderRegistry() {
        this.entries = new ArrayList<>();
    }

    @Override
    public <E extends Entity> E build(Class<E> type, BuilderArguments arguments) {
        Builder<? extends E> builder = get(type);
        return builder.build(arguments);
    }

    public RegistryEntry getEntry(Class<? extends Entity> type) {
        for(RegistryEntry entry : entries) {
            if(entry.getEntityType().equals(type)) {
                return entry;
            }
        }

        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E extends Entity> Builder<? extends E> get(Class<E> type) {
        for(RegistryEntry entry : entries) {
            if(entry.getEntityType().equals(type)) {
                return (Builder<? extends E>) entry.getBuilder();
            }
        }

        return null;
    }

    @Override
    public <E extends Entity> void register(Class<E> cls, Builder<? extends E> builder) {
        entries.add(new RegistryEntry<>(cls, builder));
    }

    @Override
    public void unregister(Class<? extends Entity> cls) {
        RegistryEntry entry = getEntry(cls);
        if(entry != null) {
            entries.remove(entry);
        }
    }

}
