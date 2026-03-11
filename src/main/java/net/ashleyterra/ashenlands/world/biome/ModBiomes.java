package net.ashleyterra.ashenlands.world.biome;

import net.ashleyterra.ashenlands.AshenLands;
import net.minecraft.client.sound.MusicTracker;
import net.minecraft.client.sound.Sound;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.util.Identifier;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.MiscPlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

public class ModBiomes {
    public static final RegistryKey<Biome> ASHEN_LAND = RegistryKey.of(RegistryKeys.BIOME,
            Identifier.of(AshenLands.MOD_ID,"ashen_lands"));

    public static void bootstrap(Registerable<Biome> context) {
        context.register(ASHEN_LAND,ashen_land(context));
    }

    public static void globalOverworldGeneration(GenerationSettings.LookupBackedBuilder builder) {
        DefaultBiomeFeatures.addLandCarvers(builder);
        DefaultBiomeFeatures.addAmethystGeodes(builder);
        DefaultBiomeFeatures.addDungeons(builder);
        DefaultBiomeFeatures.addMineables(builder);
        DefaultBiomeFeatures.addSprings(builder);
        DefaultBiomeFeatures.addFrozenTopLayer(builder);
    }

    public static Biome ashen_land(Registerable<Biome> context) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();

        spawnBuilder.spawn(SpawnGroup.MONSTER,new SpawnSettings.SpawnEntry(EntityType.HUSK,8,1,7));
        spawnBuilder.spawn(SpawnGroup.MONSTER,new SpawnSettings.SpawnEntry(EntityType.STRAY,6,2,4));
        spawnBuilder.spawn(SpawnGroup.MONSTER,new SpawnSettings.SpawnEntry(EntityType.ENDERMAN,2,1,1));
        spawnBuilder.spawn(SpawnGroup.MONSTER,new SpawnSettings.SpawnEntry(EntityType.SILVERFISH,2,3,8));
        spawnBuilder.spawn(SpawnGroup.CREATURE,new SpawnSettings.SpawnEntry(EntityType.CAMEL,3,1,1));
        spawnBuilder.spawn(SpawnGroup.CREATURE,new SpawnSettings.SpawnEntry(EntityType.ARMADILLO,4,1,3));
        spawnBuilder.spawn(SpawnGroup.CREATURE,new SpawnSettings.SpawnEntry(EntityType.DONKEY,4,2,4));
        spawnBuilder.spawn(SpawnGroup.CREATURE,new SpawnSettings.SpawnEntry(EntityType.MULE,4,1,2));
        spawnBuilder.spawn(SpawnGroup.CREATURE,new SpawnSettings.SpawnEntry(EntityType.OCELOT,3,3,3));
        spawnBuilder.spawn(SpawnGroup.CREATURE,new SpawnSettings.SpawnEntry(EntityType.RABBIT,6,4,5));
        spawnBuilder.spawn(SpawnGroup.CREATURE,new SpawnSettings.SpawnEntry(EntityType.RABBIT,6,1,5));

        GenerationSettings.LookupBackedBuilder biomeBuilder =
                new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE),
                        context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        globalOverworldGeneration(biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
        DefaultBiomeFeatures.addAncientDebris(biomeBuilder);
        DefaultBiomeFeatures.addClayOre(biomeBuilder);
        DefaultBiomeFeatures.addClayDisk(biomeBuilder);
        DefaultBiomeFeatures.addDesertDeadBushes(biomeBuilder);
        DefaultBiomeFeatures.addDungeons(biomeBuilder);
        DefaultBiomeFeatures.addFossils(biomeBuilder);
        DefaultBiomeFeatures.addInfestedStone(biomeBuilder);
        DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);

        biomeBuilder.feature(GenerationStep.Feature.SURFACE_STRUCTURES, MiscPlacedFeatures.LAKE_LAVA_UNDERGROUND);
        biomeBuilder.feature(GenerationStep.Feature.SURFACE_STRUCTURES, MiscPlacedFeatures.LAKE_LAVA_SURFACE);
        biomeBuilder.feature(GenerationStep.Feature.SURFACE_STRUCTURES, MiscPlacedFeatures.DISK_GRAVEL);
        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.BROWN_MUSHROOM_NORMAL);
        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_CACTUS_DESERT);
        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_SAVANNA);

        return new Biome.Builder()
                .precipitation(true)
                .downfall(0.2f)
                .temperature(2.0f)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects((new BiomeEffects.Builder())
                        .waterColor(0xa4b9a2)
                        .waterFogColor(0xbf1b26)
                        .skyColor(0x30c918)
                        .grassColor(0x7f03fc)
                        .foliageColor(0xd203fc)
                        .fogColor(0x22a1e6)
                        .moodSound(BiomeMoodSound.CAVE)
                        .music(MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_DRIPSTONE_CAVES)).build())
                .build();
    }
}
