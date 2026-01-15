package doodle.content;

import arc.audio.Sound;
import mindustry.Vars;

public class DoodleSounds {
    public static Sound largeCannon;
    public static Sound mediumCannon;
    public static Sound dullExplosion;
    public static Sound pew;

    public static void load() {
        largeCannon = Vars.tree.loadSound("doodle-tank-mod-cax-largeCannon");
        mediumCannon = Vars.tree.loadSound("doodle-tank-mod-cax-mediumCannon");
        dullExplosion = Vars.tree.loadSound("doodle-tank-mod-cax-dullExplosion");
        pew = Vars.tree.loadSound("doodle-tank-mod-cax-pew");
    }
}
