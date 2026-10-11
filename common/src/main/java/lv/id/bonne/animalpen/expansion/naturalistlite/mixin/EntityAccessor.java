//
// Created by BONNe
// Copyright - 2026
//


package lv.id.bonne.animalpen.expansion.naturalistlite.mixin;


import org.spongepowered.asm.mixin.gen.Accessor;
import java.util.Set;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;


@org.spongepowered.asm.mixin.Mixin(net.minecraft.world.entity.Entity.class)
public interface EntityAccessor
{
    @Accessor
    Set<TagKey<Fluid>> getFluidOnEyes();
}
