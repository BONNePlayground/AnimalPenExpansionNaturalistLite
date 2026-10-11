//
// Created by BONNe
//


package lv.id.bonne.animalpen.expansion.naturalistlite.neoforge.provider;



import com.crispytwig.naturalist.registry.NaturalistEntityTypes;

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
            add(NaturalistEntityTypes.TORTOISE.get()).
            add(NaturalistEntityTypes.ANT.get()).
            add(NaturalistEntityTypes.BLACK_BEAR.get()).
            add(NaturalistEntityTypes.CAPYBARA.get()).
            add(NaturalistEntityTypes.CRAB.get()).
            add(NaturalistEntityTypes.GIANT_ISOPOD.get()).
            add(NaturalistEntityTypes.HEDGEHOG.get()).
            add(NaturalistEntityTypes.KOMODO_DRAGON.get()).
            add(NaturalistEntityTypes.MAMMOTH.get()).
            add(NaturalistEntityTypes.MOLE.get()).
            add(NaturalistEntityTypes.OSTRICH.get()).
            add(NaturalistEntityTypes.DESERT_SCORPION.get()).
            add(NaturalistEntityTypes.JUNGLE_SCORPION.get()).
            add(NaturalistEntityTypes.RAT.get()).
            add(NaturalistEntityTypes.TIGER.get()).
            add(NaturalistEntityTypes.TURKEY.get());
        this.modTag(AnimalPenTags.BIRD_CATCHER_PICKABLE).
            add(NaturalistEntityTypes.BUTTERFLY.get()).
            add(NaturalistEntityTypes.FIREFLY.get()).
            add(NaturalistEntityTypes.VULTURE.get()).
            add(NaturalistEntityTypes.DRAGONFLY.get()).
            add(NaturalistEntityTypes.DUCK.get()).
            add(NaturalistEntityTypes.BIRD.get());
        this.modTag(AnimalPenTags.WATER_MOB_CONTAINER_PICKABLE).
            add(NaturalistEntityTypes.CATFISH.get()).
            add(NaturalistEntityTypes.BASS.get()).
            add(NaturalistEntityTypes.ANGLERFISH.get()).
            add(NaturalistEntityTypes.RAY.get()).
            add(NaturalistEntityTypes.BLOBFISH.get()).
            add(NaturalistEntityTypes.PIRANHA.get()).
            add(NaturalistEntityTypes.CLAM.get()).
            add(NaturalistEntityTypes.CRAB.get()).
            add(NaturalistEntityTypes.GIANT_ISOPOD.get()).
            add(NaturalistEntityTypes.GREAT_WHITE_SHARK.get()).
            add(NaturalistEntityTypes.JELLYFISH.get()).
            add(NaturalistEntityTypes.STARFISH.get()).
            add(NaturalistEntityTypes.WHALE.get());
    }
}
