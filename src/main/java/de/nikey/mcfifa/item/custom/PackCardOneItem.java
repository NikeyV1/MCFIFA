package de.nikey.mcfifa.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PackCardOneItem extends Item {
    public PackCardOneItem(Properties pProperties) {
        super(pProperties);
    }
    @SubscribeEvent
    public PlayerInteractEvent.RightClickItem useOn(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getEntity();
        player.sendSystemMessage(Component.literal("Gay"));
        return event;
    }
}
