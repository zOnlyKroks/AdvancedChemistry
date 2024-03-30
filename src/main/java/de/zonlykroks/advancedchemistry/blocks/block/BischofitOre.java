package de.zonlykroks.advancedchemistry.blocks.block;

import de.zonlykroks.advancedchemistry.blocks.OreBlock;
import net.minecraft.block.AbstractBlock;

public class BischofitOre extends OreBlock {
    public BischofitOre() {
        super(AbstractBlock.Settings.create().resistance(3.0F).hardness(3.0F).requiresTool());
    }

    @Override
    public String name() {
        return "bischofit_ore";
    }
}
