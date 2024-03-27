package net.callumherr.potionrings.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.Arrays;
import java.util.List;

public class RingItem extends Item implements ICurioItem {
    public RingItem(Properties pProperties) {
        super(pProperties.stacksTo(1));
    }

    @Override
    public String getDescriptionId(ItemStack pStack) {
        String prefix = "potion_rings.item.";
        CompoundTag tags = pStack.getTagElement("Ring");

        if (tags == null || tags.getInt("effect") == 0) return prefix + "enchanted_ring";

        MobEffect effect = MobEffect.byId(tags.getInt("effect"));

        if (!effect.getDescriptionId().startsWith("effect.minecraft")
                || effect.isInstantenous()) return prefix + "enchanted_ring";

        return effect.getDescriptionId().replace("effect.minecraft.",
                prefix + "ring_of_");
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        CompoundTag tags = pStack.getTagElement("Ring");
        if (tags == null) return;
        int effectId = tags.getInt("effect");
        if (effectId == 0) return;

        MobEffect effect= MobEffect.byId(effectId);
        int amp = tags.getInt("amp");
        MobEffectInstance inst = new MobEffectInstance(effect, -1, amp,
                false, false);

        PotionUtils.addPotionTooltip(Arrays.asList(inst), pTooltipComponents, 1);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        CompoundTag tags = stack.getTagElement("Ring");
        if (tags == null) return;

        int effectId = tags.getInt("effect");
        if (effectId == 0) return;

        MobEffect effect = MobEffect.byId(effectId);
        if (slotContext.entity().hasEffect(effect)) return;

        int amplifier = tags.getInt("amp");
        slotContext.entity().addEffect(new MobEffectInstance(effect, -1,
                amplifier, false, false));
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        CompoundTag tags = stack.getTagElement("Ring");
        if (tags == null) return;

        int effectId = tags.getInt("effect");
        if (effectId == 0) return;
        MobEffect effect = MobEffect.byId(effectId);

        if (!slotContext.entity().hasEffect(effect)) return;
        slotContext.entity().removeEffect(effect);
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }
}
