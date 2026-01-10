package doodle;

import arc.*;
import arc.util.*;

import doodle.content.*;

import mindustry.*;
import mindustry.content.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class DoodleTankMod extends Mod{

    @Override
    public void loadContent(){
        DoodleUnits.load();
        DoodleBlocks.load();
    }

}
