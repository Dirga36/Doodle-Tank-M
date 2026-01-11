package doodle;

import doodle.content.DoodleBlocks;
import doodle.content.DoodleUnits;
import mindustry.mod.Mod;

public class DoodleTankMod extends Mod {

    @Override
    public void loadContent() {
        DoodleUnits.load();
        DoodleBlocks.load();
    }

}
