package de.nikey.mcfifa.item;

import de.nikey.mcfifa.MCFIFA;
import de.nikey.mcfifa.item.custom.ModFoodballBoots;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraft.world.item.Rarity.EPIC;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MCFIFA.MODID);


    public static final RegistryObject<Item> FOOTBALL_BOOTS = ITEMS.register("football",
            () -> new ArmorItem(ModFoodballBoots.FOODBALL_BOOTS, ArmorItem.Type.BOOTS, new Item.Properties().rarity(EPIC)));

    public static final RegistryObject<Item> FIFA_PACK = ITEMS.register("fifa_pack",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> TRIKO = ITEMS.register("triko",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));        

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
