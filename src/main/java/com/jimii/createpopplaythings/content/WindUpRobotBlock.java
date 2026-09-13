package com.jimii.createpopplaythings.content;

import com.jimii.createpopplaythings.registry.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.item.ItemStack;

public final class WindUpRobotBlock extends PopToyBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<RobotAssembly> ASSEMBLY = EnumProperty.create("assembly", RobotAssembly.class);
    private static final VoxelShape SHAPE = box(3, 0, 3, 13, 16, 13);
    public WindUpRobotBlock(BlockBehaviour.Properties properties) {
        super(properties);
        registerDefaultState(stateDefinition.any().setValue(FACING, net.minecraft.core.Direction.NORTH).setValue(ASSEMBLY, RobotAssembly.CORRECT));
    }
    @Override protected void createBlockStateDefinition(StateDefinition.Builder<net.minecraft.world.level.block.Block, BlockState> builder) { builder.add(FACING, ASSEMBLY); }
    @Override public BlockState getStateForPlacement(BlockPlaceContext context) { return defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()); }
    @Override protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) { return SHAPE; }
    @Override protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (state.getValue(ASSEMBLY) != RobotAssembly.CORRECT) return InteractionResult.PASS;
        if (level instanceof ServerLevel server) {
            long active = server.getEntitiesOfClass(WindUpRobotEntity.class, new net.minecraft.world.phys.AABB(pos).inflate(16, 384, 16)).size();
            if (active >= 4) return InteractionResult.FAIL;
            WindUpRobotEntity robot = ModEntities.WIND_UP_ROBOT.get().create(server);
            if (robot != null) {
                robot.moveTo(pos.getX()+0.5, pos.getY(), pos.getZ()+0.5, state.getValue(FACING).toYRot(), 0);
                robot.setHome(pos, state.getValue(FACING));
                level.removeBlock(pos, false);
                server.addFreshEntity(robot);
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
    @Override protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (state.getValue(ASSEMBLY) == RobotAssembly.INCORRECT && net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(stack.getItem()).toString().equals("create:wrench")) {
            if (!level.isClientSide) level.setBlock(pos, state.setValue(ASSEMBLY, RobotAssembly.CORRECT), 3);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hit);
    }
}
