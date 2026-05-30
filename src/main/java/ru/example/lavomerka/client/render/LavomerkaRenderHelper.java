package ru.example.lavomerka.client.render;

import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.command.ModelCommandRenderer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import ru.example.lavomerka.LavomerkaClient;

public final class LavomerkaRenderHelper {
    private static final Identifier TEXTURE = Identifier.of(LavomerkaClient.MOD_ID, "textures/entity/lavomerka.png");
    private static final LavomerkaModel MODEL = new LavomerkaModel(LavomerkaModel.getTexturedModelData().createModel());

    private LavomerkaRenderHelper() {
    }

    public static void render(PlayerEntityRenderState state, MatrixStack matrices, OrderedRenderCommandQueue queue) {
        matrices.push();
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0F - state.bodyYaw));
        matrices.scale(0.92F, 0.92F, 0.92F);

        // state.light exists in 1.21.x render states, but MAX_LIGHT keeps the lava creature bright even in caves.
        int light = LightmapTextureManager.MAX_LIGHT_COORDINATE;
        int overlay = LivingEntityRenderer.getOverlay(state, 0.0F);

        queue.submitModel(
                MODEL,
                state,
                matrices,
                RenderLayers.entityCutoutNoCull(TEXTURE),
                light,
                overlay,
                0xFFFFFFFF,
                (ModelCommandRenderer.CrumblingOverlayCommand) null
        );

        matrices.pop();
    }
}
