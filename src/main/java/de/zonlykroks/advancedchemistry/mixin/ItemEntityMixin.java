package de.zonlykroks.advancedchemistry.mixin;

import de.zonlykroks.advancedchemistry.element.Element;
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
            if(itemEntity.getStack().getItem() instanceof Element element) {
                if(element.explodesOnWaterContact) {
                    itemEntity.getWorld().createExplosion(itemEntity, itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), 1.0f, World.ExplosionSourceType.NONE);
                    itemEntity.discard();
                }
            }
        }
    }

}
