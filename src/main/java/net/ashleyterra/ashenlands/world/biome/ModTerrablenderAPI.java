package net.ashleyterra.ashenlands.world.biome;

import net.ashleyterra.ashenlands.AshenLands;
import net.ashleyterra.ashenlands.world.biome.surface.ModMaterialRules;
import net.minecraft.util.Identifier;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class ModTerrablenderAPI implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new ModOverworldRegion(Identifier.of(AshenLands.MOD_ID,"overworld"),5));

        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD,AshenLands.MOD_ID, ModMaterialRules.makeRules());
    }
}
