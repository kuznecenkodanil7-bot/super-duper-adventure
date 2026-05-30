package ru.example.lavomerka.client.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import ru.example.lavomerka.LavomerkaConfig;

public final class LavomerkaScreen extends Screen {
    private final Screen parent;

    public LavomerkaScreen(Screen parent) {
        super(Text.translatable("screen.lavomerka.title"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int y = this.height / 2 - 20;

        this.addDrawableChild(ButtonWidget.builder(toggleText(), button -> {
            LavomerkaConfig.toggleReplacePlayers();
            button.setMessage(toggleText());
        }).dimensions(centerX - 110, y, 220, 20).build());

        this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, button -> this.close())
                .dimensions(centerX - 110, y + 28, 220, 20)
                .build());
    }

    private static Text toggleText() {
        return Text.translatable(LavomerkaConfig.replacePlayers
                ? "screen.lavomerka.enabled"
                : "screen.lavomerka.disabled");
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        super.render(context, mouseX, mouseY, deltaTicks);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 36, 0xFFFFFF);
        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("screen.lavomerka.note"), this.width / 2, this.height / 2 + 44, 0xA0A0A0);
    }

    @Override
    public void close() {
        if (this.client != null) {
            this.client.setScreen(parent);
        }
    }
}
