package de.zonlykroks.advancedchemistry.blocks;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public abstract class OreBlock extends Block {

    public final RegistryKey<PlacedFeature> CUSTOM_ORE_PLACED_KEY;

    public OreBlock(Settings settings) {
        super(settings);
        CUSTOM_ORE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("advancedchemistry", name()));
    }

    public void setupOreGen() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, CUSTOM_ORE_PLACED_KEY);
    }

    public abstract String name();

}
