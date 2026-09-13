package com.jimii.createpopplaythings.client;

import com.jimii.createpopplaythings.content.WindUpRobotEntity;
import com.jimii.createpopplaythings.registry.ModBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public final class RobotRenderer extends EntityRenderer<WindUpRobotEntity> {
    public RobotRenderer(EntityRendererProvider.Context context) { super(context); shadowRadius = 0.35f; }
    @Override public void render(WindUpRobotEntity entity, float yaw, float partialTick, PoseStack pose, MultiBufferSource buffers, int light) {
        pose.pushPose();
        pose.translate(-0.5, 0, -0.5);
        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(ModBlocks.WIND_UP_ROBOT.get().defaultBlockState(), pose, buffers, light, OverlayTexture.NO_OVERLAY);
        pose.popPose();
        super.render(entity, yaw, partialTick, pose, buffers, light);
    }
    @Override public ResourceLocation getTextureLocation(WindUpRobotEntity entity) { return ResourceLocation.withDefaultNamespace("textures/block/iron_block.png"); }
}
