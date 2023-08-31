package de.nikey.mcfifa.item.custom;

import de.nikey.mcfifa.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import org.checkerframework.checker.units.qual.C;

public class PackCardOneItem extends Item {
    public PackCardOneItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        player.sendSystemMessage(Component.literal("Gay"));
        return super.useOn(context);
    }

}
