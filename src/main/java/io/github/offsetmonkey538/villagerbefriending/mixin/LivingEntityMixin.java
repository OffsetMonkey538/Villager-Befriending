package io.github.offsetmonkey538.villagerbefriending.mixin;

import io.github.offsetmonkey538.villagerbefriending.entity.IVillagerData;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(
        method = "isImmobile",
        at = @At("TAIL"),
        cancellable = true
    )
    public void villagerbefriending$makeStandingVillagersImmobile(CallbackInfoReturnable<Boolean> cir) {
        if (this instanceof IVillagerData villagerData) {
            if (villagerData.isStanding() && !(villagerData.isFollowingOwner())) {
                cir.setReturnValue(true);
            }
        }
    }
}
