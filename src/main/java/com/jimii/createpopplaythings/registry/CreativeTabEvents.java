package com.jimii.createpopplaythings.registry;

import com.jimii.createpopplaythings.CreatePopPlaythings;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@EventBusSubscriber(modid = CreatePopPlaythings.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public final class CreativeTabEvents {
    @SubscribeEvent public static void addItems(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(ModItems.WIND_UP_ROBOT);
            event.accept(ModItems.SANDPIT);
            event.accept(ModItems.CARDBOARD_CAR);
            event.accept(ModItems.CARDBOARD_ROCKET);
            event.accept(ModItems.CARDBOARD_CASTLE);
        }
    }
    private CreativeTabEvents() {}
}
