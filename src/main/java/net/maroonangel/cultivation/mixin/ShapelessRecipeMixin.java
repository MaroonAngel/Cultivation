package net.maroonangel.cultivation.mixin;

import net.maroonangel.cultivation.lib.CustomRecipeRemainder;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin({ShapelessRecipe.class})
public abstract class ShapelessRecipeMixin implements CraftingRecipe {

    public ShapelessRecipeMixin() {

    }

    //@Inject(method="getRemainingStacks", at = @At("HEAD"))
    public DefaultedList<ItemStack> getRemainingStacks(CraftingInventory inventory) {
        DefaultedList<ItemStack> list = DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY);

        for(int i = 0; i < list.size(); ++i) {
            Item item = inventory.getStack(i).getItem();
            if (item.hasRecipeRemainder()) {
                if (item instanceof CustomRecipeRemainder) {
                    list.set(i, ((CustomRecipeRemainder)item).getRecipeRemainder(inventory.getStack(i)));
                } else {
                    list.set(i, new ItemStack(item.getRecipeRemainder()));
                }
            }
        }

        return list;
    }

}
