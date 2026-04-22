package lv.id.bonne.animalpen.expansion.naturalistlite.forge.provider;


import lv.id.bonne.animalpen.AnimalPen;
import lv.id.bonne.animalpen.expansion.naturalistlite.provider.ModAnimalInteractionProvider;
import net.minecraft.data.DataGenerator;
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

        if (event.includeServer())
        {
            generator.addProvider(true, new ModAnimalInteractionProvider(generator));

            generator.addProvider(true, new ForgeModEntityTypeTagProvider(generator,
                AnimalPen.MOD_ID,
                event.getExistingFileHelper()));
        }
    }
}