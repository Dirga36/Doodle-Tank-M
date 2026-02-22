package dt.type.weapons;

import mindustry.entities.units.WeaponMount;
import mindustry.gen.Unit;
import mindustry.type.Weapon;

/**
 * A weapon that synchronizes its rotation and aiming with another weapon (the primary weapon).
 * --
 * This is useful for implementing coaxial weapon systems, such as a machine gun mounted
 * alongside a main cannon that should always aim in the same direction as the primary weapon.
 * The linked weapon inherits rotation, target rotation, and aiming coordinates from the
 * primary weapon while maintaining its own shooting logic.
 * --
 * Usage pattern:
 * --
 * weapons.add(new Weapon("main-cannon") {{
 * // Main weapon configuration...
 * }});
 * --
 * weapons.add(new LinkedWeapon("coaxial-mg") {{
 * linkedIndex = 0;  // Link to first weapon (main cannon)
 * // Secondary weapon configuration...
 * }});
 *
 * @see Weapon
 */
public class LinkedWeapon extends Weapon {

    /**
     * Index of the weapon mount this weapon should follow.
     * <p>
     * Should be set to the array index of the primary weapon in the unit's weapon list.
     * Defaults to 0 (first weapon). Invalid indices are automatically clamped to 0.
     */
    public int linkedIndex = 0;

    /**
     * Creates a new unnamed linked weapon.
     */
    public LinkedWeapon() {
    }

    /**
     * Creates a new linked weapon with the specified sprite name.
     *
     * @param name the name of the weapon sprite (used to load assets)
     */
    public LinkedWeapon(String name) {
        super(name);
    }

    /**
     * Updates the weapon's rotation and aiming to follow the linked primary weapon.
     * <p>
     * Each frame, this method:
     * <p>
     * Validates and retrieves the primary weapon mount
     * Synchronizes target rotation and current rotation
     * Copies aim position (world coordinates)
     * Delegates to parent update for shooting logic
     *
     * @param unit  the unit that owns this weapon
     * @param mount the weapon mount instance for this weapon
     */
    @Override
    public void update(Unit unit, WeaponMount mount) {
        // Validate linked index and clamp to valid range
        if (linkedIndex < 0 || linkedIndex >= unit.mounts.length) linkedIndex = 0;

        // Get the primary weapon mount to follow
        WeaponMount primary = unit.mounts[linkedIndex];

        // Synchronize rotation with the primary weapon
        mount.targetRotation = primary.targetRotation;  // Where it's trying to aim
        mount.rotation = primary.rotation;              // Current actual rotation

        // Inherit aiming position from primary weapon (world coordinates)
        mount.aimX = primary.aimX;
        mount.aimY = primary.aimY;

        // Call parent update to handle shooting logic (reload, firing, etc.)
        super.update(unit, mount);
    }
}
