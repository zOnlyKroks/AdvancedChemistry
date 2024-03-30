package de.zonlykroks.advancedchemistry.blocks.machine.machines.crusher;

import de.zonlykroks.advancedchemistry.blocks.machine.MachineBlockEntity;
import de.zonlykroks.advancedchemistry.blocks.machine.MachineRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CrusherBlockEntity extends MachineBlockEntity {
    public CrusherBlockEntity(BlockPos pos, BlockState state) {
        super(MachineRegistry.CRUSHER_BLOCK_ENTITY, pos, state);
    }

    @Override
    protected void openMenu(PlayerEntity player) {
        System.out.println("Menu should open ere ;D");
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
        return 100;
    }

    @Override
    protected long maxEx() {
        return 0;
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state, BlockEntity blockEntity) {
        this.requestEnergy(100);
    }
}
