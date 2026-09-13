package com.jimii.createpopplaythings;

import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.IEventBus;
import com.jimii.createpopplaythings.registry.ModBlocks;
import com.jimii.createpopplaythings.registry.ModEntities;
import com.jimii.createpopplaythings.registry.ModItems;

@Mod(CreatePopPlaythings.MOD_ID)
public final class CreatePopPlaythings {
    public static final String MOD_ID = "create_pop_playthings";

    public CreatePopPlaythings(IEventBus modBus) {
        ModBlocks.BLOCKS.register(modBus);
        ModItems.ITEMS.register(modBus);
        ModEntities.ENTITIES.register(modBus);
        modBus.addListener(ModEntities::createAttributes);
    }
}
