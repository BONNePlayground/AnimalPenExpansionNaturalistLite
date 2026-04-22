package lv.id.bonne.animalpen.expansion.naturalistlite.forge.provider;


import com.starfish_studios.naturalist.Naturalist;
import java.util.concurrent.CompletableFuture;

import lv.id.bonne.animalpen.AnimalPen;
import lv.id.bonne.animalpen.expansion.naturalistlite.provider.ModAnimalInteractionProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AnimalPenForgeDataGen
{
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = event.getGenerator().getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(),
            new ModAnimalInteractionProvider(packOutput, lookupProvider));

        generator.addProvider(event.includeServer(),
            new ForgeModEntityTypeTagProvider(packOutput,
                lookupProvider,
                Naturalist.MOD_ID,
                event.getExistingFileHelper()));

        generator.addProvider(event.includeServer(),
            new ForgeModLootTableProvider(generator));
    }
}