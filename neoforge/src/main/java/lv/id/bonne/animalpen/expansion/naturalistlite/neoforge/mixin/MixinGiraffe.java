//
// Created by BONNe
// Copyright - 2026
//


package lv.id.bonne.animalpen.expansion.naturalistlite.neoforge.mixin;


import com.starfish_studios.naturalist.server.entity.mob.Giraffe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;


@Mixin(Giraffe.class)
public abstract class MixinGiraffe
{

    @Shadow
    public abstract boolean isFood(ItemStack stack);


    @Inject(method = "mobInteract",
        at = @At(value = "INVOKE",
            target = "Lcom/starfish_studios/naturalist/server/entity/mob/Giraffe;isFood(Lnet/minecraft/world/item/ItemStack;)Z"),
        cancellable = true)
    private void injectProperInteraction(Player player,
        InteractionHand hand,
        CallbackInfoReturnable<InteractionResult> cir)
    {
        if (!this.isFood(player.getItemInHand(hand)))
        {
            cir.setReturnValue(InteractionResult.PASS);
            cir.cancel();
        }
    }
}
