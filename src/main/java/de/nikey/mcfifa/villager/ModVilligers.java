package de.nikey.mcfifa.villager;

import de.nikey.mcfifa.MCFIFA;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModVilligers {
    public static final DeferredRegister<PoiType> POI_TYPES
            = DeferredRegister.create(ForgeRegistries.POI_TYPES, MCFIFA.MODID);

    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSION
            = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS,MCFIFA.MODID);

    public static final RegistryObject<PoiType> FIFA_PACK_POI = POI_TYPES.register("fifa_pack_poi",
            () -> new PoiType("fifa_pack_poi", PoiType.NONE))


    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSION.register(eventBus);
    }
}
