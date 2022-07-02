package com.archimage.machina.event.loot;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

public class LooseStonesDropFromStone extends LootModifier {

    private final Item addition;

    protected LooseStonesDropFromStone(LootItemCondition[] conditionsIn, Item addition)
    {
        super(conditionsIn);
        this.addition = addition;
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context)
    {
        final int max = 4;
        final int min = 1;
        int numberDropped = new Random().nextInt((max - min) + 1) + min;
        for(int i = 1; i < numberDropped; i++)
        {
            generatedLoot.add(new ItemStack(addition, 1));
        }
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<LooseStonesDropFromStone>
    {

        @Override
        public LooseStonesDropFromStone read(ResourceLocation name, JsonObject object, LootItemCondition[] conditionsIn)
        {
            Item addition = ForgeRegistries.ITEMS.getValue(new ResourceLocation(GsonHelper.getAsString(object, "addition")));
            return new LooseStonesDropFromStone(conditionsIn, addition);
        }

        @Override
        public JsonObject write(LooseStonesDropFromStone instance)
        {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("addition", ForgeRegistries.ITEMS.getKey(instance.addition).toString());
            return json;
        }
    }
}
