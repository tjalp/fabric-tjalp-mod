package net.tjalp.mod.tjalp.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.world.World;

public class BlobfishEntity extends WaterCreatureEntity {

    public BlobfishEntity(EntityType<? extends BlobfishEntity> entityType, World world) {
        super(entityType, world);
    }
}
