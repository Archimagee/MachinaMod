package com.archimage.machina.block.entity.custom;

import com.archimage.machina.block.entity.MachinaBlockEntities;
import com.archimage.machina.item.MachinaItems;
import com.archimage.machina.screen.RockTumblerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RockTumblerBlockEntity extends BlockEntity implements MenuProvider
{

    private final ItemStackHandler itemHandler = new ItemStackHandler(2)
    {
        @Override
        protected void onContentsChanged(int slot)
        {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public RockTumblerBlockEntity(BlockPos pWorldPosition, BlockState pBlockState)
    {
        super(MachinaBlockEntities.ROCK_TUMBLER_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
    }

    @Override
    public Component getDisplayName()
    {
        return new TextComponent("Rock Tumbler");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer)
    {
        return new RockTumblerMenu(pContainerId, pInventory, this);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side)
    {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad()
    {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps()
    {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag)
    {
        tag.put("inventory", itemHandler.serializeNBT());
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt)
    {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
    }

    public void drops()
    {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++)
        {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, RockTumblerBlockEntity pBlockEntity)
    {
        if (checkRecipe(pBlockEntity) && hasNotReachedStackLimit(pBlockEntity))
        {
            tumble(pBlockEntity);
        }
    }

    private static void tumble(RockTumblerBlockEntity entity)
    {
        entity.itemHandler.extractItem(0, 2, false);

        entity.itemHandler.setStackInSlot(1, new ItemStack(MachinaItems.POLISHED_STONES.get(), entity.itemHandler.getStackInSlot(2).getCount() + 2));
    }

    private static boolean checkRecipe(RockTumblerBlockEntity entity)
    {
        boolean hasRocksInSlot = entity.itemHandler.getStackInSlot(0).getItem() == MachinaItems.LOOSE_STONES.get();
        boolean hasSufficientItemsInSlot = entity.itemHandler.getStackInSlot(0).getCount() >= 2;

        return hasRocksInSlot && hasSufficientItemsInSlot;
    }

    private static boolean hasNotReachedStackLimit(RockTumblerBlockEntity entity)
    {
        return entity.itemHandler.getStackInSlot(1).getCount() < entity.itemHandler.getStackInSlot(1).getMaxStackSize();
    }
}
