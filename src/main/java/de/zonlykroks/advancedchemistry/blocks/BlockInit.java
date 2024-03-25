package de.zonlykroks.advancedchemistry.blocks;

import de.zonlykroks.advancedchemistry.blocks.block.BeryllOre;
import de.zonlykroks.advancedchemistry.blocks.block.BoraxOre;
import de.zonlykroks.advancedchemistry.blocks.block.HaliteOre;
import de.zonlykroks.advancedchemistry.blocks.block.SpodumeneOre;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockInit {

    public static final Block SPODUMENE_ORE = registerOreBlock(new SpodumeneOre());
    public static final Block BERYLL_ORE = registerOreBlock(new BeryllOre());

    public static final Block BORAX_ORE = registerOreBlock(new BoraxOre());

    public static final Block HALITE_ORE = registerOreBlock(new HaliteOre());

    public static Block registerOreBlock(OreBlock oreBlock) {
        Block registeredBlock = Registry.register(Registries.BLOCK, new Identifier("advancedchemistry", oreBlock.name()), oreBlock);
        oreBlock.setupOreGen();

        Registry.register(Registries.ITEM, new Identifier("advancedchemistry", oreBlock.name()), new BlockItem(registeredBlock, new FabricItemSettings()));

        return registeredBlock;
    }

    public static void init() {
        //DUMMY to init fields ;D
    }
}
