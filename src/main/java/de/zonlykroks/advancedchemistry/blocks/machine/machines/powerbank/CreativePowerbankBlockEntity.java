package de.zonlykroks.advancedchemistry.blocks.machine.machines.powerbank;

import de.zonlykroks.advancedchemistry.blocks.machine.MachineBlock;
import de.zonlykroks.advancedchemistry.blocks.machine.MachineBlockEntity;
import de.zonlykroks.advancedchemistry.blocks.machine.MachineRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.function.BiFunction;

public class CreativePowerbankBlockEntity extends MachineBlockEntity {
    public CreativePowerbankBlockEntity( BlockPos pos, BlockState state) {
        super(MachineRegistry.POWERBANK_BLOCK_ENTITY, pos, state);
    }

    @Override
    protected void openMenu(PlayerEntity player) {

    }

    @Override
    protected boolean hasComparatorOutput() {
        return false;
    }

    @Override
    protected int getComparatorOutput() {
        return 0;
    }

    @Override
    protected void updateRedstone() {

    }

    @Override
    protected long maxCap() {
        return Long.MAX_VALUE;
    }

    @Override
    protected long maxIn() {
        return 0;
    }

    @Override
    protected long maxEx() {
        return Long.MAX_VALUE;
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        this.getEnergyStorage().amount = Long.MAX_VALUE;
    }
}
