package org.arbor.extrasounds.mapping;

import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public final class SoundGenerator {
    public final String namespace;
    public final Function<Item, SoundDefinition> itemSoundGenerator;

    private SoundGenerator(String namespace, Function<Item, SoundDefinition> itemSoundGenerator) {
        this.namespace = namespace;
        this.itemSoundGenerator = itemSoundGenerator;
    }

    /**
     * Tells the sounds of your items and/or blocks to ExtraSounds.
     *
     * @param namespace          The item namespace your mod uses.
     * @param itemSoundGenerator The instance of the {@link Function} that converts from {@link Item} to {@link SoundDefinition}.
     * @see AutoGenerator#generator
     */
    public static SoundGenerator of(@NotNull String namespace, @NotNull Function<Item, SoundDefinition> itemSoundGenerator) {
        return new SoundGenerator(namespace, itemSoundGenerator);
    }

    public static List<SoundGenerator> auto(@NotNull List<String> namespaces, @NotNull Function<Item, SoundDefinition> itemSoundGenerator) {
        List<SoundGenerator> list = new ArrayList<>();
        for (String namespace : namespaces){
            list.add(SoundGenerator.of(namespace, itemSoundGenerator));
        }
        return list;
    }
}
