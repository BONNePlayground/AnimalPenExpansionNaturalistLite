//
// Created by BONNe
// Copyright - 2026
//


package lv.id.bonne.animalpen.expansion.naturalistlite.client;


import com.crispytwig.naturalist.registry.NaturalistEntityTypes;
import com.crispytwig.naturalist.server.entity.mob.*;

import lv.id.bonne.animalpen.registries.AnimalPenMobAnimationsRegistry;


public class NaturalistLiteExpansionClient
{
    public static void init()
    {
        // Write common init code here.

        AnimalPenMobAnimationsRegistry.AVIARY_ANIMATIONS.put(NaturalistEntityTypes.BUTTERFLY.get(),
            mob ->
            {
                if (mob instanceof Butterfly animal)
                {
                    animal.flyAnimationState.animateWhen(true, 0);
                }
            });

        AnimalPenMobAnimationsRegistry.AVIARY_ANIMATIONS.put(NaturalistEntityTypes.FIREFLY.get(),
            mob ->
            {
                if (mob instanceof Firefly animal)
                {
                    animal.flyAnimationState.animateWhen(true, 0);
                }
            });

        AnimalPenMobAnimationsRegistry.AVIARY_ANIMATIONS.put(NaturalistEntityTypes.VULTURE.get(),
            mob ->
            {
                if (mob instanceof Vulture animal)
                {
                    animal.flyAnimationState.animateWhen(true, 0);
                }
            });

        AnimalPenMobAnimationsRegistry.AVIARY_ANIMATIONS.put(NaturalistEntityTypes.DRAGONFLY.get(),
            mob ->
            {
                if (mob instanceof Dragonfly animal)
                {
                    animal.flyAnimationState.animateWhen(true, 0);
                }
            });

        AnimalPenMobAnimationsRegistry.AVIARY_ANIMATIONS.put(NaturalistEntityTypes.DUCK.get(),
            mob ->
            {
                if (mob instanceof Duck animal)
                {
                    animal.flapAnimationState.animateWhen(true, 0);
                }
            });

        AnimalPenMobAnimationsRegistry.AVIARY_ANIMATIONS.put(NaturalistEntityTypes.BIRD.get(),
            mob ->
            {
                if (mob instanceof Bird animal)
                {
                    animal.flyAnimationState.animateWhen(true, 0);
                }
            });

        // Aquarium animations

        AnimalPenMobAnimationsRegistry.AQUARIUM_ANIMATIONS.put(NaturalistEntityTypes.CATFISH.get(),
            mob ->
            {
                if (mob instanceof Catfish fish)
                {
                    fish.swimAnimationState.animateWhen(true, 0);
                }
            });

        AnimalPenMobAnimationsRegistry.AQUARIUM_ANIMATIONS.put(NaturalistEntityTypes.BASS.get(),
            mob ->
            {
                if (mob instanceof Bass fish)
                {
                    fish.swimAnimationState.animateWhen(true, 0);
                }
            });

        AnimalPenMobAnimationsRegistry.AQUARIUM_ANIMATIONS.put(NaturalistEntityTypes.ANGLERFISH.get(),
            mob ->
            {
                if (mob instanceof Anglerfish fish)
                {
                    fish.swimAnimationState.animateWhen(true, 0);
                }
            });

        AnimalPenMobAnimationsRegistry.AQUARIUM_ANIMATIONS.put(NaturalistEntityTypes.RAY.get(),
            mob ->
            {
                if (mob instanceof Ray fish)
                {
                    fish.swimAnimationState.animateWhen(true, 0);
                }
            });

        AnimalPenMobAnimationsRegistry.AQUARIUM_ANIMATIONS.put(NaturalistEntityTypes.BLOBFISH.get(),
            mob ->
            {
                if (mob instanceof Blobfish fish)
                {
                    fish.swimAnimationState.animateWhen(true, 0);
                }
            });


        AnimalPenMobAnimationsRegistry.AQUARIUM_ANIMATIONS.put(NaturalistEntityTypes.PIRANHA.get(),
            mob ->
            {
                if (mob instanceof Piranha fish)
                {
                    fish.swimAnimationState.animateWhen(true, 0);
                }
            });

        AnimalPenMobAnimationsRegistry.AQUARIUM_ANIMATIONS.put(NaturalistEntityTypes.CLAM.get(),
            mob ->
            {
                if (mob instanceof Clam fish)
                {
                    fish.idleOpenAnimationState.animateWhen(true, 0);
                }
            });

        AnimalPenMobAnimationsRegistry.AQUARIUM_ANIMATIONS.put(NaturalistEntityTypes.CRAB.get(),
            mob ->
            {
                if (mob instanceof Crab fish)
                {
                    fish.walkAnimationState.animateWhen(true, 0);
                }
            });

        AnimalPenMobAnimationsRegistry.AQUARIUM_ANIMATIONS.put(NaturalistEntityTypes.GIANT_ISOPOD.get(),
            mob ->
            {
                if (mob instanceof GiantIsopod fish)
                {
                    fish.swimAnimationState.animateWhen(true, 0);
                }
            });

        AnimalPenMobAnimationsRegistry.AQUARIUM_ANIMATIONS.put(NaturalistEntityTypes.GREAT_WHITE_SHARK.get(),
            mob ->
            {
                if (mob instanceof GreatWhiteShark fish)
                {
                    fish.swimAnimationState.animateWhen(true, 0);
                }
            });

        AnimalPenMobAnimationsRegistry.AQUARIUM_ANIMATIONS.put(NaturalistEntityTypes.JELLYFISH.get(),
            mob ->
            {
                if (mob instanceof Jellyfish fish)
                {
                    fish.swimAnimationState.animateWhen(true, 0);
                }
            });

        AnimalPenMobAnimationsRegistry.AQUARIUM_ANIMATIONS.put(NaturalistEntityTypes.STARFISH.get(),
            mob ->
            {
                if (mob instanceof Starfish fish)
                {
                    fish.idleAnimationState.animateWhen(true, 0);
                }
            });

        AnimalPenMobAnimationsRegistry.AQUARIUM_ANIMATIONS.put(NaturalistEntityTypes.WHALE.get(),
            mob ->
            {
                if (mob instanceof Whale fish)
                {
                    fish.swimAnimationState.animateWhen(true, 0);
                }
            });
    }
}
