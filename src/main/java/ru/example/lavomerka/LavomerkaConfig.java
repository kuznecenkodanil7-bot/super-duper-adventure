package ru.example.lavomerka;

import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public final class LavomerkaConfig {
    private static final Path PATH = FabricLoader.getInstance().getConfigDir().resolve("lavomerka-client.properties");

    public static boolean replacePlayers = true;

    private LavomerkaConfig() {
    }

    public static void load() {
        if (!Files.exists(PATH)) {
            save();
            return;
        }

        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(PATH)) {
            props.load(in);
            replacePlayers = Boolean.parseBoolean(props.getProperty("replacePlayers", "true"));
        } catch (IOException ignored) {
            replacePlayers = true;
        }
    }

    public static void save() {
        Properties props = new Properties();
        props.setProperty("replacePlayers", Boolean.toString(replacePlayers));

        try {
            Files.createDirectories(PATH.getParent());
            try (OutputStream out = Files.newOutputStream(PATH)) {
                props.store(out, "Lavomerka client config");
            }
        } catch (IOException ignored) {
        }
    }

    public static void toggleReplacePlayers() {
        replacePlayers = !replacePlayers;
        save();
    }
}
