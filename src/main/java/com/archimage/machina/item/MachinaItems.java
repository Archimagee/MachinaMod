package com.archimage.machina.item;

import com.archimage.machina.Machina;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MachinaItems
{

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Machina.MOD_ID);

    public static final RegistryObject<Item> LOOSE_STONES =
            ITEMS.register("loose_stones", () -> new Item(new Item.Properties().tab(MachinaCreativeModeTab.MACHINA_CREATIVE_TAB)));
    public static final RegistryObject<Item> POLISHED_STONES =
            ITEMS.register("polished_stones", () -> new Item(new Item.Properties().tab(MachinaCreativeModeTab.MACHINA_CREATIVE_TAB)));

    public static final RegistryObject<Item> WHEAT_GRAIN =
            ITEMS.register("wheat_grain", () -> new Item(new Item.Properties().tab(MachinaCreativeModeTab.MACHINA_CREATIVE_TAB)));
    public static final RegistryObject<Item> FLOUR =
            ITEMS.register("flour", () -> new Item(new Item.Properties().tab(MachinaCreativeModeTab.MACHINA_CREATIVE_TAB)));
    public static final RegistryObject<Item> DOUGH =
            ITEMS.register("dough", () -> new Item(new Item.Properties().tab(MachinaCreativeModeTab.MACHINA_CREATIVE_TAB).food(MachinaFoods.DOUGH)));
    public static final RegistryObject<Item> FLATBREAD =
            ITEMS.register("flatbread", () -> new Item(new Item.Properties().tab(MachinaCreativeModeTab.MACHINA_CREATIVE_TAB).food(MachinaFoods.FLATBREAD)));
    public static final RegistryObject<Item> MORTAR_AND_PESTLE =
            ITEMS.register("mortar_and_pestle", () -> new Item(new Item.Properties().tab(MachinaCreativeModeTab.MACHINA_CREATIVE_TAB)));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}