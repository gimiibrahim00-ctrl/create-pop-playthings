package com.jimii.createpopplaythings.mixin;

import com.jcsmp_pops.content.custom_entities.pops.PopFigure;
import com.jimii.createpopplaythings.integration.PopPlayWithToyGoal;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PopFigure.class)
public abstract class PopFigureGoalMixin extends PathfinderMob {
    protected PopFigureGoalMixin(EntityType<? extends PathfinderMob> type, Level level) { super(type, level); }
    @Inject(method = "registerGoals", at = @At("TAIL"), require = 1)
    private void createPopPlaythings$addToyGoal(CallbackInfo ci) { goalSelector.addGoal(8, new PopPlayWithToyGoal((PopFigure)(Object)this)); }
}
