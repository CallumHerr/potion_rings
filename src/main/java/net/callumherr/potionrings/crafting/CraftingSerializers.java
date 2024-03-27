package net.callumherr.potionrings.crafting;

import net.callumherr.potionrings.PotionRings;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.SpecialRecipeBuilder;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CraftingSerializers {
    public static final DeferredRegister<RecipeSerializer> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS.getRegistryName(), PotionRings.MODID);

    public static final RegistryObject<SimpleCraftingRecipeSerializer> RING_RECIPE_SERIALIZER =
            SERIALIZERS.register("ring_recipe_serializer", () ->
                    new SimpleCraftingRecipeSerializer<>(RingCraftingRecipe::new));

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
