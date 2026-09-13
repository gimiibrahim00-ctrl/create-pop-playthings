package com.jimii.createpopplaythings.registry;

import com.jimii.createpopplaythings.CreatePopPlaythings;
import com.jimii.createpopplaythings.content.CardboardToyBlock;
import com.jimii.createpopplaythings.content.SandpitBlock;
import com.jimii.createpopplaythings.content.WindUpRobotBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(CreatePopPlaythings.MOD_ID);
    public static final DeferredBlock<WindUpRobotBlock> WIND_UP_ROBOT = BLOCKS.register("wind_up_robot", () -> new WindUpRobotBlock(BlockBehaviour.Properties.of().strength(1.8f).sound(SoundType.METAL).noOcclusion()));
    public static final DeferredBlock<SandpitBlock> SANDPIT = BLOCKS.register("sandpit", () -> new SandpitBlock(BlockBehaviour.Properties.of().strength(0.8f).sound(SoundType.SAND).noOcclusion()));
    public static final DeferredBlock<CardboardToyBlock> CARDBOARD_CAR = cardboard("cardboard_car");
    public static final DeferredBlock<CardboardToyBlock> CARDBOARD_ROCKET = cardboard("cardboard_rocket");
    public static final DeferredBlock<CardboardToyBlock> CARDBOARD_CASTLE = cardboard("cardboard_castle");
    private static DeferredBlock<CardboardToyBlock> cardboard(String name) {
        return BLOCKS.register(name, () -> new CardboardToyBlock(BlockBehaviour.Properties.of().strength(0.5f).sound(SoundType.WOOL).noOcclusion()));
    }
    private ModBlocks() {}
}
