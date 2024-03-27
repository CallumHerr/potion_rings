package net.callumherr.potionrings.items;

import net.callumherr.potionrings.PotionRings;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CreativeTab {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PotionRings.MODID);

    public static final RegistryObject<CreativeModeTab> RINGS = TABS.register("rings_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("item_group.potion_rings.rings"))
                    .icon(() -> getRing(MobEffects.MOVEMENT_SPEED, 1))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(getRing(MobEffects.NIGHT_VISION, 1));
                        pOutput.accept(getRing(MobEffects.INVISIBILITY, 1));
                        pOutput.accept(getRing(MobEffects.JUMP, 1));
                        pOutput.accept(getRing(MobEffects.JUMP, 2));
                        pOutput.accept(getRing(MobEffects.FIRE_RESISTANCE, 1));
                        pOutput.accept(getRing(MobEffects.MOVEMENT_SPEED, 1));
                        pOutput.accept(getRing(MobEffects.MOVEMENT_SPEED, 2));
                        pOutput.accept(getRing(MobEffects.WATER_BREATHING, 1));
                        pOutput.accept(getRing(MobEffects.REGENERATION, 1));
                        pOutput.accept(getRing(MobEffects.FIRE_RESISTANCE, 2));
                        pOutput.accept(getRing(MobEffects.DAMAGE_BOOST, 1));
                        pOutput.accept(getRing(MobEffects.DAMAGE_BOOST, 2));
                        pOutput.accept(getRing(MobEffects.LUCK, 1));
                        pOutput.accept(getRing(MobEffects.SLOW_FALLING, 1));

                        pOutput.accept(getRing(MobEffects.MOVEMENT_SLOWDOWN, 1));
                        pOutput.accept(getRing(MobEffects.MOVEMENT_SLOWDOWN, 4));
                        pOutput.accept(getRing(MobEffects.POISON, 1));
                        pOutput.accept(getRing(MobEffects.POISON, 2));
                        pOutput.accept(getRing(MobEffects.WEAKNESS, 1));
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }

    private static ItemStack getRing(MobEffect effect, int amp) {
        ItemStack stack = new ItemStack(ModItems.POTION_RING.get());
        CompoundTag tags = stack.getOrCreateTagElement("Ring");
        tags.putInt("effect", MobEffect.getId(effect));
        tags.putInt("amp", amp-1);
        return  stack;
    }
}
