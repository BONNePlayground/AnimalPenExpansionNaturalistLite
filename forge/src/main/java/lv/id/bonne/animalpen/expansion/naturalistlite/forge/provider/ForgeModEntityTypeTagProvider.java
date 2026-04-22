package lv.id.bonne.animalpen.expansion.naturalistlite.forge.provider;


import org.jetbrains.annotations.Nullable;

import lv.id.bonne.animalpen.data.helper.SimpleTagAppender;
import lv.id.bonne.animalpen.expansion.naturalistlite.provider.ModEntityTypeTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;


public class ForgeModEntityTypeTagProvider extends EntityTypeTagsProvider implements ModEntityTypeTagsProvider
{
    public ForgeModEntityTypeTagProvider(DataGenerator arg,
        String modId,
        @Nullable ExistingFileHelper existingFileHelper)
    {
        super(arg, modId, existingFileHelper);
    }


    @Override
    protected void addTags()
    {
        this.addModTags();
    }


    @Override
    public SimpleTagAppender<EntityType<?>> modTag(TagKey<EntityType<?>> tag)
    {
        var builder = this.tag(tag);

        return new SimpleTagAppender<>() {

            @Override
            public SimpleTagAppender<EntityType<?>> add(EntityType<?> value) {
                builder.addOptional(value.builtInRegistryHolder().key().location());
                return this;
            }
        };
    }
}