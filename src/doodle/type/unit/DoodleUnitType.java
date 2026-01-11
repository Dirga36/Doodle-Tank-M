package doodle.type.unit;

import mindustry.content.Items;
import mindustry.graphics.Pal;
import mindustry.type.UnitType;
import mindustry.type.ammo.ItemAmmoType;
import mindustry.world.meta.Env;

public class DoodleUnitType extends UnitType {

    public DoodleUnitType(String name) {
        super(name);

        outlineColor = Pal.darkOutline;
        envDisabled = Env.space;
        ammoType = new ItemAmmoType(Items.beryllium);
        researchCostMultiplier = 10f;

        squareShape = false;
        omniMovement = false;
        rotateMoveFirst = true;
        rotateSpeed = 1.3f;
        envDisabled = Env.none;
        speed = 0.8f;
    }

}
