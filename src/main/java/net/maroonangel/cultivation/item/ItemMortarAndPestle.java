package net.maroonangel.cultivation.item;

import net.maroonangel.cultivation.lib.CustomRecipeRemainder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemMortarAndPestle extends Item implements CustomRecipeRemainder {

    public ItemMortarAndPestle(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasRecipeRemainder() {
        return true;
    }

    @Override
    public ItemStack getRecipeRemainder(ItemStack stackIn) {
        ItemStack stack = stackIn.copy();
        stack.setDamage(stack.getDamage() + 1);

        if (stack.getDamage() >= stack.getMaxDamage())
            stack.decrement(1);

        return stack;
    }

}
