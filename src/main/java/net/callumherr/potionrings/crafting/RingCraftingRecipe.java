package net.callumherr.potionrings.crafting;

import com.mojang.logging.LogUtils;
import net.callumherr.potionrings.items.ModItems;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.FireworkRocketRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;
import org.jline.utils.Log;

import java.util.List;

public class RingCraftingRecipe extends CustomRecipe {
    public RingCraftingRecipe(ResourceLocation pId, CraftingBookCategory pCategory) {
        super(pId, pCategory);
    }

    @Override
    public boolean matches(CraftingContainer pContainer, Level pLevel) {
        LogUtils.getLogger().debug(pContainer.getItem(4).toString());
        for (int i = 0; i < pContainer.getContainerSize(); i++) {
            if (i == 4 && !pContainer.getItem(i).is(Items.POTION)) return false;
            else if (i == 4) return !PotionUtils.getMobEffects(pContainer.getItem(i))
                    .get(0).getEffect().isInstantenous();
            if (i != 4 && !pContainer.getItem(i).is(Items.NETHERITE_INGOT)) return false;
        }

        return true;
    }

    @Override
    public ItemStack assemble(CraftingContainer pContainer, RegistryAccess pRegistryAccess) {
        ItemStack potion = pContainer.getItem(4);
        MobEffectInstance mobEffect = PotionUtils.getMobEffects(potion).get(0);

        ItemStack result = new ItemStack(ModItems.POTION_RING.get());
        CompoundTag tag = result.getOrCreateTagElement("Ring");
        tag.putInt("effect", MobEffect.getId(mobEffect.getEffect()));
        tag.putInt("amp", mobEffect.getAmplifier());
        return result;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return new ItemStack(ModItems.POTION_RING.get());
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth * pHeight >= 9;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return CraftingSerializers.RING_RECIPE_SERIALIZER.get();
    }
}
