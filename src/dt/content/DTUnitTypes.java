package dt.content;

import arc.graphics.Color;
import arc.math.geom.Rect;
import dt.type.unit.DTUnitType;
import mindustry.ai.types.GroundAI;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.abilities.StatusFieldAbility;
import mindustry.entities.bullet.ArtilleryBulletType;
import mindustry.entities.bullet.RailBulletType;
import mindustry.entities.effect.ExplosionEffect;
import mindustry.entities.part.RegionPart;
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
 * Asset naming: Sprites use "dt-" prefix (e.g., "dt-unit4007.png") while code
 * uses short names ("unit4007") to avoid collisions with base game assets.
 */
public class DTUnitTypes {

    /**
     * unit4007 long-ranged tank - with powerful cannon and overclock aura
     */
    public static DTUnitType unit4007;

    /**
     * unit214 short-ranged tank - heavy and have a large ammounts of HP
     */
    public static DTUnitType unit214;

    /**
     * Initializes all tank units with their configurations.
     * --
     * Uses double-brace initialization pattern for clean inline configuration
     * of unit attributes, weapons, abilities, and visual effects.
     */
    public static void load() {

        // === unit4007 ===
        // Long-ranged tank equiped with single powerful weapon, and team buff ability
        unit4007 = new DTUnitType("4007") {{

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
            hitSize = 18f;                       // Collision radius in world units
            treadPullOffset = 1;                 // Tread animation offset
            speed = 0.7f;                        // Movement speed
            health = 7000;                       // Hit points
            armor = 6f;                          // Damage reduction value
            crushDamage = 4f / 5f;               // Damage dealt when crushing units (5 per tick)
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
            treadRects = new Rect[]{new Rect(0, 0, 90, 100)};

            // === MAIN WEAPON - Railgun ===
            weapons.add(new Weapon("dt-4007-weapon") {{

                shootSound = DTSounds.largeCannon;
                layerOffset = 0.1f;               // Draw slightly above unit
                reload = 100f;                    // Reload time in ticks (60 ticks = 1 second)
                shootY = 55f;                     // Bullet spawn distance from weapon center
                shake = 5f;                       // Screen shake intensity on fire
                recoil = 3f;                      // Visual recoil distance
                rotate = true;                    // Turret can rotate independently
                rotateSpeed = 1.2f;               // Turret rotation speed
                mirror = false;                   // Single weapon (not mirrored to both sides)
                x = 0f;                           // Centered horizontally on unit
                y = 5f;                           // Positioned forward on unit
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
                    length = 300;
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

        // === unit214 ===
        // Heavy tank equiped with medium cannon with high AoE
        unit214 = new DTUnitType("214") {{

            alwaysUnlocked = true;

            // Unit implementation classes
            constructor = TankUnit::create;      // Tank unit entity type
            aiController = GroundAI::new;        // Ground-based AI behavior
            targetFlags = new BlockFlag[]{
                    BlockFlag.repair,
                    BlockFlag.turret,
                    null
            };

            // === Physical attributes ===
            hitSize = 22f;                       // Collision radius in world units
            treadPullOffset = 1;                 // Tread animation offset
            speed = 0.7f;                        // Movement speed
            health = 10000;                      // Hit points
            armor = 10f;                         // Damage reduction value
            crushDamage = 10f / 5f;              // Damage dealt when crushing units (5 per tick)
            rotateSpeed = 0.7f;                  // Rotation speed multiplier
            //targetAir = false;                 // Cannot attack air units

            // Tread trail effect - defines areas where tread marks appear
            treadRects = new Rect[]{new Rect(0, 0, 100, 110)};

            // === MAIN WEAPON - Medium Cannon ===
            weapons.add(new Weapon("dt-214-weapon") {{

                shootSound = DTSounds.mediumCannon;
                layerOffset = 0.1f;               // Draw slightly above unit
                reload = 60f;                     // Reload time in ticks (60 ticks = 1 second)
                shootY = 70f;                     // Bullet spawn distance from weapon center
                shake = 2.5f;                     // Screen shake intensity on fire
                recoil = 2f;                      // Visual recoil distance
                rotate = true;                    // Turret can rotate independently
                rotateSpeed = 1f;                 // Turret rotation speed
                mirror = false;                   // Single weapon (not mirrored to both sides)
                x = 0f;                           // Centered horizontally on unit
                y = 3f;                           // Positioned forward on unit
                shadow = 70f;                     // Shadow size

                // Bullet configuration
                bullet = new ArtilleryBulletType(10f, 50) {{

                    sprite = "missile-large";
                    width = 15f;
                    height = 18f;
                    lifetime = 17f;
                    hitSize = 6f;
                    knockback = 10f;              // Pushes targets back on hit

                    smokeEffect = Fx.smokeCloud;
                    pierceCap = 2;                // Can pierce 2 targets
                    pierce = true;
                    pierceBuilding = false;       // Cannot penetrate buildings
                    hitColor = backColor = trailColor = Color.valueOf("feb380");
                    frontColor = Color.white;
                    trailWidth = 4f;
                    trailLength = 9;
                    hitEffect = despawnEffect = Fx.titanExplosion;
                    despawnSound = DTSounds.dullExplosion;
                    ejectEffect = Fx.casing4;

                    shootEffect = new ExplosionEffect() {{
                        lifetime = 40f;
                        waveStroke = 4f;
                        waveColor = sparkColor = trailColor;
                        waveRad = 15f;
                        smokeSize = 5f;
                        smokes = 8;
                        smokeSizeBase = 0f;
                        smokeColor = trailColor;
                        sparks = 8;
                        sparkRad = 40f;
                        sparkLen = 4f;
                        sparkStroke = 3f;
                    }};

                    // Large splash damage - wider radius than CAX
                    splashDamage = 50f;
                    splashDamageRadius = 160f;    // Massive splash radius

                    despawnEffect = new ExplosionEffect() {{

                        lifetime = 50f;
                        waveStroke = 4f;
                        waveColor = sparkColor = trailColor;
                        waveRad = 50f;
                        smokeSize = 7f;
                        smokes = 6;
                        smokeSizeBase = 0f;
                        smokeColor = trailColor;
                        sparks = 5;
                        sparkRad = 50f;
                        sparkLen = 3f;
                        sparkStroke = 1.5f;

                    }};

                }};

            }});

        }};

    }

}
