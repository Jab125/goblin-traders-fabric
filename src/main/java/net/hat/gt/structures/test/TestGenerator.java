package net.hat.gt.structures.test;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.loot.LootTables;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.*;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.Map;
import java.util.Random;

public class TestGenerator {
    public static final int field_31550 = 90;
    static final Identifier TOP_TEMPLATE = new Identifier("goblintraders", "shelter1");
    static final Map<Identifier, BlockPos> OFFSETS;
    static final Map<Identifier, BlockPos> OFFSETS_FROM_TOP;

    public TestGenerator() {
    }

    public static void addPieces(StructureManager manager, BlockPos pos, BlockRotation rotation, StructurePiecesHolder structurePiecesHolder, Random random) {
        structurePiecesHolder.addPiece(new TestGenerator.Piece(manager, TOP_TEMPLATE, pos, rotation, 0));
    }

    static {
        OFFSETS = ImmutableMap.of(TOP_TEMPLATE, new BlockPos(3, 5, 5), MIDDLE_TEMPLATE, new BlockPos(1, 3, 1), BOTTOM_TEMPLATE, new BlockPos(3, 6, 7));
        OFFSETS_FROM_TOP = ImmutableMap.of(TOP_TEMPLATE, BlockPos.ORIGIN, MIDDLE_TEMPLATE, new BlockPos(2, -3, 4), BOTTOM_TEMPLATE, new BlockPos(0, -3, -2));
    }

    public static class Piece extends SimpleStructurePiece {
        public Piece(StructureManager manager, Identifier identifier, BlockPos pos, BlockRotation rotation, int yOffset) {
            super(StructurePieceType.IGLOO, 0, manager, identifier, identifier.toString(), createPlacementData(rotation, identifier), getPosOffset(identifier, pos, yOffset));
        }

        public Piece(ServerWorld world, NbtCompound nbt) {
            super(StructurePieceType.IGLOO, nbt, world, (identifier) -> {
                return createPlacementData(BlockRotation.valueOf(nbt.getString("Rot")), identifier);
            });
        }

        private static StructurePlacementData createPlacementData(BlockRotation rotation, Identifier identifier) {
            return (new StructurePlacementData()).setRotation(rotation).setMirror(BlockMirror.NONE).setPosition((BlockPos) TestGenerator.OFFSETS.get(identifier)).addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS);
        }

        private static BlockPos getPosOffset(Identifier identifier, BlockPos pos, int yOffset) {
            return pos.add((Vec3i) TestGenerator.OFFSETS_FROM_TOP.get(identifier)).down(yOffset);
        }

        protected void writeNbt(ServerWorld world, NbtCompound nbt) {
            super.writeNbt(world, nbt);
            nbt.putString("Rot", this.placementData.getRotation().name());
        }

        protected void handleMetadata(String metadata, BlockPos pos, ServerWorldAccess world, Random random, BlockBox boundingBox) {
            if ("chest".equals(metadata)) {
                world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                BlockEntity blockEntity = world.getBlockEntity(pos.down());
                if (blockEntity instanceof ChestBlockEntity) {
                    ((ChestBlockEntity)blockEntity).setLootTable(LootTables.IGLOO_CHEST_CHEST, random.nextLong());
                }

            }
        }

        public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos pos) {
            Identifier identifier = new Identifier(this.identifier);
            StructurePlacementData structurePlacementData = createPlacementData(this.placementData.getRotation(), identifier);
            BlockPos blockPos = (BlockPos) TestGenerator.OFFSETS_FROM_TOP.get(identifier);
            BlockPos blockPos2 = this.pos.add(Structure.transform(structurePlacementData, new BlockPos(3 - blockPos.getX(), 0, -blockPos.getZ())));
            int i = world.getTopY(Heightmap.Type.WORLD_SURFACE_WG, blockPos2.getX(), blockPos2.getZ());
            BlockPos blockPos3 = this.pos;
            this.pos = this.pos.add(0, i - 90 - 1, 0);
            boolean bl = super.generate(world, structureAccessor, chunkGenerator, random, boundingBox, chunkPos, pos);
            if (identifier.equals(TestGenerator.TOP_TEMPLATE)) {
                BlockPos blockPos4 = this.pos.add(Structure.transform(structurePlacementData, new BlockPos(3, 0, 5)));
                BlockState blockState = world.getBlockState(blockPos4.down());
                if (!blockState.isAir() && !blockState.isOf(Blocks.LADDER)) {
                    world.setBlockState(blockPos4, Blocks.SNOW_BLOCK.getDefaultState(), 3);
                }
            }

            this.pos = blockPos3;
            return bl;
        }
    }
}
