package de.nikey.mcfifa.events;

import de.nikey.mcfifa.MCFIFA;
import de.nikey.mcfifa.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MCFIFA.MODID , bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {
    public static class ForgeEvents{

        @SubscribeEvent
        public void onLivingUpdate(LivingEvent.LivingTickEvent event) {
            if (event.getEntity() instanceof net.minecraft.server.level.ServerPlayer) {
                net.minecraft.server.level.ServerPlayer player = (net.minecraft.server.level.ServerPlayer) event.getEntity();
                player.sendSystemMessage(Component.literal("jup"));
                ItemStack boots = player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.FEET);

                if (!boots.isEmpty() && boots.getItem().equals(ModItems.FOOTBALL_BOOTS) && player.isSprinting()) {
                    player.sendSystemMessage(Component.literal("Gay"));
                }
            }
        }

        @SubscribeEvent
        public static void onTick(TickEvent.PlayerTickEvent event){
            event.player.sendSystemMessage(Component.literal("Gay"));
            if (event.side == LogicalSide.SERVER){
                Player player = event.player;
                player.sendSystemMessage(Component.literal("server"));
                if (player.hasItemInSlot(EquipmentSlot.FEET)){
                    if (player.getInventory().getArmor(8).getItem().equals(ModItems.FOOTBALL_BOOTS)){
                        player.sendSystemMessage(Component.literal("yes sir"));
                    }else {
                        player.sendSystemMessage(Component.literal("no"));
                    }
                }
            }
        }
    }
}
