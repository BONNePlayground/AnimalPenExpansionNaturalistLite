package lv.id.bonne.animalpen.expansion.naturalistlite.forge.provider;


import org.jetbrains.annotations.Nullable;

import lv.id.bonne.animalpen.data.helper.SimpleTagAppender;
import lv.id.bonne.animalpen.expansion.naturalistlite.NaturalistLiteExpansion;
import lv.id.bonne.animalpen.expansion.naturalistlite.provider.ModEntityTypeTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.server.EntityTypeTagProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.tag.TagKey;
import net.minecraftforge.common.data.ExistingFileHelper;


public class ForgeModEntityTypeTagProvider extends EntityTypeTagProvider implements ModEntityTypeTagsProvider
{
    public ForgeModEntityTypeTagProvider(DataGenerator arg,
        String modId,
        @Nullable ExistingFileHelper existingFileHelper)
    {
        super(arg, modId, existingFileHelper);
    }


    @Override
    protected void configure()
    {
        this.addModTags();
    }


    @Override
    public SimpleTagAppender<EntityType<?>> modTag(TagKey<EntityType<?>> tag)
    {
        var builder = this.getTagBuilder(tag);

        return new SimpleTagAppender<>() {

            @Override
            public SimpleTagAppender<EntityType<?>> add(EntityType<?> value) {
                builder.addOptional(value.getRegistryEntry().registryKey().getValue(), NaturalistLiteExpansion.MOD_ID);
                return this;
            }
        };
    }
}