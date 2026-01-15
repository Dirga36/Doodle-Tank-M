package doodle;

import doodle.content.*;
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
