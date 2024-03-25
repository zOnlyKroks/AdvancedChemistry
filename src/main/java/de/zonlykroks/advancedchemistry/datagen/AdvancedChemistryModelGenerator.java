package de.zonlykroks.advancedchemistry.datagen;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.zonlykroks.advancedchemistry.AdvancedChemistry;
import de.zonlykroks.advancedchemistry.blocks.BlockInit;
import de.zonlykroks.advancedchemistry.config.SimpleChemConfig;
import de.zonlykroks.advancedchemistry.element.Element;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

import java.util.ArrayList;
import java.util.List;

public class AdvancedChemistryModelGenerator extends FabricModelProvider {
    public static final SimpleChemConfig config = new SimpleChemConfig();

    public AdvancedChemistryModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerGeneric(BlockInit.SPODUMENE_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        config.loadOrCreateConfig();
        config.convertCSVToJSON(config.configFileCSV);
        config.convertJSONToString();

        parseItems(config.jsonFileContent);

        AdvancedChemistry.registryNameElementToElement.forEach((identifier, element) -> {
            itemModelGenerator.register(element, Models.GENERATED);
        });
    }

    private void parseItems(String json) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<Element> elements = objectMapper.readValue(json, new TypeReference<>() {});
            elements.forEach(Element::convertAndRegisterToItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
