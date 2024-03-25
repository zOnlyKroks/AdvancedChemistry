package de.zonlykroks.advancedchemistry.datagen;

import de.zonlykroks.advancedchemistry.AdvancedChemistry;
import de.zonlykroks.advancedchemistry.blocks.BlockInit;
import de.zonlykroks.advancedchemistry.config.SimpleChemConfig;
import de.zonlykroks.advancedchemistry.util.ParsingUtils;
import de.zonlykroks.advancedchemistry.util.ReflectionUtil;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class AdvancedChemistryModelGenerator extends FabricModelProvider {
    public static final SimpleChemConfig config = new SimpleChemConfig();

    public AdvancedChemistryModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        for(Block block : ReflectionUtil.collectBlockFieldsFromClass(BlockInit.class)) {
            blockStateModelGenerator.registerGeneric(block);
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        config.loadOrCreateConfig();
        config.convertCSVToJSON(config.configFileCSV);
        config.convertJSONToString();

        ParsingUtils.parseItems(config.jsonFileContent);

        AdvancedChemistry.registryNameElementToElement.forEach((identifier, element) -> {
            itemModelGenerator.register(element, Models.GENERATED);
        });
    }
}
