package lv.id.bonne.animalpen.expansion.naturalistlite;


import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import lv.id.bonne.animalpen.expansion.naturalistlite.functions.SnailChangeColor;
import lv.id.bonne.animalpen.processing.function.wrapper.EntityFunctionEntry;
import lv.id.bonne.animalpen.registries.AnimalPenFunctionRegistry;
import net.minecraft.resources.ResourceLocation;


public final class NaturalistLiteExpansion
{
    public static void init()
    {
        NaturalistLiteExpansion.ENTITY_FUNCTIONS.register();
    }


    public static ResourceLocation resourceOf(String key)
    {
        return ResourceLocation.fromNamespaceAndPath(NaturalistLiteExpansion.MOD_ID, key);
    }


    public static final DeferredRegister<EntityFunctionEntry> ENTITY_FUNCTIONS =
        DeferredRegister.create(
            NaturalistLiteExpansion.MOD_ID,
            AnimalPenFunctionRegistry.ENTITY_FUNCTIONS_REGISTRY_KEY
        );

    public static final RegistrySupplier<EntityFunctionEntry> SNAIL_CHANGE_COLOR =
        ENTITY_FUNCTIONS.register(
            NaturalistLiteExpansion.resourceOf("snail_change_color"),
            () -> new EntityFunctionEntry(new SnailChangeColor())
        );


    public static final String MOD_ID = "animal_pen_expansion_naturalist_lite";
}
