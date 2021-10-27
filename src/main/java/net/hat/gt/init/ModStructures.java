package net.hat.gt.init;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.hat.gt.structures.test.TestFeature;
import net.hat.gt.structures.test.TestGenerator;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class ModStructures {
    public static final StructurePieceType TEST_PIECE = TestGenerator.Piece::new;
    private static final StructureFeature<DefaultFeatureConfig> TEST_STRUCTURE = new TestFeature(DefaultFeatureConfig.CODEC);
    private static final ConfiguredStructureFeature<?, ?> TEST_CONFIGURED = TEST_STRUCTURE.configure(DefaultFeatureConfig.DEFAULT);

    @SuppressWarnings("deprecation")
    public static void registerStructures() {
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier("goblintraders", "test_piece"), TEST_PIECE);
        FabricStructureBuilder.create(new Identifier("goblintraders", "test_structure"), TEST_STRUCTURE)
                .step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(32, 8, 12345)
                .adjustsSurface()
                .register();

        RegistryKey<ConfiguredStructureFeature<?, ?>> testConfigured = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_KEY,
                new Identifier("goblintraders", "test_structure"));
        BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, testConfigured.getValue(), TEST_CONFIGURED);

        BiomeModifications.addStructure(BiomeSelectors.all(), testConfigured);
    }
}
