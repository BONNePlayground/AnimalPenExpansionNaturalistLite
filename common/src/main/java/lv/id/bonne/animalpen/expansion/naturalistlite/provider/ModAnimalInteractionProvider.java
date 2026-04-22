package lv.id.bonne.animalpen.expansion.naturalistlite.provider;


import com.starfish_studios.naturalist.Naturalist;
import com.starfish_studios.naturalist.core.registry.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import lv.id.bonne.animalpen.data.provider.AnimalInteractionProvider;
import lv.id.bonne.animalpen.interaction.cooldown.CooldownEntry;
import lv.id.bonne.animalpen.interaction.function.FunctionKey;
import lv.id.bonne.animalpen.interaction.ingredient.ConsumerEntry;
import lv.id.bonne.animalpen.interaction.ingredient.CustomIngredient;
import lv.id.bonne.animalpen.interaction.loot.LootEntry;
import lv.id.bonne.animalpen.interaction.model.AnimalInteractionBuilder;
import lv.id.bonne.animalpen.interaction.textentry.TextEntry;
import lv.id.bonne.animalpen.registries.AnimalPenFunctionRegistry;
import lv.id.bonne.animalpen.registries.AnimalPenTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;


/**
 * Animal Interaction Data Provider.
 */
public class ModAnimalInteractionProvider extends AnimalInteractionProvider
{
    public ModAnimalInteractionProvider(PackOutput generator,
        CompletableFuture<HolderLookup.Provider> lookupProvider)
    {
        super(generator, lookupProvider);
        this.registry = lookupProvider;
    }


