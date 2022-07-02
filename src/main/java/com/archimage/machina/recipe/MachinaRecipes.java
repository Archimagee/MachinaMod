package com.archimage.machina.recipe;

import com.archimage.machina.Machina;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MachinaRecipes
{
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Machina.MOD_ID);

    public static final RegistryObject<RecipeSerializer<RockTumblerRecipe>> ROCK_TUMBLING_SERIALIZER =
            SERIALIZERS.register("rock_tumbling", () -> RockTumblerRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}