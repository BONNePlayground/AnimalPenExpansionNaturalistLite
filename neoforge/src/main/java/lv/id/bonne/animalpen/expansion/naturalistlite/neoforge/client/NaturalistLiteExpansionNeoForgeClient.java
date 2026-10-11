package lv.id.bonne.animalpen.expansion.naturalistlite.neoforge.client;


import lv.id.bonne.animalpen.expansion.naturalistlite.NaturalistLiteExpansion;
import lv.id.bonne.animalpen.expansion.naturalistlite.client.NaturalistLiteExpansionClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;


@EventBusSubscriber(modid = NaturalistLiteExpansion.MOD_ID, value = Dist.CLIENT)
public class NaturalistLiteExpansionNeoForgeClient
{
    @SubscribeEvent
    public static void init(FMLClientSetupEvent event)
    {
        NaturalistLiteExpansionClient.init();
    }
}