package lv.id.bonne.animalpen.expansion.naturalistlite.forge.provider.loottable;


import com.starfish_studios.naturalist.Naturalist;
import com.starfish_studios.naturalist.core.registry.NaturalistBlocks;
import com.starfish_studios.naturalist.core.registry.NaturalistItems;
import java.util.function.BiConsumer;

import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;


public class ForgeModGiftLootProvider implements LootTableSubProvider
{
    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> consumer)
    {
        consumer.accept(
            ResourceLocation.tryBuild(Naturalist.MOD_ID, "animal_interactions/bucket/alligator_egg"),
            LootTable.lootTable().withPool(LootPool.lootPool().
                setRolls(ConstantValue.exactly(1)).
                add(LootItem.lootTableItem(NaturalistBlocks.ALLIGATOR_EGG.get())))
        );

        consumer.accept(
            ResourceLocation.tryBuild(Naturalist.MOD_ID, "animal_interactions/bucket/tortoise_egg"),
            LootTable.lootTable().withPool(LootPool.lootPool().
                setRolls(ConstantValue.exactly(1)).
                add(LootItem.lootTableItem(NaturalistBlocks.TORTOISE_EGG.get().asItem())))
        );

        consumer.accept(
            ResourceLocation.tryBuild(Naturalist.MOD_ID, "animal_interactions/bucket/duck_egg"),
            LootTable.lootTable().withPool(LootPool.lootPool().
                setRolls(ConstantValue.exactly(1)).
                add(LootItem.lootTableItem(NaturalistItems.DUCK_EGG.get())))
        );
    }
}