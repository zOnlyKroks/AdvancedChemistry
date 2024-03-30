package de.zonlykroks.advancedchemistry.blocks.machine;

import de.zonlykroks.advancedchemistry.blocks.machine.machines.crusher.CrusherBlock;
import de.zonlykroks.advancedchemistry.blocks.machine.machines.crusher.CrusherBlockEntity;
import de.zonlykroks.advancedchemistry.blocks.machine.machines.powerbank.CreativePowerbankBlock;
import de.zonlykroks.advancedchemistry.blocks.machine.machines.powerbank.CreativePowerbankBlockEntity;
import de.zonlykroks.advancedchemistry.util.annotation.anno.ClassInit;
import de.zonlykroks.advancedchemistry.util.annotation.anno.CreativeGroupRegister;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import team.reborn.energy.api.EnergyStorage;

@ClassInit
@CreativeGroupRegister
public class MachineRegistry {

    public static final Block POWERBANK_BLOCK = registerBlock("powerbank", new CreativePowerbankBlock());
    public static final BlockEntityType<MachineBlockEntity> POWERBANK_BLOCK_ENTITY = registerMachineBlockEntity("powerbank", FabricBlockEntityTypeBuilder.create(CreativePowerbankBlockEntity::new, POWERBANK_BLOCK).build());

    public static final Block CRUSHER_BLOCK = registerBlock("crusher", new CrusherBlock());
    public static final BlockEntityType<MachineBlockEntity> CRUSHER_BLOCK_ENTITY = registerMachineBlockEntity("crusher", FabricBlockEntityTypeBuilder.create(CrusherBlockEntity::new, CRUSHER_BLOCK).build());

    private static BlockEntityType<MachineBlockEntity> registerMachineBlockEntity(String name, BlockEntityType<?> blockEntityType) {
        BlockEntityType<MachineBlockEntity> type = (BlockEntityType<MachineBlockEntity>) Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier("advancedchemistry", name), blockEntityType);
        EnergyStorage.SIDED.registerForBlockEntity((myBlockEntity, direction) -> myBlockEntity.getEnergyStorage(), type);
        return type;
    }

    private static Block registerBlock(String name, Block block) {
        Block registeredBlock = Registry.register(Registries.BLOCK, new Identifier("advancedchemistry", name), block);

        Registry.register(Registries.ITEM, new Identifier("advancedchemistry", name), new BlockItem(registeredBlock, new FabricItemSettings()));

        return registeredBlock;
    }

}
