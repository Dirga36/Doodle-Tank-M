package doodle.content;

import arc.graphics.Color;
import arc.math.geom.Rect;
import doodle.type.unit.DoodleUnitType;
import mindustry.ai.types.GroundAI;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.abilities.StatusFieldAbility;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.effect.ExplosionEffect;
import mindustry.entities.part.RegionPart;
import mindustry.gen.TankUnit;
import mindustry.type.Weapon;

public class DoodleUnits {
    public static DoodleUnitType cax;
    public static DoodleUnitType unit103;

    public static void load() {

        cax = new DoodleUnitType("cax") {{

            constructor = TankUnit::create;
            aiController = GroundAI::new;

            //attributes
            hitSize = 50f;
            treadPullOffset = 1;
            speed = 0.7f;
            health = 17000;
            armor = 30f;
            crushDamage = 25f / 5f;
            rotateSpeed = 1f;
            targetAir = false;
            abilities.add(new StatusFieldAbility(StatusEffects.overclock, 60f * 6, 60f * 6f, 100f));

            //tread trail effect
            treadRects = new Rect[]{new Rect(0, 0, 140, 150)};

            //main weapon
            weapons.add(new Weapon("doodle-tank-mod-cax-weapon") {{

                shootSound = DoodleSounds.mediumCannon;
                layerOffset = 0.1f;
                reload = 100f;
                shootY = 60f;
                shake = 5f;
                recoil = 1f;
                rotate = true;
                rotateSpeed = 1.2f;
                mirror = false;
                x = 0f;
                y = 7f;
                shadow = 50f;

                parts.addAll(
                        new RegionPart("-suspension-barrel") {{
                            progress = PartProgress.recoil;
                            mirror = false;
                            under = true;
                            moveY = -5f;
                        }}
                );

                bullet = new BasicBulletType(20f, 400f) {{
                    sprite = "missile-large";
                    width = 12f;
                    height = 20f;
                    lifetime = 15f;
                    hitSize = 6f;

                    smokeEffect = Fx.shootSmokeTitan;
                    pierceCap = 3;
                    pierce = true;
                    pierceBuilding = true;
                    hitColor = backColor = trailColor = Color.valueOf("feb380");
                    frontColor = Color.white;
                    trailWidth = 4f;
                    trailLength = 9;
                    hitEffect = despawnEffect = Fx.massiveExplosion;
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

                    splashDamage = 65f;
                    splashDamageRadius = 70f;
                    despawnEffect = new ExplosionEffect() {{
                        lifetime = 50f;
                        waveStroke = 4f;
                        waveColor = sparkColor = trailColor;
                        waveRad = 30f;
                        smokeSize = 7f;
                        smokes = 6;
                        smokeSizeBase = 0f;
                        smokeColor = trailColor;
                        sparks = 5;
                        sparkRad = 30f;
                        sparkLen = 3f;
                        sparkStroke = 1.5f;
                    }};

                }};
                
            }});

        }};


        unit103 = new DoodleUnitType("103") {{

            constructor = TankUnit::create;
            aiController = GroundAI::new;

            omniMovement = true;

            //attributes
            hitSize = 50f;
            treadPullOffset = 1;
            speed = 0.8f;
            health = 18000;
            armor = 25f;
            crushDamage = 25f / 5f;
            rotateSpeed = 2f;
            targetAir = true;
            faceTarget = true;

            //tread trail effect
            treadRects = new Rect[]{new Rect(0, 0, 140, 150)};

            //main weapon
            weapons.add(new Weapon("doodle-tank-mod-103-weapon") {{

                shootSound = DoodleSounds.largeCannon;
                layerOffset = 0.1f;
                reload = 75f;
                shootY = 33f;
                shake = 5f;
                recoil = 0f;
                rotate = false;
                rotateSpeed = 0f;
                mirror = false;
                x = 0f;
                y = 35f;

                bullet = new BasicBulletType(20f, 420f) {{
                    sprite = "missile-large";
                    width = 15f;
                    height = 18f;
                    lifetime = 20f;
                    hitSize = 6f;

                    smokeEffect = Fx.shootSmokeTitan;
                    pierceCap = 2;
                    pierce = true;
                    pierceBuilding = false;
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

                    splashDamage = 50f;
                    splashDamageRadius = 185f;
                    despawnEffect = new ExplosionEffect() {{
                        lifetime = 50f;
                        waveStroke = 4f;
                        waveColor = sparkColor = trailColor;
                        waveRad = 30f;
                        smokeSize = 7f;
                        smokes = 6;
                        smokeSizeBase = 0f;
                        smokeColor = trailColor;
                        sparks = 5;
                        sparkRad = 30f;
                        sparkLen = 3f;
                        sparkStroke = 1.5f;
                    }};

                }};

            }});

            //secondary weapon
            weapons.add(new Weapon("doodle-tank-mod-103-weapon-1") {{

                shootSound = DoodleSounds.pew;
                layerOffset = 0.1f;
                reload = 5f;
                inaccuracy = 1;
                shootY = 10f;
                shake = 0f;
                recoil = 0f;
                rotate = false;
                rotateSpeed = 0f;
                mirror = false;
                x = -18f;
                y = 36f;
                shootCone = 1f;

                bullet = new BasicBulletType(4.5f, 25) {{
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

            //third weapon
            weapons.add(new Weapon("doodle-tank-mod-103-weapon-2") {{

                shootSound = DoodleSounds.shootSnap;
                layerOffset = 0.1f;
                reload = 7f;
                shootY = 15f;
                shake = 0f;
                recoil = 1f;
                rotate = true;
                rotateSpeed = 15f;
                mirror = false;
                x = 0f;
                y = -10f;
                inaccuracy = 17f;
                shootCone = 35f;
                controllable = false;
                autoTarget = true;

                bullet = new BasicBulletType(4.2f, 3) {{
                    lifetime = 60f;
                    shootEffect = Fx.shootSmall;
                    width = 6f;
                    height = 8f;
                    hitEffect = Fx.flakExplosion;
                    splashDamage = 27f * 1.5f;
                    splashDamageRadius = 15f;
                }};
            }});

        }};
    }
}
