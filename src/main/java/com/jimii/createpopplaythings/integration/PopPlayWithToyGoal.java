package com.jimii.createpopplaythings.integration;

import com.jcsmp_pops.content.custom_entities.pops.PopFigure;
import com.jimii.createpopplaythings.CreatePopPlaythings;
import com.jimii.createpopplaythings.content.RobotAssembly;
import com.jimii.createpopplaythings.content.SandCreation;
import com.jimii.createpopplaythings.content.SandpitBlock;
import com.jimii.createpopplaythings.content.WindUpRobotBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import java.util.EnumSet;

public final class PopPlayWithToyGoal extends Goal {
    private static final TagKey<Block> TOYS = TagKey.create(net.minecraft.core.registries.Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(CreatePopPlaythings.MOD_ID, "pop_toys"));
    private final PopFigure pop;
    private BlockPos target;
    private int playTicks;

    public PopPlayWithToyGoal(PopFigure pop) { this.pop = pop; setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK)); }

    @Override public boolean canUse() {
        if (pop.isPassenger() || pop.getRandom().nextInt(160) != 0) return false;
        target = findToy(7);
        return target != null;
    }
    @Override public boolean canContinueToUse() { return target != null && playTicks < 100 && safe(pop.level().getBlockState(target)); }
    @Override public void start() { playTicks = 0; pop.getNavigation().moveTo(target.getX()+0.5, target.getY(), target.getZ()+0.5, 0.8); }
    @Override public void stop() { target = null; playTicks = 0; pop.getNavigation().stop(); }
    @Override public void tick() {
        if (target == null) return;
        double distance = pop.distanceToSqr(target.getX()+0.5, target.getY()+0.5, target.getZ()+0.5);
        if (distance > 4) {
            if (pop.getNavigation().isDone() && playTicks % 20 == 0) pop.getNavigation().moveTo(target.getX()+0.5, target.getY(), target.getZ()+0.5, 0.8);
            playTicks++;
            return;
        }
        pop.getNavigation().stop();
        pop.getLookControl().setLookAt(target.getX()+0.5, target.getY()+0.5, target.getZ()+0.5);
        playTicks++;
        BlockState state = pop.level().getBlockState(target);
        if (state.hasProperty(WindUpRobotBlock.ASSEMBLY)) {
            if (playTicks == 25) pop.level().setBlock(target, state.setValue(WindUpRobotBlock.ASSEMBLY, RobotAssembly.PARTS), 3);
            if (playTicks == 65) pop.level().setBlock(target, state.setValue(WindUpRobotBlock.ASSEMBLY, pop.getRandom().nextFloat() < 0.05f ? RobotAssembly.INCORRECT : RobotAssembly.CORRECT), 3);
        } else if (state.hasProperty(SandpitBlock.CREATION) && playTicks % 30 == 0) {
            SandCreation[] choices = SandCreation.values();
            pop.level().setBlock(target, state.setValue(SandpitBlock.CREATION, choices[1 + pop.getRandom().nextInt(choices.length - 1)]), 3);
        }
    }
    private BlockPos findToy(int radius) {
        BlockPos origin = pop.blockPosition();
        BlockPos.MutableBlockPos cursor = new BlockPos.MutableBlockPos();
        BlockPos best = null; double bestDistance = Double.MAX_VALUE;
        for (int y=-2; y<=2; y++) for (int x=-radius; x<=radius; x++) for (int z=-radius; z<=radius; z++) {
            cursor.setWithOffset(origin, x, y, z);
            if (safe(pop.level().getBlockState(cursor))) {
                double d = cursor.distSqr(origin);
                if (d < bestDistance) { bestDistance = d; best = cursor.immutable(); }
            }
        }
        return best;
    }
    private static boolean safe(BlockState state) {
        return state.is(TOYS) && BuiltInRegistries.BLOCK.getKey(state.getBlock()).getNamespace().equals(CreatePopPlaythings.MOD_ID);
    }
}
