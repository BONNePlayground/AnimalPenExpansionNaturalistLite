package lv.id.bonne.animalpen.expansion.naturalistlite.forge.provider;


import java.util.List;
import java.util.Set;

import lv.id.bonne.animalpen.expansion.naturalistlite.forge.provider.loottable.ForgeModGiftLootProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;


public class ForgeModLootTableProvider extends LootTableProvider
{
    public ForgeModLootTableProvider(PackOutput output) {
        super(output, Set.of(), List.of(
            new SubProviderEntry(ForgeModGiftLootProvider::new, LootContextParamSets.GIFT)
        ));
    }
}