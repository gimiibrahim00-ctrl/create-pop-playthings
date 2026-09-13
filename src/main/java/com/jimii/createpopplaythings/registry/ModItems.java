package com.jimii.createpopplaythings.registry;

import com.jimii.createpopplaythings.CreatePopPlaythings;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreatePopPlaythings.MOD_ID);
    public static final DeferredItem<BlockItem> WIND_UP_ROBOT = item("wind_up_robot", ModBlocks.WIND_UP_ROBOT);
    public static final DeferredItem<BlockItem> SANDPIT = item("sandpit", ModBlocks.SANDPIT);
    public static final DeferredItem<BlockItem> CARDBOARD_CAR = item("cardboard_car", ModBlocks.CARDBOARD_CAR);
    public static final DeferredItem<BlockItem> CARDBOARD_ROCKET = item("cardboard_rocket", ModBlocks.CARDBOARD_ROCKET);
    public static final DeferredItem<BlockItem> CARDBOARD_CASTLE = item("cardboard_castle", ModBlocks.CARDBOARD_CASTLE);
    private static <T extends net.minecraft.world.level.block.Block> DeferredItem<BlockItem> item(String name, java.util.function.Supplier<T> block) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    private ModItems() {}
}
