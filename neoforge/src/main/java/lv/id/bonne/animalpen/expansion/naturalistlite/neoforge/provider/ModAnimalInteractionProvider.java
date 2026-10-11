package lv.id.bonne.animalpen.expansion.naturalistlite.neoforge.provider;


import com.crispytwig.naturalist.Naturalist;
import com.crispytwig.naturalist.registry.NaturalistEntityTypes;
import com.crispytwig.naturalist.registry.NaturalistRegistry;
import com.crispytwig.naturalist.registry.NaturalistSoundEvents;
import com.crispytwig.naturalist.registry.NaturalistTags;
import com.crispytwig.naturalist.server.entity.mob.Snail;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import lv.id.bonne.animalpen.data.provider.AnimalInteractionProvider;
import lv.id.bonne.animalpen.expansion.naturalistlite.NaturalistLiteExpansion;
import lv.id.bonne.animalpen.interaction.cooldown.CooldownEntry;
import lv.id.bonne.animalpen.interaction.function.FunctionKey;
import lv.id.bonne.animalpen.interaction.ingredient.ConsumerEntry;
import lv.id.bonne.animalpen.interaction.ingredient.CustomIngredient;
import lv.id.bonne.animalpen.interaction.loot.LootEntry;
import lv.id.bonne.animalpen.interaction.model.AnimalInteractionBuilder;
import lv.id.bonne.animalpen.interaction.textentry.TextEntry;
import lv.id.bonne.animalpen.registries.AnimalPenFunctionRegistry;
import lv.id.bonne.animalpen.registries.AnimalPenTags;
import lv.id.bonne.animalpen.util.AnimalPenItemHelper;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.PackOutput;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomData;
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
    public CompletableFuture<?> generateData(CachedOutput cache)
    {
        return this.registry.thenCompose(provider ->
        {
            List<CompletableFuture<?>> futureList = new ArrayList<>();

            // Animal pen
            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.SNAIL.get(),
                Stream.concat(this.generateFood(CustomIngredient.of(Items.BEETROOT)).stream(),
                    Stream.of(
                        AnimalInteractionBuilder.create("eggs").ingredient(CustomIngredient.of(Items.BUCKET)).
                            lootEntry(LootEntry.of(ResourceLocation.tryBuild(Naturalist.MOD_ID, "animal_interactions/bucket/snail_eggs"), 320, true)).
                            cooldown(new CooldownEntry.Linear(6000, -20, 200)).
                            sound(NaturalistSoundEvents.SNAIL_AMBIENT.get().getLocation()).
                            redstoneBit(2).
                            textLines(TextEntry.ready("display.animal_pen.full_ready",
                                CustomIngredient.of(NaturalistRegistry.SNAIL_EGGS.get()))).
                            textLines(TextEntry.cooldown("display.animal_pen.egg_cooldown",
                                CustomIngredient.of(NaturalistRegistry.SNAIL_EGGS.get()))).
                            build(),
                        AnimalInteractionBuilder.create("slug_slime_drop").
                            finishFunctions(FunctionKey.of(AnimalPenFunctionRegistry.DROP_LOOT.get(),
                                "loot_table",
                                Naturalist.MOD_ID +
                                    ":animal_interactions/drop/snail_slime")).
                            cooldown(new CooldownEntry.Randomized(12000, 13200)).
                            build(),
                        AnimalInteractionBuilder.create("dye").ingredient(
                            CustomIngredient.of(
                                Items.WHITE_DYE,
                                Items.ORANGE_DYE,
                                Items.MAGENTA_DYE,
                                Items.LIGHT_BLUE_DYE,
                                Items.YELLOW_DYE,
                                Items.LIME_DYE,
                                Items.PINK_DYE,
                                Items.GRAY_DYE,
                                Items.LIGHT_GRAY_DYE,
                                Items.CYAN_DYE,
                                Items.PURPLE_DYE,
                                Items.BLUE_DYE,
                                Items.BROWN_DYE,
                                Items.GREEN_DYE,
                                Items.RED_DYE,
                                Items.BLACK_DYE)).
                            consume(new ConsumerEntry.Replace()).
                            sound(SoundEvents.DYE_USE.getLocation()).
                            runFunctions(FunctionKey.of(NaturalistLiteExpansion.SNAIL_CHANGE_COLOR.get())).
                            textLines(TextEntry.ready("display.animal_pen.color_ready",
                                CustomIngredient.of(this.generateSnailColors().stream()))).
                            build(),
                        this.generateAmbientSound(NaturalistSoundEvents.SNAIL_AMBIENT.get()))).toList(),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.BEAR.get(),
                Stream.concat(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.BEAR_TEMPT_ITEMS)).stream(),
                    Stream.of(AnimalInteractionBuilder.create("shearing").
                        ingredient(CustomIngredient.merge(CustomIngredient.of(Items.SHEARS),
                            CustomIngredient.of(AnimalPenTags.COMMON_SHEARS))).
                        lootEntry(LootEntry.of(ResourceLocation.fromNamespaceAndPath(Naturalist.MOD_ID,
                            "animal_interactions/shear/bear_fur"), 320, true)).
                        consume(new ConsumerEntry.Damage(1)).
                        cooldown(new CooldownEntry.Static(1200)).
                        sound(SoundEvents.SHEEP_SHEAR.getLocation()).
                        redstoneBit(2).
                        runFunctions(FunctionKey.of(AnimalPenFunctionRegistry.MOB_SET_SHEARED.get(), null, true)).
                        finishFunctions(FunctionKey.of(AnimalPenFunctionRegistry.MOB_SET_SHEARED.get(), null, false)).
                        textLines(TextEntry.ready("display.animal_pen.full_ready",
                            CustomIngredient.of(NaturalistRegistry.FUR.get()))).
                        textLines(TextEntry.cooldown("display.animal_pen.wool_cooldown",
                            CustomIngredient.of(NaturalistRegistry.FUR.get()))).
                        build(),
                    this.generateAmbientSound(NaturalistSoundEvents.BEAR_AMBIENT.get()))).toList(),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.BLACK_BEAR.get(),
                Stream.concat(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.BEAR_TEMPT_ITEMS)).stream(),
                    Stream.of(AnimalInteractionBuilder.create("shearing").
                            ingredient(CustomIngredient.merge(CustomIngredient.of(Items.SHEARS),
                                CustomIngredient.of(AnimalPenTags.COMMON_SHEARS))).
                            lootEntry(LootEntry.of(ResourceLocation.fromNamespaceAndPath(Naturalist.MOD_ID,
                                "animal_interactions/shear/bear_fur"), 320, true)).
                            consume(new ConsumerEntry.Damage(1)).
                            cooldown(new CooldownEntry.Static(1200)).
                            sound(SoundEvents.SHEEP_SHEAR.getLocation()).
                            redstoneBit(2).
                            runFunctions(FunctionKey.of(AnimalPenFunctionRegistry.MOB_SET_SHEARED.get(), null, true)).
                            finishFunctions(FunctionKey.of(AnimalPenFunctionRegistry.MOB_SET_SHEARED.get(), null, false)).
                            textLines(TextEntry.ready("display.animal_pen.full_ready",
                                CustomIngredient.of(NaturalistRegistry.FUR.get()))).
                            textLines(TextEntry.cooldown("display.animal_pen.wool_cooldown",
                                CustomIngredient.of(NaturalistRegistry.FUR.get()))).
                            build(),
                        this.generateAmbientSound(NaturalistSoundEvents.BLACK_BEAR_AMBIENT.get()))).toList(),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.SNAKE.get(),
                Stream.concat(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.SNAKE_TEMPT_ITEMS)).stream(),
                    Stream.of(this.generateAmbientSound(NaturalistSoundEvents.SNAKE_HISS.get()))).toList(),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.DEER.get(),
                Stream.concat(this.generateFood(CustomIngredient.of(Items.APPLE)).stream(),
                    Stream.of(this.generateAmbientSound(NaturalistSoundEvents.DEER_AMBIENT.get()))).toList(),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.CATERPILLAR.get(),
                this.generateFood(CustomIngredient.of(ItemTags.FLOWERS)),
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
                Stream.concat(this.generateFood(CustomIngredient.of(Items.WHEAT,
                        Items.SUGAR,
                        Blocks.HAY_BLOCK.asItem(),
                        Items.APPLE,
                        Items.GOLDEN_CARROT,
                        Items.GOLDEN_APPLE,
                        Items.ENCHANTED_GOLDEN_APPLE)).stream(),
                    Stream.of(this.generateAmbientSound(NaturalistSoundEvents.ZEBRA_AMBIENT.get()))).toList(),
                Naturalist.MOD_ID));
            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.GIRAFFE.get(),
                Stream.concat(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.GIRAFFE_FOOD_ITEMS)).stream(),
                    Stream.of(this.generateAmbientSound(NaturalistSoundEvents.GIRAFFE_AMBIENT.get()))).toList(),
                Naturalist.MOD_ID));
            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.HIPPO.get(),
                Stream.concat(this.generateFood(CustomIngredient.of(Blocks.MELON)).stream(),
                    Stream.of(this.generateAmbientSound(NaturalistSoundEvents.HIPPO_AMBIENT.get()))).toList(),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.BOAR.get(),
                Stream.concat(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.BOAR_FOOD_ITEMS)).stream(),
                    Stream.of(this.generateAmbientSound(SoundEvents.PIG_AMBIENT))).toList(),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.ALLIGATOR.get(),
                Stream.concat(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.ALLIGATOR_FOOD_ITEMS)).stream(),
                    Stream.of(AnimalInteractionBuilder.create("eggs").
                        ingredient(CustomIngredient.of(Items.BUCKET)).
                        lootEntry(LootEntry.of(ResourceLocation.tryBuild(Naturalist.MOD_ID, "animal_interactions/bucket/alligator_egg"),
                            320,
                            true)).
                        cooldown(new CooldownEntry.Linear(6000, -20, 200)).
                        redstoneBit(2).
                        textLines(TextEntry.ready("display.animal_pen.full_ready",
                            CustomIngredient.of(NaturalistRegistry.ALLIGATOR_EGG.get()))).
                        textLines(TextEntry.cooldown("display.animal_pen.egg_cooldown",
                            CustomIngredient.of(NaturalistRegistry.ALLIGATOR_EGG.get()))).
                        build(),
                    this.generateAmbientSound(NaturalistSoundEvents.GATOR_AMBIENT.get()))).toList(),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.LIZARD.get(),
                Stream.concat(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.LIZARD_TEMPT_ITEMS)).stream(),
                    Stream.of(this.generateAmbientSound(NaturalistSoundEvents.LIZARD_AMBIENT.get()))).toList(),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.TORTOISE.get(),
                Stream.concat(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.TORTOISE_TEMPT_ITEMS)).stream(),
                    Stream.of(AnimalInteractionBuilder.create("eggs").
                        ingredient(CustomIngredient.of(Items.BUCKET)).
                        lootEntry(LootEntry.of(ResourceLocation.tryBuild(Naturalist.MOD_ID, "animal_interactions/bucket/tortoise_egg"),
                            320,
                            true)).
                        cooldown(new CooldownEntry.Linear(6000, -20, 200)).
                        redstoneBit(2).
                        textLines(TextEntry.ready("display.animal_pen.full_ready",
                            CustomIngredient.of(NaturalistRegistry.TORTOISE_EGG.get()))).
                        textLines(TextEntry.cooldown("display.animal_pen.egg_cooldown",
                            CustomIngredient.of(NaturalistRegistry.TORTOISE_EGG.get()))).
                        build())).toList(),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.ANT.get(),
                Stream.concat(this.generateFood(CustomIngredient.of(Items.SUGAR)).stream(),
                    Stream.of(this.generateAmbientSound(NaturalistSoundEvents.ANT_AMBIENT.get()))).toList(),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.CAPYBARA.get(),
                Stream.concat(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.CAPYBARA_FOOD_ITEMS)).stream(),
                    Stream.of(this.generateAmbientSound(NaturalistSoundEvents.CAPYBARA_AMBIENT.get()))).toList(),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.CRAB.get(),
                Stream.concat(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.CRAB_FOOD)).stream(),
                    Stream.of(this.generateAmbientSound(NaturalistSoundEvents.CRAB_AMBIENT.get()))).toList(),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.GIANT_ISOPOD.get(),
                this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.GIANT_ISOPOD_FOOD)),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.HEDGEHOG.get(),
                Stream.concat(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.HEDGEHOG_FOOD_ITEMS)).stream(),
                    Stream.of(this.generateAmbientSound(NaturalistSoundEvents.HEDGEHOG_AMBIENT.get()))).toList(),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.KOMODO_DRAGON.get(),
                Stream.concat(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.KOMODO_DRAGON_FOOD_ITEMS)).stream(),
                    Stream.of(this.generateAmbientSound(NaturalistSoundEvents.KOMODO_DRAGON_AMBIENT.get()))).toList(),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.MAMMOTH.get(),
                Stream.concat(this.generateFood(CustomIngredient.of(Items.MELON_SLICE)).stream(),
                    Stream.of(this.generateAmbientSound(NaturalistSoundEvents.MAMMOTH_AMBIENT.get()))).toList(),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.MOLE.get(),
                Stream.concat(this.generateFood(CustomIngredient.of(Items.SPIDER_EYE)).stream(),
                    Stream.of(this.generateAmbientSound(NaturalistSoundEvents.MOLE_AMBIENT.get()))).toList(),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.OSTRICH.get(),
                    Stream.concat(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.OSTRICH_FOOD_ITEMS)).stream(),
                        Stream.of(AnimalInteractionBuilder.create("eggs").
                            ingredient(CustomIngredient.of(Items.BUCKET)).
                            lootEntry(LootEntry.of(ResourceLocation.tryBuild(Naturalist.MOD_ID, "animal_interactions/bucket/ostrich_egg"),
                                320,
                                true)).
                            cooldown(new CooldownEntry.Linear(6000, -20, 200)).
                            redstoneBit(2).
                            textLines(TextEntry.ready("display.animal_pen.full_ready",
                                CustomIngredient.of(NaturalistRegistry.OSTRICH_EGG.get()))).
                            textLines(TextEntry.cooldown("display.animal_pen.egg_cooldown",
                                CustomIngredient.of(NaturalistRegistry.OSTRICH_EGG.get()))).
                            build(),
                            this.generateAmbientSound(NaturalistSoundEvents.OSTRICH_AMBIENT.get()))).toList(),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.DESERT_SCORPION.get(),
                List.of(this.generateAmbientSound(NaturalistSoundEvents.SCORPION_AMBIENT.get())),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.JUNGLE_SCORPION.get(),
                List.of(this.generateAmbientSound(NaturalistSoundEvents.SCORPION_AMBIENT.get())),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.RAT.get(),
                Stream.concat(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.RAT_FOOD)).stream(),
                    Stream.of(this.generateAmbientSound(NaturalistSoundEvents.RAT_AMBIENT.get()))).toList(),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.TIGER.get(),
                Stream.concat(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.TIGER_FOOD_ITEMS)).stream(),
                    Stream.of(this.generateAmbientSound(NaturalistSoundEvents.TIGER_AMBIENT.get()))).toList(),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.TURKEY.get(),
                Stream.concat(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.TURKEY_FOOD_ITEMS)).stream(),
                    Stream.of(this.generateAmbientSound(NaturalistSoundEvents.TURKEY_AMBIENT.get()))).toList(),
                Naturalist.MOD_ID));

            // Aviary
            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.BUTTERFLY.get(),
                this.generateFood(CustomIngredient.of(ItemTags.FLOWERS)),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.FIREFLY.get(),
                List.of(),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.BIRD.get(),
                Stream.concat(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.BIRD_FOOD_ITEMS)).stream(),
                    Stream.of(this.generateAmbientSound(NaturalistSoundEvents.BIRD_AMBIENT_BLUEJAY.get()))).toList(),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.VULTURE.get(),
                Stream.concat(this.generateFood(CustomIngredient.of(Items.ROTTEN_FLESH)).stream(),
                    Stream.of(this.generateAmbientSound(NaturalistSoundEvents.VULTURE_AMBIENT.get()))).toList(),
                Naturalist.MOD_ID));

            futureList.add(
                this.generateWithInteractions(cache,
                    NaturalistEntityTypes.DRAGONFLY.get(),
                    List.of(),
                    Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.DUCK.get(),
                Stream.concat(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.DUCK_FOOD_ITEMS)).stream(),
                    Stream.of(AnimalInteractionBuilder.create("eggs").
                        ingredient(CustomIngredient.of(Items.BUCKET)).
                        lootEntry(LootEntry.of(ResourceLocation.tryBuild(Naturalist.MOD_ID, "animal_interactions/bucket/duck_egg"),
                            320,
                            true)).
                        cooldown(new CooldownEntry.Linear(6000, -20, 200)).
                        redstoneBit(2).
                        textLines(TextEntry.ready("display.animal_pen.full_ready",
                            CustomIngredient.of(NaturalistRegistry.DUCK_EGG.get()))).
                        textLines(TextEntry.cooldown("display.animal_pen.egg_cooldown",
                            CustomIngredient.of(NaturalistRegistry.DUCK_EGG.get()))).
                        build(),
                    this.generateAmbientSound(NaturalistSoundEvents.DUCK_AMBIENT.get()))).toList(),
                Naturalist.MOD_ID));

            // Aquarium
            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.CATFISH.get(),
                List.of(
                    this.generateBucketable(Items.WATER_BUCKET, NaturalistRegistry.CATFISH_BUCKET.get(), 2),
                    this.generateAmbientSound(SoundEvents.SALMON_AMBIENT)),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.BASS.get(),
                List.of(
                    this.generateBucketable(Items.WATER_BUCKET, NaturalistRegistry.BASS_BUCKET.get(), 2),
                    this.generateAmbientSound(SoundEvents.SALMON_AMBIENT)),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.ANGLERFISH.get(),
                List.of(
                    this.generateBucketable(Items.WATER_BUCKET, NaturalistRegistry.ANGLERFISH_BUCKET.get(), 2),
                    this.generateAmbientSound(NaturalistSoundEvents.ANGLERFISH_SWIM.get())),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.RAY.get(),
                List.of(
                    this.generateBucketable(Items.WATER_BUCKET, NaturalistRegistry.RAY_BUCKET.get(), 2),
                    this.generateAmbientSound(NaturalistSoundEvents.RAY_SWIM.get())),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.BLOBFISH.get(),
                List.of(
                    this.generateBucketable(Items.WATER_BUCKET, NaturalistRegistry.BLOBFISH_BUCKET.get(), 2),
                    this.generateAmbientSound(NaturalistSoundEvents.BLOBFISH_SWIM.get())),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.PIRANHA.get(),
                List.of(
                    this.generateBucketable(Items.WATER_BUCKET, NaturalistRegistry.PIRANHA_BUCKET.get(), 2),
                    this.generateAmbientSound(NaturalistSoundEvents.PIRANHA_FLOP.get())),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.CLAM.get(),
                List.of(this.generateAmbientSound(NaturalistSoundEvents.CLAM_AMBIENT.get())),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.GREAT_WHITE_SHARK.get(),
                Stream.concat(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.GREAT_WHITE_SHARK_FOOD_ITEMS)).stream(),
                    Stream.of(this.generateAmbientSound(NaturalistSoundEvents.GREAT_WHITE_SHARK_AMBIENT.get()))).toList(),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.JELLYFISH.get(),
                List.of(
                    this.generateBucketable(Items.WATER_BUCKET, NaturalistRegistry.JELLYFISH_BUCKET.get(), 2),
                    this.generateAmbientSound(NaturalistSoundEvents.JELLYFISH_IDLE.get())),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.STARFISH.get(),
                List.of(this.generateBucketable(Items.WATER_BUCKET, NaturalistRegistry.STARFISH_BUCKET.get(), 2)),
                Naturalist.MOD_ID));

            futureList.add(this.generateWithInteractions(cache,
                NaturalistEntityTypes.WHALE.get(),
                Stream.concat(this.generateFood(
                    CustomIngredient.of(Items.COD,
                        Items.COOKED_COD,
                        Items.SALMON,
                        Items.COOKED_SALMON,
                        NaturalistRegistry.CRAB_MEAT.get(),
                        NaturalistRegistry.COOKED_CRAB_MEAT.get())).stream(),
                    Stream.of(this.generateAmbientSound(NaturalistSoundEvents.WHALE_AMBIENT.get()))).toList(),
                Naturalist.MOD_ID));

            return CompletableFuture.allOf(futureList.toArray(CompletableFuture[]::new));
        });
    }


    private List<ItemStack> generateSnailColors()
    {
        List<ItemStack> colors = new ArrayList<>();

        for (Snail.Color value : Snail.Color.values())
        {
            ItemStack instance = NaturalistRegistry.SNAIL.get().getDefaultInstance();
            CompoundTag compoundTag = new CompoundTag();
            compoundTag.putInt("Color", value.getId());
            instance.set(DataComponents.CUSTOM_DATA, CustomData.of(compoundTag));
            colors.add(instance);
        }

        return colors;
    }


    private final CompletableFuture<HolderLookup.Provider> registry;
}