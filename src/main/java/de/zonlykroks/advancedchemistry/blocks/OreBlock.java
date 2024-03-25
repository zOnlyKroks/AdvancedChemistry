package de.zonlykroks.advancedchemistry.blocks;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.PlacedFeature;

public abstract class OreBlock extends Block {

    public final RegistryKey<PlacedFeature> CUSTOM_ORE_PLACED_KEY;

    public OreBlock(Settings settings) {
        super(settings);
        CUSTOM_ORE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("advancedchemistry", name()));
    }

    public abstract void setupOreGen();

    public abstract String name();

}
