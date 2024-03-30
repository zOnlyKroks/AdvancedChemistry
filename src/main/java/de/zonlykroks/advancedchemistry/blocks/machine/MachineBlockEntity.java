package de.zonlykroks.advancedchemistry.blocks.machine;

import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.EnergyStorageUtil;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public abstract class MachineBlockEntity  extends BlockEntity {

    private final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(maxCap(), maxIn(), maxEx()) {
        @Override
        protected void onFinalCommit() {
            markDirty();
        }
    };


    public MachineBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    protected abstract void openMenu(PlayerEntity player);

    protected abstract boolean hasComparatorOutput();

    protected abstract int getComparatorOutput();

    protected abstract void updateRedstone();

    public SimpleEnergyStorage getEnergyStorage() {
        return this.energyStorage;
    }

    protected abstract long maxCap();
    protected abstract long maxIn();
    protected abstract long maxEx();

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    public abstract void tick(World world, BlockPos pos, BlockState state, BlockEntity blockEntity);

    protected void requestEnergy(long needed) {
        EnergyStorage source = EnergyStorage.SIDED.find(this.world,this.pos,null);

        // Open a transaction: this allows cancelling the operation if it doesn't go as expected.
        try (Transaction transaction = Transaction.openOuter()) {
            // Try to extract, will return how much was actually extracted
            long amountExtracted = source.extract(this.maxIn(), transaction);
            if (amountExtracted == needed) {
                // "Commit" the transaction to make sure the change is applied.
                transaction.commit();
            } else {
                // If we didn't get what we needed, cancel the transaction.
                transaction.close();
            }
        }
    }
}
