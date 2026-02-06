package doodle;

import doodle.content.DoodleBlocks;
import doodle.content.DoodleItems;
import doodle.content.DoodleSounds;
import doodle.content.DoodleUnits;
import mindustry.mod.Mod;

public class DoodleTankMod extends Mod {
 
    @Override
    public void loadContent() {
        DoodleSounds.load();
        DoodleItems.load();
        DoodleUnits.load();
        DoodleBlocks.load();
    }

}
