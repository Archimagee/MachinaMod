package com.archimage.machina.event;

import com.archimage.machina.Machina;
import com.archimage.machina.event.loot.LooseStonesDropFromStone;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = Machina.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MachinaEventBusEvents {

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>>
                                                           event) {
        event.getRegistry().registerAll(
                new LooseStonesDropFromStone.Serializer().setRegistryName
                        (new ResourceLocation(Machina.MOD_ID,"loose_stones_from_stone"))
        );
    }
}