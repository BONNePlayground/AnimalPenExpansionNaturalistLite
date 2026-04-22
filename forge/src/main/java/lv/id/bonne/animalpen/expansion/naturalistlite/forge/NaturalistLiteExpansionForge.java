package lv.id.bonne.animalpen.expansion.naturalistlite.forge;

import lv.id.bonne.animalpen.expansion.naturalistlite.NaturalistLiteExpansion;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(NaturalistLiteExpansion.MOD_ID)
public final class NaturalistLiteExpansionForge
{
    public NaturalistLiteExpansionForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(NaturalistLiteExpansion.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        // Run our common setup.
        NaturalistLiteExpansion.init();
    }
}
