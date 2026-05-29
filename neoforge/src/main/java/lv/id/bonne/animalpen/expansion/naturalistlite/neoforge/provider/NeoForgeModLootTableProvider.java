package lv.id.bonne.animalpen.expansion.naturalistlite.neoforge.provider;


import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import lv.id.bonne.animalpen.expansion.naturalistlite.neoforge.provider.loottable.NeoForgeModGiftLootProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;


public class NeoForgeModLootTableProvider extends LootTableProvider
{
    public NeoForgeModLootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider)
    {
        super(output,
            Set.of(),
            List.of(new SubProviderEntry(NeoForgeModGiftLootProvider::new, LootContextParamSets.GIFT)),
            lookupProvider);
    }
}