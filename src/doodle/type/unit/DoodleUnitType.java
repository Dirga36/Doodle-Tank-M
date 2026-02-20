package doodle.type.unit;

import mindustry.content.Items;
import mindustry.graphics.Pal;
import mindustry.type.UnitType;
import mindustry.type.ammo.ItemAmmoType;
import mindustry.world.meta.Env;

/**
 * Base unit type for all doodle-style tanks in the mod.
 * --
 * Provides shared default configuration for tank units including:
 *   --
 *   Dark outline color for doodle aesthetic
 *   Graphite ammo type for consistency
 *   Tank movement behavior (square shape, rotate before moving)
 *   Increased research costs (10x multiplier)
 *   --
 *   --
 * All custom tank units should extend this class rather than the base UnitType
 * to inherit these shared properties automatically.
 */
public class DoodleUnitType extends UnitType {

    /**
     * Creates a new doodle tank unit type with default tank configuration.
     * 
     * @param name the internal name of the unit (e.g., "cax", "103")
     */
    public DoodleUnitType(String name) {
        super(name);

        // Visual style
        outlineColor = Pal.darkOutline;  // Dark outline for doodle aesthetic
        
        // Environment restrictions
        envDisabled = Env.space;  // Cannot be used in space
        
        // Ammo configuration
        ammoType = new ItemAmmoType(Items.graphite);  // Uses graphite as ammo
        
        // Tank movement characteristics
        squareShape = true;         // Square collision hitbox for tank shape
        omniMovement = false;         // Cannot strafe - must rotate to turn
        rotateMoveFirst = true;       // Rotates to face direction before moving
        envDisabled = Env.none;       // Can operate in all normal environments
    }

}
