package de.zonlykroks.advancedchemistry.blocks.machine.machines.powerbank;

import de.zonlykroks.advancedchemistry.blocks.machine.MachineBlock;

public class CreativePowerbankBlock extends MachineBlock {
    public CreativePowerbankBlock() {
        super(CreativePowerbankBlockEntity::new, Settings.create().requiresTool());
    }
}
