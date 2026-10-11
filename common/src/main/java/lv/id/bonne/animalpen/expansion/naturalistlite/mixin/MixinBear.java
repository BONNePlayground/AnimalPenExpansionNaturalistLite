//
// Created by BONNe
// Copyright - 2026
//


package lv.id.bonne.animalpen.expansion.naturalistlite.mixin;


import com.crispytwig.naturalist.server.entity.mob.Bear;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import lv.id.bonne.animalpen.processing.function.api.ShearStateAccessor;


@Mixin(value = Bear.class, remap = false)
public abstract class MixinBear implements ShearStateAccessor
{
        @Shadow
        public abstract void setSheared(boolean b);


        @Override
        public void setShearedState(boolean sheared)
        {
            this.setSheared(sheared);
        }
}
