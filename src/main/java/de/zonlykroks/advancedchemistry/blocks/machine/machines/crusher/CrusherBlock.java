package de.zonlykroks.advancedchemistry.blocks.machine.machines.crusher;

import de.zonlykroks.advancedchemistry.blocks.machine.MachineBlock;
public class CrusherBlock extends MachineBlock {
    public CrusherBlock() {
        super(CrusherBlockEntity::new, Settings.create().requiresTool());
    }
}
