package com.jimii.createpopplaythings.content;

import com.jimii.createpopplaythings.registry.ModBlocks;
import com.jimii.createpopplaythings.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public final class WindUpRobotEntity extends PathfinderMob {
    private BlockPos home = BlockPos.ZERO;
    private Direction facing = Direction.NORTH;
    public WindUpRobotEntity(EntityType<? extends WindUpRobotEntity> type, Level level) { super(type, level); }
    public static AttributeSupplier.Builder attributes() { return createMobAttributes().add(Attributes.MAX_HEALTH, 6).add(Attributes.MOVEMENT_SPEED, 0.22); }
    public void setHome(BlockPos pos, Direction direction) { home = pos.immutable(); facing = direction; }
    @Override protected void registerGoals() {}
    @Override public void tick() {
        super.tick();
        if (!level().isClientSide && tickCount % 20 == 1) {
            Entity nearest = level().getEntities(this, getBoundingBox().inflate(10), this::isPop).stream().min(java.util.Comparator.comparingDouble(this::distanceToSqr)).orElse(null);
            if (nearest != null) getNavigation().moveTo(nearest, 1.0); else if (getNavigation().isDone()) getNavigation().moveTo(getX() + random.nextInt(9)-4, getY(), getZ()+random.nextInt(9)-4, 0.8);
        }
        if (!level().isClientSide && tickCount >= 400) restore();
    }
    private boolean isPop(Entity entity) { return BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).getNamespace().equals("jcsmp_pops"); }
    private void restore() {
        ServerLevel server = (ServerLevel) level();
        BlockState state = ModBlocks.WIND_UP_ROBOT.get().defaultBlockState().setValue(WindUpRobotBlock.FACING, facing);
        if (server.getBlockState(home).canBeReplaced()) server.setBlock(home, state, 3);
        else spawnAtLocation(ModItems.WIND_UP_ROBOT.get());
        discard();
    }
    @Override protected void addAdditionalSaveData(CompoundTag tag) { super.addAdditionalSaveData(tag); tag.putLong("Home", home.asLong()); tag.putString("Facing", facing.getName()); }
    @Override protected void readAdditionalSaveData(CompoundTag tag) { super.readAdditionalSaveData(tag); home = BlockPos.of(tag.getLong("Home")); facing = Direction.byName(tag.getString("Facing")); if (facing == null) facing = Direction.NORTH; }
    @Override public boolean canChangeDimensions(Level from, Level to) { return false; }
}
