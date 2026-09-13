package com.jimii.createpopplaythings.registry;

import com.jimii.createpopplaythings.CreatePopPlaythings;
import com.jimii.createpopplaythings.content.WindUpRobotEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, CreatePopPlaythings.MOD_ID);
    public static final DeferredHolder<EntityType<?>, EntityType<WindUpRobotEntity>> WIND_UP_ROBOT = ENTITIES.register("wind_up_robot", () -> EntityType.Builder.of(WindUpRobotEntity::new, MobCategory.MISC).sized(0.6f, 0.95f).clientTrackingRange(8).updateInterval(3).build("wind_up_robot"));
    public static void createAttributes(EntityAttributeCreationEvent event) { event.put(WIND_UP_ROBOT.get(), WindUpRobotEntity.attributes().build()); }
    private ModEntities() {}
}
