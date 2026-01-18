package doodle.content;

import arc.Core;
import arc.graphics.Color;
import arc.math.geom.Rect;
import doodle.type.unit.DoodleUnitType;
import mindustry.ai.types.GroundAI;
import mindustry.content.Fx;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.effect.ExplosionEffect;
import mindustry.entities.part.RegionPart;
import mindustry.gen.TankUnit;
import mindustry.type.Weapon;
import mindustry.Vars;

public class DoodleUnits {
    public static DoodleUnitType cax;

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
            range = 300f;

            //tread trail effect
            treadRects = new Rect[]{new Rect(0, 0, 140, 150)};

            //main weapon
            weapons.add(new Weapon("doodle-tank-mod-cax-weapon") {{
                //shootSound = DoodleSounds.mediumCannon;
                layerOffset = 0.1f;
                reload = 100f;
                shootY = 60f;
                shake = 5f;
                recoil = 0f;
                rotate = true;
                rotateSpeed = 0.7f;
                mirror = false;
                x = 0f;
                y = 7f;
                shadow = 50f;
                cooldownTime = 110f;

                parts.addAll(
                        new RegionPart("-suspension-barrel") {{
                            progress = PartProgress.recoil;
                            mirror = false;
                            under = true;
                            moveY = -5f;
                        }}
                );

                bullet = new BasicBulletType(15f, 400f) {{
                    sprite = "missile-large";
                    width = 12f;
                    height = 20f;
                    lifetime = 500f;
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
                    //despawnSound = DoodleSounds.dullExplosion;
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

            //secondary weapon (WIP)
            weapons.add(new Weapon("doodle-tank-mod-cax-point-weapon") {{
                //shootSound = DoodleSounds.pew;
                reload = 5f;
                x = 5f;
                y = 14f;
                shootY = 5.5f;
                recoil = 0f;
                rotate = false;
                mirror = false;

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

        }};

        if (!Vars.headless) {
            var icon = Core.atlas.find("doodle-tank-mod-cax-icon");
            if (icon != null && icon.found()) {
                cax.uiIcon = icon;
                cax.fullIcon = icon;
            }
        }

    }
}
