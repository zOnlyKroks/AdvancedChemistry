package de.zonlykroks.advancedchemistry;

import de.zonlykroks.advancedchemistry.blocks.BlockInit;
import de.zonlykroks.advancedchemistry.config.ChemItemPropertyConfig;
import de.zonlykroks.advancedchemistry.config.SimpleChemConfig;
import de.zonlykroks.advancedchemistry.element.Element;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class AdvancedChemistry implements ModInitializer {

    public static final SimpleChemConfig itemConfig = new SimpleChemConfig();
    public static final ChemItemPropertyConfig rarityConfig = new ChemItemPropertyConfig();

    //Identifier, Mapped to registered Element
    public static final Map<Identifier, Element> registryNameElementToElement = new HashMap<>();

    private static final ItemGroup ADVANCED_CHEMISTRY_CREATIVE_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(Blocks.GLASS))
            .displayName(Text.translatable("itemGroup.advancedchemistry.creative_group"))
            .entries((displayContext, entries) -> {
                registryNameElementToElement.forEach((identifier, element) -> {
                    entries.add(new ItemStack(element));
                });
            })
            .build();

    @Override
    public void onInitialize() {
        itemConfig.loadOrCreateConfig();
        itemConfig.convertCSVToJSON(itemConfig.configFileCSV);
        itemConfig.convertJSONToString();

        itemConfig.parseItems();

        rarityConfig.loadOrCreateConfig();
        rarityConfig.loadAndChangeItemProperties();

        new BlockInit();

        Registry.register(Registries.ITEM_GROUP, new Identifier("advancedchemistry", "creative_group"), ADVANCED_CHEMISTRY_CREATIVE_GROUP);
    }
}
