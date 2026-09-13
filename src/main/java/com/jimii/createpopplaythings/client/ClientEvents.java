package com.jimii.createpopplaythings.client;

import com.jimii.createpopplaythings.CreatePopPlaythings;
import com.jimii.createpopplaythings.registry.ModEntities;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = CreatePopPlaythings.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientEvents {
    @SubscribeEvent public static void renderers(EntityRenderersEvent.RegisterRenderers event) { event.registerEntityRenderer(ModEntities.WIND_UP_ROBOT.get(), RobotRenderer::new); }
    private ClientEvents() {}
}
