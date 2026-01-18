package doodle;

import doodle.content.DoodleBlocks;
import doodle.content.DoodleSounds;
import doodle.content.DoodleUnits;
import mindustry.mod.Mod;

public class DoodleTankMod extends Mod {

    public void DoodleTank() {
        //
    }

    @Override
    public void loadContent() {
        DoodleSounds.load();
        DoodleUnits.load();
        DoodleBlocks.load();
    }

}
