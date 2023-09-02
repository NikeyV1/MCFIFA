package de.nikey.mcfifa.events;

import de.nikey.mcfifa.MCFIFA;
import de.nikey.mcfifa.item.ModItems;
import de.nikey.mcfifa.villager.ModVilligers;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = MCFIFA.MODID , bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {

    @SubscribeEvent
    public static void onItemRightClick(PlayerInteractEvent.RightClickItem event) {
        if (event.getItemStack().getItem() == ModItems.FIFA_PACK.get()){
            Player p = event.getEntity();
            Random random = new Random();
            int i = random.nextInt(40);
            ItemStack item = event.getItemStack();
            int count = item.getCount();
            item.setCount(count-1);

            if (i == 20){
                p.getInventory().add(ModItems.FOOTBALL_BOOTS.get().getDefaultInstance());
            }else {
                p.getInventory().add(Items.WHEAT_SEEDS.getDefaultInstance());
            }
        }
    }

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {

        if(event.getType() == ModVilligers.PACK_MASTER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.FIFA_PACK.get(), 1);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.NETHERITE_BLOCK, 1),
                    stack,3,8,0.02F));
        }
    }
}
