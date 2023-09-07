package de.nikey.mcfifa;

import com.mojang.logging.LogUtils;
import de.nikey.mcfifa.block.ModBlocks;
import de.nikey.mcfifa.events.ModEvents;
import de.nikey.mcfifa.item.ModItems;
import de.nikey.mcfifa.villager.ModVilligers;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

@Mod(MCFIFA.MODID)
public class MCFIFA {

    public static final String MODID = "mcfifa";
    public static final Logger LOGGER = LogUtils.getLogger();
    private int sprintTickCounter = 0;
    private int times;

    public MCFIFA() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModVilligers.register(modEventBus);
        ModBlocks.register(modEventBus);

        modEventBus.addListener(this::commonSetup);


        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ModEvents());
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
        //event.enqueueWork(ModVilligers::registerPOIs);

    }
    private void addCreative(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey() == CreativeModeTabs.COMBAT){
            event.accept(ModItems.FOOTBALL_BOOTS);
            event.accept(ModBlocks.HOPE_BLOCK.get());
            event.accept(ModItems.FIFA_PACK.get());
            event.accept(ModItems.TRIKO.get());
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Mod starting!");
    }



    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity instanceof Player player) {
            ItemStack boots = player.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.FEET);
            if (!boots.isEmpty() ){
                if (boots.getItem() == ModItems.FOOTBALL_BOOTS.get()){
                    if (player.isSprinting()){
                        sprintTickCounter++;
                        if (sprintTickCounter >= 20) {
                            times++;
                            sprintTickCounter = 0;
                            if (times == 3){
                                player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.2F);
                            } else if (times == 5){
                                player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.3F);
                            } else if (times == 7) {
                                player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.4F);
                            }else if (times == 10){
                                player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.8F);
                            }else if (times >12){
                                times=12;
                            }
                        }
                    }else {
                        player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.1);
                        sprintTickCounter = 0;
                        times = 0;
                    }
                }
            }
        }
    }
}
