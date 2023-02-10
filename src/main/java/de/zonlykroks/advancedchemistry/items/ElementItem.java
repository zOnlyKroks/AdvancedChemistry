package de.zonlykroks.advancedchemistry.items;

import com.mojang.serialization.Lifecycle;
import de.zonlykroks.advancedchemistry.AdvancedChemistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;

public class ElementItem extends Item {

    private final String name;

    private final int elementNumber;
    public ElementItem(String name,int elementNumber) {
        super(new FabricItemSettings().group(AdvancedChemistry.customItemGroup));
        this.name = name;
        this.elementNumber = elementNumber;
    }
}
