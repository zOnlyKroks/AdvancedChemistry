package de.zonlykroks.advancedchemistry.mixin;

import net.minecraft.entity.ItemEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    public void advancedchemistry$onItemEntityTick(CallbackInfo ci) {
        ItemEntity itemEntity = ((ItemEntity) (Object)this);
        if(itemEntity.isSubmergedInWater()) {
            itemEntity.getWorld().createExplosion(itemEntity, itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), 1.0f, World.ExplosionSourceType.NONE);
            itemEntity.discard();
        }
    }

}
