package doodle.content;

import arc.Core;
import arc.assets.AssetDescriptor;
import arc.assets.loaders.SoundLoader;
import arc.audio.Sound;
import mindustry.Vars;

public class DoodleSounds {
    public static Sound largeCannon = new Sound();
    public static Sound mediumCannon = new Sound();
    public static Sound dullExplosion = new Sound();
    public static Sound pew = new Sound();
    
    private static Sound loadSound(String soundName){
        if(!Vars.headless) {
            String name = "sounds/" + soundName;
            String path = Vars.tree.get(name + ".ogg").exists() ? name + ".ogg" : name + ".mp3";

            Sound sound = new Sound();

            AssetDescriptor<?> desc = Core.assets.load(path, Sound.class, new SoundLoader.SoundParameter(sound));
            desc.errored = Throwable::printStackTrace;

            return sound;

        } else {
            return new Sound();
        }
    }

    public static void load(){
        largeCannon = loadSound("large-cannon");
        mediumCannon = loadSound("medium-cannon");
        dullExplosion = loadSound("dull-explosion");
        pew = loadSound("pew");
    }
}
