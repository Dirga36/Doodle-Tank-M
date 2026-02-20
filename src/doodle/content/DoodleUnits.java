package doodle.content;

import arc.graphics.Color;
import arc.math.geom.Rect;
import doodle.type.unit.DoodleUnitType;
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

/**
 * Contains all custom tank units used in the Doodle Tank mod.
 * --
 * Units must be loaded after items and sounds (which they reference) but before
 * blocks (which reference units in their production plans). Each unit extends
 * {@link DoodleUnitType} for shared tank characteristics.
 * --
 * Asset naming: Sprites use "dt-" prefix (e.g., "dt-cax.png") while code
 * uses short names ("cax") to avoid collisions with base game assets.
 */
public class DoodleUnits {

    /**
     * CAX medium tank - rotating turret with powerful cannon and overclock aura
     */
    public static DoodleUnitType cax;

    /**
     * Unit 103 heavy tank - fixed gun with secondary weapons and anti-air capability
     */
    public static DoodleUnitType unit103;

    /**
     * Initializes all tank units with their configurations.
     * <p>
     * Uses double-brace initialization pattern for clean inline configuration
     * of unit attributes, weapons, abilities, and visual effects.
     */
    public static void load() {

        // === CAX MEDIUM TANK ===
        // Medium tank with rotating turret, single powerful cannon, and team buff ability
        cax = new DoodleUnitType("cax") {{

            alwaysUnlocked = true;

            // Unit implementation classes
            constructor = TankUnit::create;      // Tank unit entity type
            aiController = GroundAI::new;        // Ground-based AI behavior

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

                shootSound = DoodleSounds.mediumCannon;
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
                    pierceEffect = Fx.railHit;
                    pointEffect = Fx.railTrail;
                    shootEffect = Fx.instShoot;
                    hitEffect = Fx.instHit;
                    smokeEffect = Fx.smokeCloud;
                    length = 500;
                    pointEffectSpace = 60f;
                    damage = 1250;
                    pierceDamageFactor = 0.5f;
                }};

            }});

        }};

        // === UNIT 103 HEAVY TANK ===
        // Heavy tank with fixed main gun, rapid-fire secondary weapons, and anti-air capability
        unit103 = new DoodleUnitType("103") {{

            alwaysUnlocked = true;

            // Unit implementation classes
            constructor = TankUnit::create;
            aiController = GroundAI::new;

            omniMovement = true;                 // Can strafe sideways (different from CAX)

            // === Physical attributes ===
            hitSize = 65f;
            treadPullOffset = 1;
            speed = 0.5f;                        // Slower than CAX due to heavier armor
            health = 22000;                      // More health than CAX
            armor = 15f;                         // More armor than CAX
            crushDamage = 25f / 5f;
            rotateSpeed = 2f;                    // Faster rotation than CAX
            targetAir = true;                    // Can attack air units (has AA weapon)
            faceTarget = true;                   // Entire unit rotates to face target

            // Tread trail effect
            treadRects = new Rect[]{new Rect(0, 0, 140, 150)};

            // === PRIMARY WEAPON - Large Fixed Cannon ===
            weapons.add(new Weapon("dt-103-weapon") {{

                shootSound = DoodleSounds.largeCannon;
                layerOffset = 0.1f;
                reload = 75f;                     // Faster reload than CAX
                shootY = 33f;
                shake = 5f;
                recoil = 0f;                      // No visual recoil
                rotate = false;                   // Fixed gun - doesn't rotate independently
                rotateSpeed = 0f;
                mirror = false;
                x = 0f;
                y = 35f;

                bullet = new BasicBulletType(20f, 220f) {{  // Speed: 20, Damage: 220

                    sprite = "missile-large";
                    width = 15f;
                    height = 18f;
                    lifetime = 17f;
                    hitSize = 6f;
                    knockback = 10f;               // Pushes targets back on hit

                    smokeEffect = Fx.smokeCloud;
                    pierceCap = 2;                // Can pierce 2 targets
                    pierce = true;
                    pierceBuilding = false;       // Cannot penetrate buildings
                    hitColor = backColor = trailColor = Color.valueOf("feb380");
                    frontColor = Color.white;
                    trailWidth = 4f;
                    trailLength = 9;
                    hitEffect = despawnEffect = Fx.titanExplosion;
                    despawnSound = DoodleSounds.dullExplosion;
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
                    splashDamageRadius = 200f;    // Massive splash radius

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

            // === SECONDARY WEAPON - Rapid-Fire Hull Gun ===
            // Fixed forward-facing rapid fire weapon for suppression
            weapons.add(new Weapon("dt-103-weapon-1") {{

                shootSound = DoodleSounds.pew;
                layerOffset = 0.1f;
                reload = 3.5f;                    // Very fast reload
                inaccuracy = 1;                   // Slight spread
                shootY = 10f;
                shake = 0f;
                recoil = 0f;
                rotate = false;                   // Fixed forward
                rotateSpeed = 0f;
                mirror = false;
                x = -18f;                         // Offset to left side
                y = 36f;
                shootCone = 2f;
                shoot = new ShootAlternate(4f);   // Alternates left/right by 4 units

                bullet = new BasicBulletType(4.5f, 25) {{  // Speed: 4.5, Damage: 25

                    width = 6.5f;
                    height = 11f;
                    shootEffect = Fx.sparkShoot;
                    hitColor = backColor = trailColor = Color.valueOf("feb380");
                    frontColor = Color.white;
                    trailWidth = 1.5f;
                    trailLength = 4;
                    hitEffect = despawnEffect = Fx.hitBulletColor;

                }};

            }});

            // === TERTIARY WEAPON - Anti-Air Turret ===
            // Rotating rear turret for shooting down air units
            weapons.add(new Weapon("dt-103-weapon-2") {{

                shootSound = DoodleSounds.shootSnap;
                layerOffset = 0.1f;
                reload = 3f;                      // Fast fire rate
                shootY = 15f;
                shake = 0f;
                recoil = 1f;
                rotate = true;                    // Rotates independently
                rotateSpeed = 15f;                // Very fast rotation for tracking air
                mirror = false;
                x = 0f;
                y = -10f;                         // Positioned at rear of unit
                inaccuracy = 17f;                 // Spread for flak effect
                shootCone = 35f;                  // Wide firing cone
                controllable = false;             // AI-controlled only
                autoTarget = true;                // Automatically acquires targets

                // Anti-air flak bullet with splash damage
                bullet = new BasicBulletType(4.2f, 10) {{
                    lifetime = 60f;               // Long lifetime for AA coverage
                    shootEffect = Fx.shootSmall;
                    width = 6f;
                    height = 8f;
                    hitEffect = Fx.flakExplosion;
                    splashDamage = 30f * 1.5f;    // 45 splash damage
                    splashDamageRadius = 20f;     // Small explosion radius
                }};

            }});

        }};

    }

}
