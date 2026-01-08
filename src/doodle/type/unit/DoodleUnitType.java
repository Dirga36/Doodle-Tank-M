package doodle.type.unit;

import mindustry.content.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.ammo.*;
import mindustry.world.meta.*;

public class DoodleUnitType extends UnitType{

    public DoodleUnitType(String name){
        super(name);

        outlineColor = Pal.darkOutline;
        envDisabled = Env.space;
        ammoType = new ItemAmmoType(Items.beryllium);
        researchCostMultiplier = 10f;

        squareShape = true;
        omniMovement = false;
        rotateMoveFirst = true;
        rotateSpeed = 1.3f;
        envDisabled = Env.none;
        speed = 0.8f;
    }

}
