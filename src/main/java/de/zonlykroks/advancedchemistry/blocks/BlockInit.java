package de.zonlykroks.advancedchemistry.blocks;

import de.zonlykroks.advancedchemistry.blocks.block.*;
import de.zonlykroks.advancedchemistry.util.annotation.anno.ClassInit;
import de.zonlykroks.advancedchemistry.util.annotation.anno.CreativeGroupRegister;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

@ClassInit
@CreativeGroupRegister
public class BlockInit {

    //Lithium
    public static final Block SPODUMENE_ORE = registerOreBlock(new SpodumeneOre());

    //Beryllium
    public static final Block BERYLL_ORE = registerOreBlock(new BeryllOre());

    //Boron
    public static final Block BORAX_ORE = registerOreBlock(new BoraxOre());

    //Sodium
    public static final Block HALITE_ORE = registerOreBlock(new HaliteOre());

    //Bischofit -> Magnesiumchloride -> DownsCell -> Magnesium and Chloride
    public static final Block BISCHOFIT_ORE = registerOreBlock(new BischofitOre());

    //Aluminum
    public static final Block BAUXITE_ORE = registerOreBlock(new BauxitOre());

    public static Block registerOreBlock(OreBlock oreBlock) {
        Block registeredBlock = Registry.register(Registries.BLOCK, new Identifier("advancedchemistry", oreBlock.name()), oreBlock);
        oreBlock.setupOreGen();

        Registry.register(Registries.ITEM, new Identifier("advancedchemistry", oreBlock.name()), new BlockItem(registeredBlock, new FabricItemSettings()));

        return registeredBlock;
    }
}
