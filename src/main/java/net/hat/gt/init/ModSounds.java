package net.hat.gt.init;

import net.hat.gt.GobT;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModSounds {
    private static final Map<SoundEvent, Identifier> SOUND_EVENTS = new LinkedHashMap<>();
    public static final SoundEvent ANNOYED_GRUNT = create("annoyed_grunt");
    public static final SoundEvent IDLE_GRUNT = create("idle_grunt");

    private static SoundEvent create(String name) {
        Identifier id = new Identifier(GobT.MODID, name);
        SoundEvent soundEvent = new SoundEvent(id);
        SOUND_EVENTS.put(soundEvent, id);
        return soundEvent;
    }

    public static void registerSounds () {
        SOUND_EVENTS.keySet().forEach(soundEvent -> Registry.register(Registry.SOUND_EVENT, SOUND_EVENTS.get(soundEvent), soundEvent));
    }
}
