package net.tjalp.mod.tjalp.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tjalp.mod.tjalp.Tjalp;

import java.util.Optional;

public class TjalpEntityType {

    public static EntityType<BlobfishEntity> BLOBFISH;

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
        BLOBFISH = register("blobfish", FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, BlobfishEntity::new)
                .dimensions(EntityDimensions.fixed(0.55F, 0.30F))
                .build());
    }
}
