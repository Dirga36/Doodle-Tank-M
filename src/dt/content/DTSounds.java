package dt.content;

import arc.Core;
import arc.assets.AssetDescriptor;
import arc.assets.loaders.SoundLoader;
import arc.audio.Sound;
import mindustry.Vars;

/**
 * Contains all custom sound effects used in the Doodle Tank mod.
 * --
 * Sounds are loaded from the {@code assets/sounds/} directory and must be loaded
 * before any content (weapons, units) that references them. All sounds are safely
 * loaded with headless server checks to prevent crashes in dedicated server environments.
 * --
 * Critical: This class must be loaded first in {@link dt.DoodleTankMod#loadContent()}
 * so that sound references exist when weapons are initialized.
 */
public class DTSounds {
    
    /** Large cannon firing sound - deep, powerful boom */
    public static Sound largeCannon = new Sound();
    
    /** Medium cannon firing sound - moderate boom */
    public static Sound mediumCannon = new Sound();
    
    /** Dull explosion sound - muffled impact effect */
    public static Sound dullExplosion = new Sound();
    
    /** Pew sound - light weapon fire */
    public static Sound pew = new Sound();
    
    /** Shoot snap sound - quick firing sound */
    public static Sound shootSnap = new Sound();
    
    /**
     * Loads a sound file from the assets directory.
     * 
     * Attempts to load an .ogg file first, falling back to .mp3 if not found.
     * Includes error handling and headless server safety checks.
     * 
     * @param soundName the name of the sound file (without extension)
     * @return a loaded Sound object, or an empty Sound if running headless
     */
    private static Sound loadSound(String soundName){
        // Skip sound loading on headless servers (no audio support)
        if(!Vars.headless) {
            String name = "sounds/" + soundName;
            // Try .ogg first (preferred), fallback to .mp3
            String path = Vars.tree.get(name + ".ogg").exists() ? name + ".ogg" : name + ".mp3";

            Sound sound = new Sound();

            // Load the sound asset asynchronously
            AssetDescriptor<?> desc = Core.assets.load(path, Sound.class, new SoundLoader.SoundParameter(sound));
            // Print any loading errors to console
            desc.errored = Throwable::printStackTrace;

            return sound;

        } else {
            // Return empty sound for headless servers
            return new Sound();
        }
    }

    /**
     * Loads all custom sound effects.
     * 
     * Sound files are expected to be in {@code assets/sounds/} with exact name matching.
     * For example, "large-cannon" loads "sounds/large-cannon.ogg" or "sounds/large-cannon.mp3".
     */
    public static void load(){
        largeCannon = loadSound("large-cannon");
        mediumCannon = loadSound("medium-cannon");
        dullExplosion = loadSound("dull-explosion");
        pew = loadSound("pew");
        shootSnap = loadSound("shoot-snap");
    }
}