    @Override
    public CompletableFuture<?> run(CachedOutput cache)
    {
        return this.registry.thenCompose(provider ->
        {
            List<CompletableFuture<?>> futureList = new ArrayList<>();

            // Animal pen
            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.SNAIL.get(),
                List.of(this.generateBucketable(Items.BUCKET, NaturalistItems.SNAIL_BUCKET.get(), 1),
                    this.generateAmbientSound(NaturalistSoundEvents.SNAIL_FORWARD.get())),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.BEAR.get(),
                List.of(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.BEAR_TEMPT_ITEMS)),
                    AnimalInteractionBuilder.create("shearing").
                        ingredient(CustomIngredient.merge(CustomIngredient.of(Items.SHEARS),
                            CustomIngredient.of(AnimalPenTags.FORGE_SHEARS),
                            CustomIngredient.of(AnimalPenTags.COMMON_SHEARS))).
                        lootEntry(LootEntry.of(new ResourceLocation(Naturalist.MOD_ID,
                            "animal_interactions/shear/bear_fur"), 320, true)).
                        consume(new ConsumerEntry.Damage(1)).
                        cooldown(new CooldownEntry.Static(1200)).
                        sound(SoundEvents.SHEEP_SHEAR.getLocation()).
                        redstoneBit(2).
                        runFunctions(FunctionKey.of(AnimalPenFunctionRegistry.MOB_SET_SHEARED.get(), null, true)).
                        finishFunctions(FunctionKey.of(AnimalPenFunctionRegistry.MOB_SET_SHEARED.get(), null, false)).
                        textLines(TextEntry.ready("display.animal_pen.full_ready",
                            CustomIngredient.of(NaturalistItems.BEAR_FUR.get()))).
                        textLines(TextEntry.cooldown("display.animal_pen.wool_cooldown",
                            CustomIngredient.of(NaturalistItems.BEAR_FUR.get()))).
                        build(),
                    this.generateAmbientSound(NaturalistSoundEvents.BEAR_AMBIENT.get())),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.SNAKE.get(),
                List.of(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.SNAKE_TEMPT_ITEMS)),
                    this.generateAmbientSound(NaturalistSoundEvents.SNAKE_HISS.get())),
                Naturalist.MOD_ID));
            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.CORAL_SNAKE.get(),
                List.of(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.SNAKE_TEMPT_ITEMS)),
                    this.generateAmbientSound(NaturalistSoundEvents.SNAKE_HISS.get())),
                Naturalist.MOD_ID));
            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.RATTLESNAKE.get(),
                List.of(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.SNAKE_TEMPT_ITEMS)),
                    this.generateAmbientSound(NaturalistSoundEvents.SNAKE_RATTLE.get())),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.DEER.get(),
                List.of(this.generateFood(CustomIngredient.of(Items.APPLE)),
                    this.generateAmbientSound(NaturalistSoundEvents.DEER_AMBIENT.get())),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.CATERPILLAR.get(),
                List.of(this.generateFood(CustomIngredient.of(ItemTags.FLOWERS))),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.RHINO.get(),
                List.of(this.generateAmbientSound(NaturalistSoundEvents.RHINO_AMBIENT.get())),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.LION.get(),
                List.of(this.generateAmbientSound(NaturalistSoundEvents.LION_AMBIENT.get())),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.ELEPHANT.get(),
                List.of(this.generateAmbientSound(NaturalistSoundEvents.ELEPHANT_AMBIENT.get())),
                Naturalist.MOD_ID));
            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.ZEBRA.get(),
                List.of(this.generateFood(CustomIngredient.of(Items.WHEAT,
                        Items.SUGAR,
                        Blocks.HAY_BLOCK.asItem(),
                        Items.APPLE,
                        Items.GOLDEN_CARROT,
                        Items.GOLDEN_APPLE,
                        Items.ENCHANTED_GOLDEN_APPLE)),
                    this.generateAmbientSound(NaturalistSoundEvents.ZEBRA_AMBIENT.get())),
                Naturalist.MOD_ID));
            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.GIRAFFE.get(),
                List.of(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.GIRAFFE_FOOD_ITEMS)),
                    this.generateAmbientSound(NaturalistSoundEvents.GIRAFFE_AMBIENT.get())),
                Naturalist.MOD_ID));
            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.HIPPO.get(),
                List.of(this.generateFood(CustomIngredient.of(Blocks.MELON)),
                    this.generateAmbientSound(NaturalistSoundEvents.HIPPO_AMBIENT.get())),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.BOAR.get(),
                List.of(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.BOAR_FOOD_ITEMS)),
                    this.generateAmbientSound(SoundEvents.PIG_AMBIENT)),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.ALLIGATOR.get(),
                List.of(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.ALLIGATOR_FOOD_ITEMS)),
                    AnimalInteractionBuilder.create("eggs").
                        ingredient(CustomIngredient.of(Items.BUCKET)).
                        lootEntry(LootEntry.of(ResourceLocation.tryBuild(Naturalist.MOD_ID, "animal_interactions/bucket/alligator_egg"),
                            320,
                            true)).
                        cooldown(new CooldownEntry.Linear(6000, -20, 200)).
                        redstoneBit(2).
                        textLines(TextEntry.ready("display.animal_pen.full_ready",
                            CustomIngredient.of(NaturalistBlocks.ALLIGATOR_EGG.get()))).
                        textLines(TextEntry.cooldown("display.animal_pen.egg_cooldown",
                            CustomIngredient.of(NaturalistBlocks.ALLIGATOR_EGG.get()))).
                        build(),
                    this.generateAmbientSound(NaturalistSoundEvents.GATOR_AMBIENT.get())),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.LIZARD.get(),
                List.of(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.LIZARD_TEMPT_ITEMS)),
                    this.generateAmbientSound(SoundEvents.SLIME_SQUISH)),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.TORTOISE.get(),
                List.of(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.TORTOISE_TEMPT_ITEMS)),
                    AnimalInteractionBuilder.create("eggs").
                        ingredient(CustomIngredient.of(Items.BUCKET)).
                        lootEntry(LootEntry.of(ResourceLocation.tryBuild(Naturalist.MOD_ID, "animal_interactions/bucket/tortoise_egg"),
                            320,
                            true)).
                        cooldown(new CooldownEntry.Linear(6000, -20, 200)).
                        redstoneBit(2).
                        textLines(TextEntry.ready("display.animal_pen.full_ready",
                            CustomIngredient.of(NaturalistBlocks.TORTOISE_EGG.get()))).
                        textLines(TextEntry.cooldown("display.animal_pen.egg_cooldown",
                            CustomIngredient.of(NaturalistBlocks.TORTOISE_EGG.get()))).
                        build()),
                Naturalist.MOD_ID));

            // Aviary
            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.BUTTERFLY.get(),
                List.of(this.generateFood(CustomIngredient.of(ItemTags.FLOWERS))),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.FIREFLY.get(),
                List.of(),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.BLUEJAY.get(),
                List.of(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.BIRD_FOOD_ITEMS)),
                    this.generateAmbientSound(NaturalistSoundEvents.BIRD_AMBIENT_BLUEJAY.get())),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.CANARY.get(),
                List.of(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.BIRD_FOOD_ITEMS)),
                    this.generateAmbientSound(NaturalistSoundEvents.BIRD_AMBIENT_CANARY.get())),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.CARDINAL.get(),
                List.of(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.BIRD_FOOD_ITEMS)),
                    this.generateAmbientSound(NaturalistSoundEvents.BIRD_AMBIENT_CARDINAL.get())),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.ROBIN.get(),
                List.of(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.BIRD_FOOD_ITEMS)),
                    this.generateAmbientSound(NaturalistSoundEvents.BIRD_AMBIENT_ROBIN.get())),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.VULTURE.get(),
                List.of(this.generateFood(CustomIngredient.of(Items.ROTTEN_FLESH)),
                    this.generateAmbientSound(NaturalistSoundEvents.VULTURE_AMBIENT.get())),
                Naturalist.MOD_ID));

            futureList.add(
                this.generateWithInteractions(cache,
                    NaturalistEntityTypes.DRAGONFLY.get(),
                    List.of(),
                    Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.DUCK.get(),
                List.of(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.DUCK_FOOD_ITEMS)),
                    AnimalInteractionBuilder.create("eggs").
                        ingredient(CustomIngredient.of(Items.BUCKET)).
                        lootEntry(LootEntry.of(ResourceLocation.tryBuild(Naturalist.MOD_ID, "animal_interactions/bucket/duck_egg"),
                            320,
                            true)).
                        cooldown(new CooldownEntry.Linear(6000, -20, 200)).
                        redstoneBit(2).
                        textLines(TextEntry.ready("display.animal_pen.full_ready",
                            CustomIngredient.of(NaturalistItems.DUCK_EGG.get()))).
                        textLines(TextEntry.cooldown("display.animal_pen.egg_cooldown",
                            CustomIngredient.of(NaturalistItems.DUCK_EGG.get()))).
                        build(),
                    this.generateAmbientSound(NaturalistSoundEvents.DUCK_AMBIENT.get())),
                Naturalist.MOD_ID));

            // Aquarium
            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.CATFISH.get(),
                List.of(
                    this.generateBucketable(Items.WATER_BUCKET, NaturalistItems.CATFISH_BUCKET.get(), 2),
                    this.generateAmbientSound(SoundEvents.SALMON_AMBIENT)),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.BASS.get(),
                List.of(
                    this.generateBucketable(Items.WATER_BUCKET, NaturalistItems.BASS_BUCKET.get(), 2),
                    this.generateAmbientSound(SoundEvents.SALMON_AMBIENT)),
                Naturalist.MOD_ID));

            return CompletableFuture.allOf(futureList.toArray(CompletableFuture[]::new));
        });
    }


    private final CompletableFuture<HolderLookup.Provider> registry;
}