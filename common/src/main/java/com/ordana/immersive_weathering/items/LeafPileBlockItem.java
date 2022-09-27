package com.ordana.immersive_weathering.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;

public class LeafPileBlockItem extends BlockItem {

    public LeafPileBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        BlockHitResult blockHitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
        if (level.getBlockState(blockHitResult.getBlockPos()).getBlock() == Blocks.WATER) {
            BlockHitResult blockHitResult2 = blockHitResult.withPosition(blockHitResult.getBlockPos().above());
            InteractionResult interactionResult = super.useOn(new UseOnContext(player, hand, blockHitResult2));
            return new InteractionResultHolder<>(interactionResult, player.getItemInHand(hand));
        }
        return super.use(level, player, hand);
    }
}
