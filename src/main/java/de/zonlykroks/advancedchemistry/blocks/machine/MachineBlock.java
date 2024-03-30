package de.zonlykroks.advancedchemistry.blocks.machine;

import com.mojang.serialization.MapCodec;
import de.zonlykroks.advancedchemistry.blocks.machine.machines.crusher.CrusherBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class MachineBlock extends Block implements BlockEntityProvider {
    public final BiFunction<BlockPos, BlockState, MachineBlockEntity> blockEntityConstructor;
    private volatile MachineBlockEntity blockEntityInstance = null;

    public MachineBlock(BiFunction<BlockPos, BlockState, MachineBlockEntity> blockEntityConstructor, Settings settings) {
        super(settings);
        this.blockEntityConstructor = blockEntityConstructor;
    }

    public MachineBlockEntity getBlockEntityInstance() {
        if (this.blockEntityInstance == null) {
            this.blockEntityInstance = createBlockEntity(BlockPos.ORIGIN, this.getDefaultState());
        }
        return blockEntityInstance;
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return getBlockEntityInstance().hasComparatorOutput();
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        if(world.getBlockEntity(pos) instanceof MachineBlockEntity machine)
            return machine.getComparatorOutput();

        return 0;
    }

    @Override
    public void onBroken(WorldAccess world, BlockPos pos, BlockState state) {
        if (world.getBlockEntity(pos) instanceof MachineBlockEntity machine) {
            machine.updateRedstone();
        }
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (world.getBlockEntity(pos) instanceof MachineBlockEntity machine) {
            machine.updateRedstone();
        }
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (world.getBlockEntity(pos) instanceof MachineBlockEntity machine) {
            machine.updateRedstone();
        }
    }

    @Nullable
    @Override
    public MachineBlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return blockEntityConstructor.apply(pos,state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return (world1, pos, state1, blockEntity) -> {
            if (blockEntity instanceof MachineBlockEntity machineBlockEntity) {
                machineBlockEntity.tick(world1,pos,state1,blockEntity);
            }
        };
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(world.isClient){
            return ActionResult.SUCCESS;
        }else {
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof MachineBlockEntity machine) {
                machine.openMenu(player);
            }
            return ActionResult.CONSUME;
        }
    }
}
