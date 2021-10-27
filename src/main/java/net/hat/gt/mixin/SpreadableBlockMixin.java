package net.hat.gt.mixin;


import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.SpreadableBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Random;

@Mixin(SpreadableBlock.class)
public class SpreadableBlockMixin {
    @Overwrite
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {

    }
}
