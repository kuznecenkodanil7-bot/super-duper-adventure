package ru.example.lavomerka.client.render;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.util.math.MathHelper;

public final class LavomerkaModel extends EntityModel<PlayerEntityRenderState> {
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;
    private final ModelPart tail;

    public LavomerkaModel(ModelPart root) {
        super(root, RenderLayers::entityCutoutNoCull);
        this.body = root.getChild("body");
        this.head = body.getChild("head");
        this.leftArm = body.getChild("left_arm");
        this.rightArm = body.getChild("right_arm");
        this.leftLeg = body.getChild("left_leg");
        this.rightLeg = body.getChild("right_leg");
        this.tail = body.getChild("tail");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData root = modelData.getRoot();

        ModelPartData body = root.addChild("body",
                ModelPartBuilder.create()
                        .uv(0, 16).cuboid(-5.0F, -8.0F, -3.0F, 10.0F, 12.0F, 6.0F, Dilation.NONE),
                ModelTransform.origin(0.0F, 18.0F, 0.0F));

        body.addChild("head",
                ModelPartBuilder.create()
                        .uv(0, 0).cuboid(-4.0F, -7.0F, -4.0F, 8.0F, 7.0F, 8.0F, Dilation.NONE)
                        .uv(32, 0).cuboid(-2.0F, -4.0F, -6.0F, 4.0F, 3.0F, 2.0F, Dilation.NONE)
                        .uv(44, 0).cuboid(-5.0F, -7.0F, -1.0F, 1.0F, 3.0F, 2.0F, Dilation.NONE)
                        .uv(50, 0).cuboid(4.0F, -7.0F, -1.0F, 1.0F, 3.0F, 2.0F, Dilation.NONE),
                ModelTransform.origin(0.0F, -8.0F, -1.0F));

        body.addChild("right_arm",
                ModelPartBuilder.create().uv(32, 16).cuboid(-2.0F, -1.0F, -2.0F, 3.0F, 10.0F, 4.0F, Dilation.NONE),
                ModelTransform.origin(-5.0F, -6.0F, 0.0F));

        body.addChild("left_arm",
                ModelPartBuilder.create().uv(46, 16).cuboid(-1.0F, -1.0F, -2.0F, 3.0F, 10.0F, 4.0F, Dilation.NONE),
                ModelTransform.origin(5.0F, -6.0F, 0.0F));

        body.addChild("right_leg",
                ModelPartBuilder.create().uv(0, 34).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, Dilation.NONE),
                ModelTransform.origin(-2.5F, 4.0F, 0.0F));

        body.addChild("left_leg",
                ModelPartBuilder.create().uv(16, 34).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 4.0F, Dilation.NONE),
                ModelTransform.origin(2.5F, 4.0F, 0.0F));

        body.addChild("tail",
                ModelPartBuilder.create().uv(32, 34).cuboid(-1.5F, -1.0F, 0.0F, 3.0F, 3.0F, 8.0F, Dilation.NONE),
                ModelTransform.origin(0.0F, 0.0F, 3.0F));

        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(PlayerEntityRenderState state) {
        super.setAngles(state);

        this.head.yaw = state.relativeHeadYaw * MathHelper.RADIANS_PER_DEGREE;
        this.head.pitch = state.pitch * MathHelper.RADIANS_PER_DEGREE;

        float swing = state.limbSwingAnimationProgress;
        float amount = Math.min(state.limbSwingAmplitude, 1.0F);

        this.rightArm.pitch = MathHelper.cos(swing * 0.6662F + MathHelper.PI) * 1.6F * amount;
        this.leftArm.pitch = MathHelper.cos(swing * 0.6662F) * 1.6F * amount;
        this.rightLeg.pitch = MathHelper.cos(swing * 0.6662F) * 1.4F * amount;
        this.leftLeg.pitch = MathHelper.cos(swing * 0.6662F + MathHelper.PI) * 1.4F * amount;

        this.tail.yaw = MathHelper.sin(state.age * 0.15F) * 0.25F;
        this.tail.pitch = 0.35F;
    }
}
