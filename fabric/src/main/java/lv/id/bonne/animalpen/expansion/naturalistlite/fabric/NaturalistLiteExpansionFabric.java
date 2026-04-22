package lv.id.bonne.animalpen.expansion.naturalistlite.fabric;

import lv.id.bonne.animalpen.expansion.naturalistlite.NaturalistLiteExpansion;
import net.fabricmc.api.ModInitializer;

public final class NaturalistLiteExpansionFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // Run our common setup.
        NaturalistLiteExpansion.init();
    }
}
