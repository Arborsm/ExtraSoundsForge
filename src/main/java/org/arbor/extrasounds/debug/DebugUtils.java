package org.arbor.extrasounds.debug;

import org.arbor.extrasounds.mapping.SoundPackLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class DebugUtils
{
    public static final String debugVar = "extrasounds.debug";
    public static final String debugPathVar = "extrasounds.debug.path";
    public static final String noCacheVar = "extrasounds.nocache";

    public static final boolean debug = System.getProperties().containsKey(debugVar)
            && System.getProperty(debugVar).equals("true");
    public static final String debugPath = System.getProperties().containsKey(debugPathVar)
            ? System.getProperty(debugPathVar) : "debug/";
    public static final boolean noCache = System.getProperties().containsKey(noCacheVar)
            && System.getProperties().get(noCacheVar).equals("true");

    private static final Logger LOGGER = LogManager.getLogger();

    public static void init()
    {
        if (!debug) return;
        LOGGER.info("ExtraSounds: DEBUG mode enabled.");
        LOGGER.info("Debug path: " + Path.of(debugPath).toAbsolutePath());
    }

    public static void exportSoundsJson(byte[] jsonData)
    {
        if (!debug) return;
        try
        {
            Path p = Path.of(debugPath).resolve("sounds.json");
            createFile(p);
            Files.write(p, jsonData, StandardOpenOption.TRUNCATE_EXISTING);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void soundLog(SoundEvent snd)
    {
        if (!debug) return;
        LOGGER.info("Playing sound: " + snd.getLocation());
    }

    public static void effectLog(MobEffect effect, boolean add)
    {
        if (!debug) return;
        boolean positive = !effect.getCategory().equals(MobEffectCategory.HARMFUL);
        LOGGER.info(
                (positive ? "Positive" : "Negative") + " effect " + (add ? "added" : "removed") + ": " + effect.getDisplayName()
                                                                                                               .getString());
    }

    public static void genericLog(String message)
    {
        if (!debug) return;
        LOGGER.info(message);
    }

    private static void createFile(Path p)
    {
        try
        {
            if (!Files.isDirectory(Path.of(debugPath)))
                Files.createDirectory(Path.of(debugPath));
            if (!Files.exists(p))
                Files.createFile(p);
        }
        catch (IOException e)
        {
            LOGGER.error("Unable to create file: " + p);
            e.printStackTrace();
        }
    }
}
