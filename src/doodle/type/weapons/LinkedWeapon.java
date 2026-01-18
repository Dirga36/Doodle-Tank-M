package doodle.type.weapons;

import arc.math.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.type.*;

/**
 * A weapon that follows the rotation of another weapon (the primary weapon).
 * Useful for secondary weapons like machine guns mounted alongside a main cannon.
 */
public class LinkedWeapon extends Weapon{

    /** Index of the weapon this one should follow */
    public int linkedIndex = 0;

    public LinkedWeapon(){
    }

    public LinkedWeapon(String name){
        super(name);
    }

    @Override
    public void update(Unit unit, WeaponMount mount){
        // Get the primary weapon mount to follow
        if(linkedIndex < 0 || linkedIndex >= unit.mounts.length) linkedIndex = 0;
        
        WeaponMount primary = unit.mounts[linkedIndex];

        // Synchronize rotation with the primary weapon
        mount.targetRotation = primary.targetRotation;
        mount.rotation = primary.rotation;
        
        // Inherit aiming position from primary weapon
        mount.aimX = primary.aimX;
        mount.aimY = primary.aimY;

        // Call parent update to handle shooting logic
        super.update(unit, mount);
    }
}
