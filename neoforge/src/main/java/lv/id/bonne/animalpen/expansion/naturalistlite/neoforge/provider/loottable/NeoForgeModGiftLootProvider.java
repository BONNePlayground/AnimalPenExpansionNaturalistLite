package lv.id.bonne.animalpen.expansion.naturalistlite.neoforge.provider.loottable;


import com.crispytwig.naturalist.Naturalist;
import com.crispytwig.naturalist.registry.NaturalistRegistry;
import java.util.function.BiConsumer;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;


public class NeoForgeModGiftLootProvider implements LootTableSubProvider
{
    public NeoForgeModGiftLootProvider(HolderLookup.Provider provider)
    {
        super();
    }


    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer)
    {
        consumer.accept(
            ResourceKey.create(Registries.LOOT_TABLE,
                ResourceLocation.fromNamespaceAndPath(Naturalist.MOD_ID, "animal_interactions/bucket/alligator_egg")),
            LootTable.lootTable().withPool(LootPool.lootPool().
                setRolls(ConstantValue.exactly(1)).
                add(LootItem.lootTableItem(NaturalistRegistry.ALLIGATOR_EGG.get())))
        );

        consumer.accept(
            ResourceKey.create(Registries.LOOT_TABLE,
                ResourceLocation.fromNamespaceAndPath(Naturalist.MOD_ID, "animal_interactions/bucket/tortoise_egg")),
            LootTable.lootTable().withPool(LootPool.lootPool().
                setRolls(ConstantValue.exactly(1)).
                add(LootItem.lootTableItem(NaturalistRegistry.TORTOISE_EGG.get().asItem())))
        );

        consumer.accept(
            ResourceKey.create(Registries.LOOT_TABLE,
                ResourceLocation.fromNamespaceAndPath(Naturalist.MOD_ID, "animal_interactions/bucket/duck_egg")),
            LootTable.lootTable().withPool(LootPool.lootPool().
                setRolls(ConstantValue.exactly(1)).
                add(LootItem.lootTableItem(NaturalistRegistry.DUCK_EGG.get())))
        );

        consumer.accept(
            ResourceKey.create(Registries.LOOT_TABLE,
                ResourceLocation.fromNamespaceAndPath(Naturalist.MOD_ID, "animal_interactions/bucket/ostrich_egg")),
            LootTable.lootTable().withPool(LootPool.lootPool().
                setRolls(ConstantValue.exactly(1)).
                add(LootItem.lootTableItem(NaturalistRegistry.OSTRICH_EGG.get())))
        );

        consumer.accept(
            ResourceKey.create(Registries.LOOT_TABLE,
                ResourceLocation.fromNamespaceAndPath(Naturalist.MOD_ID, "animal_interactions/bucket/snail_eggs")),
            LootTable.lootTable().withPool(LootPool.lootPool().
                setRolls(ConstantValue.exactly(1)).
                add(LootItem.lootTableItem(NaturalistRegistry.SNAIL_EGGS.get())))
        );

        consumer.accept(
            ResourceKey.create(Registries.LOOT_TABLE,
                ResourceLocation.fromNamespaceAndPath(Naturalist.MOD_ID, "animal_interactions/drop/snail_slime")),
            LootTable.lootTable().withPool(LootPool.lootPool().
                setRolls(ConstantValue.exactly(1)).
                add(LootItem.lootTableItem(Items.SLIME_BALL)))
        );

        consumer.accept(
            ResourceKey.create(Registries.LOOT_TABLE,
                ResourceLocation.fromNamespaceAndPath(Naturalist.MOD_ID, "animal_interactions/shear/bear_fur")),
            LootTable.lootTable().withPool(LootPool.lootPool().
                setRolls(UniformGenerator.between(1, 3)).
                add(LootItem.lootTableItem(NaturalistRegistry.FUR.get())))
        );
    }
}