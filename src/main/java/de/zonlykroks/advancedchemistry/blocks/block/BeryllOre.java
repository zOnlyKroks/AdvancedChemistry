package de.zonlykroks.advancedchemistry.blocks.block;

import de.zonlykroks.advancedchemistry.blocks.OreBlock;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.AbstractBlock;
import net.minecraft.world.gen.GenerationStep;

public class BeryllOre extends OreBlock {
    public BeryllOre() {
        super(AbstractBlock.Settings.create().resistance(3.0F).hardness(3.0F).requiresTool());
    }

    @Override
    public String name() {
        return "beryll_ore";
    }
}
