package de.nikey.mcfifa;

import com.mojang.logging.LogUtils;
import de.nikey.mcfifa.events.ModEvents;
import de.nikey.mcfifa.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.level.NoteBlockEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.checkerframework.checker.units.qual.C;
import org.slf4j.Logger;

@Mod(MCFIFA.MODID)
public class MCFIFA {

    public static final String MODID = "mcfifa";
    private static final Logger LOGGER = LogUtils.getLogger();
    private int sprintTickCounter = 0;
    private int times;

    public MCFIFA() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);

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

    }
    private void addCreative(BuildCreativeModeTabContentsEvent event){
        if (event.getTabKey() == CreativeModeTabs.COMBAT){
            event.accept(ModItems.FOOTBALL_BOOTS);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Mod starting!");
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();

        // Überprüfe, ob der Spieler Diamantschuhe trägt und sprintet
        if (entity.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.FEET).getItem().equals(ModItems.FOOTBALL_BOOTS) && entity instanceof Player) {
            Player player = (Player) entity;
            player.sendSystemMessage(Component.literal("tick"));
            if (entity.isSprinting()){
                sprintTickCounter++;
                if (sprintTickCounter >= 20) {
                    times++;
                    sprintTickCounter = 0;
                    player.sendSystemMessage(Component.literal("You speed increased!"));
                    if (times == 1){
                        increaseSprintSpeed(player,1.2f);
                    } else if (times == 2){
                        increaseSprintSpeed(player,1.5f);
                    } else if (times == 3) {
                        increaseSprintSpeed(player,1.7f);
                    }else if (times == 4){
                        times=0;
                        increaseSprintSpeed(player,2.0f);
                    }
                }
            }else {
                sprintTickCounter = 0;
            }

        }else{
            if (entity instanceof Player){
                Player player = (Player) entity;
                player.getAbilities().setWalkingSpeed(0.1f);
            }
        }
    }
    private void increaseSprintSpeed(Player player,float f) {
        // Erhöhen Sie die Geschwindigkeit des Spielers um 10% (Multiplikation mit 1.1)
        float newSpeed = player.getAbilities().getWalkingSpeed() * 1.1f;
        player.getAbilities().setWalkingSpeed(newSpeed);
    }
}
