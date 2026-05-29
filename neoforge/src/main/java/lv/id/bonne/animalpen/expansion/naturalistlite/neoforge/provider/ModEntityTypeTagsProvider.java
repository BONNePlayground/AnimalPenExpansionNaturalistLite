//
// Created by BONNe
// Copyright add(NaturalistEntityTypes.2025
//


package lv.id.bonne.animalpen.expansion.naturalistlite.neoforge.provider;


import com.starfish_studios.naturalist.registry.NaturalistEntityTypes;

import lv.id.bonne.animalpen.data.helper.SimpleTagAppender;
import lv.id.bonne.animalpen.registries.AnimalPenTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;


@FunctionalInterface
public interface ModEntityTypeTagsProvider
{
    SimpleTagAppender<EntityType<?>> modTag(TagKey<EntityType<?>> tag);


    default void addModTags()
    {
        this.modTag(AnimalPenTags.ANIMAL_CAGE_PICKABLE).
            add(NaturalistEntityTypes.SNAIL.get()).
            add(NaturalistEntityTypes.BEAR.get()).
            add(NaturalistEntityTypes.SNAKE.get()).
            add(NaturalistEntityTypes.CORAL_SNAKE.get()).
            add(NaturalistEntityTypes.RATTLESNAKE.get()).
            add(NaturalistEntityTypes.DEER.get()).
            add(NaturalistEntityTypes.CATERPILLAR.get()).
            add(NaturalistEntityTypes.RHINO.get()).
            add(NaturalistEntityTypes.LION.get()).
            add(NaturalistEntityTypes.ELEPHANT.get()).
            add(NaturalistEntityTypes.ZEBRA.get()).
            add(NaturalistEntityTypes.GIRAFFE.get()).
            add(NaturalistEntityTypes.HIPPO.get()).
            add(NaturalistEntityTypes.BOAR.get()).
            add(NaturalistEntityTypes.ALLIGATOR.get()).
            add(NaturalistEntityTypes.LIZARD.get()).
            add(NaturalistEntityTypes.DUCK.get()).
            add(NaturalistEntityTypes.TORTOISE.get());
        this.modTag(AnimalPenTags.BIRD_CATCHER_PICKABLE).
            add(NaturalistEntityTypes.BUTTERFLY.get()).
            add(NaturalistEntityTypes.FIREFLY.get()).
            add(NaturalistEntityTypes.BLUEJAY.get()).
            add(NaturalistEntityTypes.CANARY.get()).
            add(NaturalistEntityTypes.CARDINAL.get()).
            add(NaturalistEntityTypes.ROBIN.get()).
            add(NaturalistEntityTypes.VULTURE.get()).
            add(NaturalistEntityTypes.DRAGONFLY.get()).
            add(NaturalistEntityTypes.DUCK.get()).
            add(NaturalistEntityTypes.FINCH.get()).
            add(NaturalistEntityTypes.SPARROW.get());
        this.modTag(AnimalPenTags.WATER_MOB_CONTAINER_PICKABLE).
            add(NaturalistEntityTypes.CATFISH.get()).
            add(NaturalistEntityTypes.BASS.get());
    }
}
