package lv.id.bonne.animalpen.expansion.naturalistlite.forge.provider;


import lv.id.bonne.animalpen.AnimalPen;
import lv.id.bonne.animalpen.expansion.naturalistlite.provider.ModAnimalInteractionProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AnimalPenForgeDataGen
{
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();

        if (event.includeServer())
        {
            generator.addProvider(new ModAnimalInteractionProvider(generator));

            generator.addProvider(new ForgeModEntityTypeTagProvider(generator,
                AnimalPen.MOD_ID,
                event.getExistingFileHelper()));
        }
    }
}