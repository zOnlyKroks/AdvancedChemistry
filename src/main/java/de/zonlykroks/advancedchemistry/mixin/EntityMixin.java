package de.zonlykroks.advancedchemistry.mixin;

import de.zonlykroks.advancedchemistry.element.Element;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.InventoryOwner;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin{

    @Shadow public abstract World getWorld();

    @Shadow public abstract double getX();
    @Shadow public abstract double getY();
    @Shadow public abstract double getZ();

    @Inject(method = "tick", at = @At("TAIL"))
    public void advancedchemistry$addWaterExplosionLogic(CallbackInfo ci) {
        Entity entity = ((Entity) (Object)this);
        if(entity instanceof InventoryOwner inventoryOwner) {
            inventoryOwner.getInventory().heldStacks.forEach(itemStack -> {
                if(itemStack.getItem() instanceof Element element) {
                    doExplosionLogic(element,entity,itemStack);
                    doRadioactivityLogic(element,entity);
                }
            });
        }else if(entity instanceof PlayerEntity playerEntity) {
            playerEntity.getInventory().main.forEach(itemStack -> {
                if(itemStack.getItem() instanceof Element element) {
                    doExplosionLogic(element,entity,itemStack);
                    doRadioactivityLogic(element,entity);
                }
            });

            if(playerEntity.getInventory().offHand.get(0).getItem() instanceof Element element) {
                doExplosionLogic(element,entity,playerEntity.getInventory().offHand.get(0));
                doRadioactivityLogic(element,entity);
            }
        }
    }

    @Unique
    private void doExplosionLogic(Element element, Entity entity, ItemStack itemStack) {
        if(element.explodesOnWaterContact && entity.isSubmergedInWater()) {
            this.getWorld().createExplosion(entity, this.getX(), this.getY(), this.getZ(), 1.0f * itemStack.getCount(), World.ExplosionSourceType.NONE);
            itemStack.decrement(itemStack.getCount());
        }
    }

    @Unique
    private void doRadioactivityLogic(Element element, Entity entity) {
        if(entity instanceof LivingEntity livingEntity) {
            if(element.radioactive && livingEntity.isAffectedBySplashPotions() && !livingEntity.hasStatusEffect(StatusEffects.POISON)) {
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 40,2));
            }
        }
    }
}
