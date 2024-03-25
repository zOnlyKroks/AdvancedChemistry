package de.zonlykroks.advancedchemistry;

import de.zonlykroks.advancedchemistry.blocks.BlockInit;
import de.zonlykroks.advancedchemistry.config.ChemItemPropertyConfig;
import de.zonlykroks.advancedchemistry.config.SimpleChemConfig;
import de.zonlykroks.advancedchemistry.element.Element;
import de.zonlykroks.advancedchemistry.util.ParsingUtils;
import de.zonlykroks.advancedchemistry.util.ReflectionUtil;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.*;

public class AdvancedChemistry implements ModInitializer {

    public static final SimpleChemConfig itemConfig = new SimpleChemConfig();
    public static final ChemItemPropertyConfig rarityConfig = new ChemItemPropertyConfig();

    //Identifier, Mapped to registered Element
    public static final Map<Identifier, Element> registryNameElementToElement = new HashMap<>();

    private static final ItemGroup ADVANCED_CHEMISTRY_CREATIVE_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(Blocks.GLASS))
            .displayName(Text.translatable("itemGroup.advancedchemistry.creative_group"))
            .entries((displayContext, entries) -> {
                List<Element> elementList = new ArrayList<>(registryNameElementToElement.values());

                Collections.sort(elementList, Comparator.comparingInt(o -> o.atomicNumber));

                for(Element element : elementList) {
                    entries.add(new ItemStack(element));
                }

                for(Block block : ReflectionUtil.collectBlockFieldsFromClass(BlockInit.class)) {
                    entries.add(new ItemStack(block));
                }
            })
            .build();

    @Override
    public void onInitialize() {
        itemConfig.loadOrCreateConfig();
        itemConfig.convertCSVToJSON(itemConfig.configFileCSV);
        itemConfig.convertJSONToString();

        ParsingUtils.parseItems(itemConfig.jsonFileContent);

        rarityConfig.loadOrCreateConfig();
        rarityConfig.loadAndChangeItemProperties();

        BlockInit.init();

        Registry.register(Registries.ITEM_GROUP, new Identifier("advancedchemistry", "creative_group"), ADVANCED_CHEMISTRY_CREATIVE_GROUP);
    }
}
