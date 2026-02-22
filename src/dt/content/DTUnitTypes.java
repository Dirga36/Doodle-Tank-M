package dt.content;

import arc.graphics.Color;
import arc.math.geom.Rect;
import dt.type.unit.DTUnitType;
import mindustry.ai.types.GroundAI;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.abilities.StatusFieldAbility;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.bullet.RailBulletType;
import mindustry.entities.effect.ExplosionEffect;
import mindustry.entities.part.RegionPart;
import mindustry.entities.pattern.ShootAlternate;
import mindustry.gen.TankUnit;
import mindustry.type.Weapon;
import mindustry.world.meta.BlockFlag;

/**
 * Contains all custom tank units used in the Doodle Tank mod.
 * --
 * Units must be loaded after items and sounds (which they reference) but before
 * blocks (which reference units in their production plans). Each unit extends
 * {@link DTUnitType} for shared tank characteristics.
 * --
 * Asset naming: Sprites use "dt-" prefix (e.g., "dt-cax.png") while code
 * uses short names ("cax") to avoid collisions with base game assets.
 */
public class DTUnitTypes {

    /**
     * CAX medium tank - rotating turret with powerful cannon and overclock aura
     */
    public static DTUnitType cax;

    /**
     * Initializes all tank units with their configurations.
     * --
     * Uses double-brace initialization pattern for clean inline configuration
     * of unit attributes, weapons, abilities, and visual effects.
     */
    public static void load() {

        // === CAX ===
        // Long-ranged tank equiped with single powerful cannon, and team buff ability
        cax = new DTUnitType("cax") {{

            alwaysUnlocked = true;

            // Unit implementation classes
            constructor = TankUnit::create;      // Tank unit entity type
            aiController = GroundAI::new;        // Ground-based AI behavior
            targetFlags = new BlockFlag[]{
                    BlockFlag.reactor,
                    BlockFlag.battery,
                    BlockFlag.factory,
                    BlockFlag.core,
                    null
            };

            // === Physical attributes ===
            hitSize = 60f;                       // Collision radius in world units
            treadPullOffset = 1;                 // Tread animation offset
            speed = 0.7f;                        // Movement speed
            health = 17000;                      // Hit points
            armor = 10f;                         // Damage reduction value
            crushDamage = 25f / 5f;              // Damage dealt when crushing units (5 per tick)
            rotateSpeed = 1f;                    // Rotation speed multiplier
            targetAir = false;                   // Cannot attack air units

            // Team buff ability - applies overclock to nearby friendly units
            abilities.add(new StatusFieldAbility(
                    StatusEffects.overclock,         // Status effect to apply
                    60f * 15,                        // Duration: 6 seconds
                    60f * 20f,                       // Reload: 6 seconds
                    150f                             // Range: 150 world units
            ));

            // Tread trail effect - defines areas where tread marks appear
            treadRects = new Rect[]{new Rect(0, 0, 140, 150)};

            // === MAIN WEAPON - Medium Cannon ===
            weapons.add(new Weapon("dt-cax-weapon") {{

                shootSound = DTSounds.mediumCannon;
                layerOffset = 0.1f;               // Draw slightly above unit
                reload = 100f;                    // Reload time in ticks (60 ticks = 1 second)
                shootY = 60f;                     // Bullet spawn distance from weapon center
                shake = 5f;                       // Screen shake intensity on fire
                recoil = 3f;                      // Visual recoil distance
                rotate = true;                    // Turret can rotate independently
                rotateSpeed = 1.2f;               // Turret rotation speed
                mirror = false;                   // Single weapon (not mirrored to both sides)
                x = 0f;                           // Centered horizontally on unit
                y = 7f;                           // Positioned forward on unit
                shadow = 50f;                     // Shadow size

                // Weapon visual parts - animated components
                parts.addAll(

                        // Barrel recoil animation
                        new RegionPart("-suspension-barrel") {{

                            progress = PartProgress.recoil;   // Animates with weapon recoil
                            mirror = false;                   // Single barrel
                            under = true;                     // Draw below weapon
                            moveY = -5f;                      // Moves back 5 units on fire

                        }}
                );

                // Bullet configuration
                bullet = new RailBulletType() {{
                    shootEffect = Fx.railShoot;
                    length = 500;
                    pointEffectSpace = 60f;
                    pierceEffect = Fx.railHit;
                    pointEffect = Fx.railTrail;
                    hitEffect = Fx.hitFuse;
                    smokeEffect = Fx.shootBig2;
                    damage = 1250;
                    pierceDamageFactor = 0.5f;
                }};

            }});

        }};

    }

}
