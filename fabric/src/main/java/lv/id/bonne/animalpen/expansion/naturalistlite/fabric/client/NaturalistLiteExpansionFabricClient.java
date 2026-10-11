package lv.id.bonne.animalpen.expansion.naturalistlite.fabric.client;


import lv.id.bonne.animalpen.expansion.naturalistlite.client.NaturalistLiteExpansionClient;
import net.fabricmc.api.ClientModInitializer;


public final class NaturalistLiteExpansionFabricClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        NaturalistLiteExpansionClient.init();
    }
}