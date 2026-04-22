//
// Created by BONNe
// Copyright - 2026
//


package lv.id.bonne.animalpen.expansion.naturalistlite.mixin;


import com.starfish_studios.naturalist.entity.Giraffe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;


@Mixin(Giraffe.class)
public abstract class MixinGiraffe
{
    @Shadow
    public abstract boolean isBreedingItem(ItemStack stack);


    @Inject(method = "interactMob",
        at = @At(value = "INVOKE",
            target = "Lcom/starfish_studios/naturalist/entity/Giraffe;isBreedingItem(Lnet/minecraft/item/ItemStack;)Z"),
        cancellable = true)
    private void injectProperInteraction(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir)
    {
        if (!this.isBreedingItem(player.getStackInHand(hand)))
        {
            cir.setReturnValue(ActionResult.PASS);
            cir.cancel();
        }
    }
}
