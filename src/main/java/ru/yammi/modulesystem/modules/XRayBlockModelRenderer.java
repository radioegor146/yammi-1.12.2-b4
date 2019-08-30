package ru.yammi.modulesystem.modules;

import ru.yammi.helpers.ReflectionHelper;
import ru.yammi.modulesystem.ModuleManager;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class XRayBlockModelRenderer
        extends BlockModelRenderer {

    public static List<Integer> xrayBlocks = new ArrayList();

    public XRayBlockModelRenderer(BlockColors blockColors) {
        super(blockColors);
    }

    @Override
    public boolean renderModel(IBlockAccess iBlockAccess, IBakedModel iBakedModel, IBlockState iBlockState, BlockPos blockPos, BufferBuilder bufferBuilder, boolean bl) {
        if (ModuleManager.getModule("XRay").getState()) {
            if (!this.isVisible(iBlockState.getBlock())) {
                return false;
            }
            try {
                ReflectionHelper.getLookupMethod(Block.class, MethodType.methodType(Block.class, Float.TYPE), "setLightLevel", "func_149715_a", "a").invoke(iBlockState.getBlock(), 100.0f);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            bl = false;
        }
        return super.renderModel(iBlockAccess, iBakedModel, iBlockState, blockPos, bufferBuilder, bl);
    }

    private boolean isVisible(Block block) {
        int n = Block.getIdFromBlock(block);
        return xrayBlocks.stream().anyMatch(n2 -> n2 == n);
    }
}
