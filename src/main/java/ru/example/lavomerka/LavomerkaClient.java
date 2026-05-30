package ru.example.lavomerka;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import ru.example.lavomerka.client.gui.LavomerkaScreen;

public final class LavomerkaClient implements ClientModInitializer {
    public static final String MOD_ID = "lavomerka";

    private static KeyBinding openMenuKey;

    @Override
    public void onInitializeClient() {
        LavomerkaConfig.load();

        openMenuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.lavomerka.open_menu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "key.categories.lavomerka"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openMenuKey.wasPressed()) {
                MinecraftClient.getInstance().setScreen(new LavomerkaScreen(client.currentScreen));
            }
        });
    }
}
