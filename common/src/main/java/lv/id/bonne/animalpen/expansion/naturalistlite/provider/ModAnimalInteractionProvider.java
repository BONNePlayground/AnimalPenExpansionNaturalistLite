package lv.id.bonne.animalpen.expansion.naturalistlite.provider;


import com.starfish_studios.naturalist.Naturalist;
import com.starfish_studios.naturalist.registry.NaturalistEntityTypes;
import com.starfish_studios.naturalist.registry.NaturalistItems;
import com.starfish_studios.naturalist.registry.NaturalistSoundEvents;
import com.starfish_studios.naturalist.registry.NaturalistTags;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

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
import net.minecraft.block.Blocks;
import net.minecraft.data.DataCache;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;


/**
 * Animal Interaction Data Provider.
 */
public class ModAnimalInteractionProvider extends AnimalInteractionProvider
{
    public ModAnimalInteractionProvider(DataGenerator generator)
    {
        super(generator);
        this.generator = generator;
    }


    @Override
    public void run(DataCache cache) throws IOException
    {
        Path basePath = this.generator.getOutput().resolve("data").
            resolve(Naturalist.MOD_ID).
            resolve("animal_interactions");

        // Animal pen
        this.generateWithInteractions(basePath, cache,
            NaturalistEntityTypes.SNAIL.get(),
            List.of(this.generateBucketable(Items.BUCKET, NaturalistItems.SNAIL_BUCKET.get(), 1),
                this.generateAmbientSound(NaturalistSoundEvents.SNAIL_FORWARD.get())),
            Naturalist.MOD_ID);

        this.generateWithInteractions(basePath, cache,
            NaturalistEntityTypes.BEAR.get(),
            List.of(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.BEAR_TEMPT_ITEMS)),
                AnimalInteractionBuilder.create("shearing").
                    ingredient(CustomIngredient.merge(CustomIngredient.of(Items.SHEARS),
                        CustomIngredient.of(AnimalPenTags.FORGE_SHEARS),
                        CustomIngredient.of(AnimalPenTags.COMMON_SHEARS))).
                    lootEntry(LootEntry.of(new Identifier(Naturalist.MOD_ID, "animal_interactions/shear/bear_fur"), 320, true)).
                    consume(new ConsumerEntry.Damage(1)).
                    cooldown(new CooldownEntry.Static(1200)).
                    sound(SoundEvents.ENTITY_SHEEP_SHEAR.getId()).
                    redstoneBit(2).
                    runFunctions(FunctionKey.of(AnimalPenFunctionRegistry.MOB_SET_SHEARED.get(), null, true)).
                    finishFunctions(FunctionKey.of(AnimalPenFunctionRegistry.MOB_SET_SHEARED.get(), null, false)).
                    textLines(TextEntry.ready("display.animal_pen.full_ready",
                        CustomIngredient.of(NaturalistItems.BEAR_FUR.get()))).
                    textLines(TextEntry.cooldown("display.animal_pen.wool_cooldown",
                        CustomIngredient.of(NaturalistItems.BEAR_FUR.get()))).
                    build(),
                this.generateAmbientSound(NaturalistSoundEvents.BEAR_AMBIENT.get())),
            Naturalist.MOD_ID);

        this.generateWithInteractions(basePath, cache,
            NaturalistEntityTypes.SNAKE.get(),
            List.of(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.SNAKE_TEMPT_ITEMS)),
                this.generateAmbientSound(NaturalistSoundEvents.SNAKE_HISS.get())),
            Naturalist.MOD_ID);
        this.generateWithInteractions(basePath, cache,
            NaturalistEntityTypes.CORAL_SNAKE.get(),
            List.of(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.SNAKE_TEMPT_ITEMS)),
                this.generateAmbientSound(NaturalistSoundEvents.SNAKE_HISS.get())),
            Naturalist.MOD_ID);
        this.generateWithInteractions(basePath, cache,
            NaturalistEntityTypes.RATTLESNAKE.get(),
            List.of(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.SNAKE_TEMPT_ITEMS)),
                this.generateAmbientSound(NaturalistSoundEvents.SNAKE_RATTLE.get())),
            Naturalist.MOD_ID);

        this.generateWithInteractions(basePath, cache,
            NaturalistEntityTypes.DEER.get(),
            List.of(this.generateFood(CustomIngredient.of(Items.APPLE)),
                this.generateAmbientSound(NaturalistSoundEvents.DEER_AMBIENT.get())),
            Naturalist.MOD_ID);

        this.generateWithInteractions(basePath, cache,
            NaturalistEntityTypes.CATERPILLAR.get(),
            List.of(this.generateFood(CustomIngredient.of(ItemTags.FLOWERS))),
            Naturalist.MOD_ID);

        this.generateWithInteractions(basePath, cache,
            NaturalistEntityTypes.RHINO.get(),
            List.of(this.generateAmbientSound(NaturalistSoundEvents.RHINO_AMBIENT.get())),
            Naturalist.MOD_ID);

        this.generateWithInteractions(basePath, cache,
            NaturalistEntityTypes.LION.get(),
            List.of(this.generateAmbientSound(NaturalistSoundEvents.LION_AMBIENT.get())),
            Naturalist.MOD_ID);

        this.generateWithInteractions(basePath, cache,
            NaturalistEntityTypes.ELEPHANT.get(),
            List.of(this.generateAmbientSound(NaturalistSoundEvents.ELEPHANT_AMBIENT.get())),
            Naturalist.MOD_ID);
        this.generateWithInteractions(basePath, cache,
            NaturalistEntityTypes.ZEBRA.get(),
            List.of(this.generateFood(CustomIngredient.of(Items.WHEAT,
                    Items.SUGAR,
                    Blocks.HAY_BLOCK.asItem(),
                    Items.APPLE,
                    Items.GOLDEN_CARROT,
                    Items.GOLDEN_APPLE,
                    Items.ENCHANTED_GOLDEN_APPLE)),
                this.generateAmbientSound(NaturalistSoundEvents.ZEBRA_AMBIENT.get())),
            Naturalist.MOD_ID);
        this.generateWithInteractions(basePath, cache,
            NaturalistEntityTypes.GIRAFFE.get(),
            List.of(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.GIRAFFE_FOOD_ITEMS)),
                this.generateAmbientSound(NaturalistSoundEvents.GIRAFFE_AMBIENT.get())),
            Naturalist.MOD_ID);
        this.generateWithInteractions(basePath, cache,
            NaturalistEntityTypes.HIPPO.get(),
            List.of(this.generateFood(CustomIngredient.of(Blocks.MELON)),
                this.generateAmbientSound(NaturalistSoundEvents.HIPPO_AMBIENT.get())),
            Naturalist.MOD_ID);

        this.generateWithInteractions(basePath, cache,
            NaturalistEntityTypes.BOAR.get(),
            List.of(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.BOAR_FOOD_ITEMS)),
                this.generateAmbientSound(SoundEvents.ENTITY_PIG_AMBIENT)),
            Naturalist.MOD_ID);

        // Aviary
        this.generateWithInteractions(basePath, cache,
            NaturalistEntityTypes.BUTTERFLY.get(),
            List.of(this.generateFood(CustomIngredient.of(ItemTags.FLOWERS))),
            Naturalist.MOD_ID);

        this.generateWithInteractions(basePath, cache,
            NaturalistEntityTypes.FIREFLY.get(),
            List.of(),
            Naturalist.MOD_ID);

        this.generateWithInteractions(basePath, cache,
            NaturalistEntityTypes.BLUEJAY.get(),
            List.of(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.BIRD_FOOD_ITEMS)),
                this.generateAmbientSound(NaturalistSoundEvents.BIRD_AMBIENT_BLUEJAY.get())),
            Naturalist.MOD_ID);

        this.generateWithInteractions(basePath, cache,
            NaturalistEntityTypes.CANARY.get(),
            List.of(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.BIRD_FOOD_ITEMS)),
                this.generateAmbientSound(NaturalistSoundEvents.BIRD_AMBIENT_CANARY.get())),
            Naturalist.MOD_ID);

        this.generateWithInteractions(basePath, cache,
            NaturalistEntityTypes.CARDINAL.get(),
            List.of(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.BIRD_FOOD_ITEMS)),
                this.generateAmbientSound(NaturalistSoundEvents.BIRD_AMBIENT_CARDINAL.get())),
            Naturalist.MOD_ID);

        this.generateWithInteractions(basePath, cache,
            NaturalistEntityTypes.ROBIN.get(),
            List.of(this.generateFood(CustomIngredient.of(NaturalistTags.ItemTags.BIRD_FOOD_ITEMS)),
                this.generateAmbientSound(NaturalistSoundEvents.BIRD_AMBIENT_ROBIN.get())),
            Naturalist.MOD_ID);

        this.generateWithInteractions(basePath, cache,
            NaturalistEntityTypes.VULTURE.get(),
            List.of(this.generateFood(CustomIngredient.of(Items.ROTTEN_FLESH)),
                this.generateAmbientSound(NaturalistSoundEvents.VULTURE_AMBIENT.get())),
            Naturalist.MOD_ID);
    }


// ---------------------------------------------------------------------
// Section: Variables
// ---------------------------------------------------------------------


    private final DataGenerator generator;
}