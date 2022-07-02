package com.archimage.machina.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class MachinaCreativeModeTab {

    public static final CreativeModeTab MACHINA_CREATIVE_TAB = new CreativeModeTab("machina_tab")
    {
        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(MachinaItems.LOOSE_STONES.get());
        }
    };

}
