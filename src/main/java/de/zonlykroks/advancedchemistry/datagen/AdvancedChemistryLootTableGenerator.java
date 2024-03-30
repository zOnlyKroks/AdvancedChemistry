package de.zonlykroks.advancedchemistry.datagen;

import de.zonlykroks.advancedchemistry.blocks.BlockInit;
import de.zonlykroks.advancedchemistry.util.ReflectionUtil;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;

public class AdvancedChemistryLootTableGenerator extends FabricBlockLootTableProvider {
    protected AdvancedChemistryLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        for(Block block : ReflectionUtil.collectBlockFieldsFromClass(BlockInit.class)) {
            addDrop(block);
        }
    }
}
