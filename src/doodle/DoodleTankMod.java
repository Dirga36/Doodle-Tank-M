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

    public DoodleTankMod(){
        Log.info("Loaded DoodleTankMod constructor.");

        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            //show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("frog");
                dialog.cont.add("behold").row();
                //mod sprites are prefixed with the mod name (this mod is called 'doodle-tank-mod' in its config)
                dialog.cont.image(Core.atlas.find("doodle-tank-mod-frog")).pad(20f).row();
                dialog.cont.button("I see", dialog::hide).size(100f, 50f);
                dialog.show();
            });
        });
    }

    @Override
    public void loadContent(){
        Log.info("Loading some doodle content.");

        DoodleUnits.load();
        DoodleBlocks.load();
    }

}
