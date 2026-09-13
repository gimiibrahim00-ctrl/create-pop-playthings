package com.jimii.createpopplaythings.content;

import com.jimii.createpopplaythings.CreatePopPlaythings;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class PopToyBlock extends Block {
    public PopToyBlock(BlockBehaviour.Properties properties) { super(properties); }

    public static boolean isAddonToy(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block).getNamespace().equals(CreatePopPlaythings.MOD_ID);
    }
}
