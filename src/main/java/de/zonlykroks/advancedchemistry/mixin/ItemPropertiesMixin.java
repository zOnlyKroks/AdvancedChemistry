package de.zonlykroks.advancedchemistry.mixin;

import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Item.class)
public interface ItemPropertiesMixin {

    @Accessor("maxCount")
    @Mutable
    void setItemMaxCount(int maxCount);

    @Accessor("maxDamage")
    @Mutable
    void setItemMaxDamage(int maxDamage);

    @Accessor("rarity")
    @Mutable
    void setItemRarity(Rarity rarity);

    @Accessor("fireproof")
    @Mutable
    void setItemFireproof(boolean fireproof);
}
