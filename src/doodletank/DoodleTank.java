package doodletank;

import arc.*;
import arc.util.*;

import doodletank.content.*;

import mindustry.*;
import mindustry.content.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class DoodleTank extends Mod{

    public DoodleTank(){
        Log.info("Loaded DoodleTank constructor.");

        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            //show dialog upon startup
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("frog");
                dialog.cont.add("behold").row();
                //mod sprites are prefixed with the mod name (this mod is called 'doodletank' in its config)
                dialog.cont.image(Core.atlas.find("doodletank-java-mod-frog")).pad(20f).row();
                dialog.cont.button("I see", dialog::hide).size(100f, 50f);
                dialog.show();
            });
        });
    }

    @Override
    public void loadContent(){
        Log.info("Loading some doodletank content.");

        DoodleTankUnits.load();
        DoodleTankBlocks.load();
    }

}
