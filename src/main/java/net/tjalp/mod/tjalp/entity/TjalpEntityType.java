package net.tjalp.mod.tjalp.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tjalp.mod.tjalp.Tjalp;

import java.util.Optional;

public class TjalpEntityType {

    private static <T extends Entity> EntityType<T> register(String id, EntityType<T> type) {
        return register(new Identifier(Tjalp.MOD_ID, id), type);
    }

    private static <T extends Entity> EntityType<T> register(Identifier id, EntityType<T> type) {
        return Registry.register(Registry.ENTITY_TYPE, id, type);
    }

    public static Identifier getId(EntityType<?> type) {
        return Registry.ENTITY_TYPE.getId(type);
    }

    public static Optional<EntityType<?>> get(Identifier id) {
        return Registry.ENTITY_TYPE.getOrEmpty(id);
    }

    public static void registerEntityTypes() {

    }
}
