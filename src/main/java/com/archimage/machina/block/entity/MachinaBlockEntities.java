package com.archimage.machina.block.entity;

import com.archimage.machina.Machina;
import com.archimage.machina.block.MachinaBlocks;
import com.archimage.machina.block.custom.RockTumblerBlock;
import com.archimage.machina.block.entity.custom.RockTumblerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MachinaBlockEntities
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Machina.MOD_ID);

    public static final RegistryObject<BlockEntityType<RockTumblerBlockEntity>> ROCK_TUMBLER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("rock_tumbler_block_entity", () -> BlockEntityType.Builder.of(RockTumblerBlockEntity::new, MachinaBlocks.ROCK_TUMBLER_BLOCK.get()).build(null));


    public static void register(IEventBus eventBus)
    {
        BLOCK_ENTITIES.register(eventBus);
    }
}