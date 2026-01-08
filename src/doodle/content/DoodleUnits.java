package doodle.content;

import arc.graphics.Color;
import arc.math.geom.Rect;
import doodle.type.unit.DoodleUnitType;
import mindustry.ai.types.GroundAI;
import mindustry.content.Fx;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.effect.ExplosionEffect;
import mindustry.gen.TankUnit;
import mindustry.type.Weapon;

public class DoodleUnits {
    public static DoodleUnitType cax;

    public static void load() {

        cax = new DoodleUnitType("cax") {{
            constructor = TankUnit::create;
            aiController = GroundAI::new;

            hitSize = 46f;
            treadPullOffset = 1;
            speed = 0.48f;
            health = 22000;
            armor = 26f;
            crushDamage = 25f / 5f;
            rotateSpeed = 0.8f;

            float xo = 231f / 2f, yo = 231f / 2f;
            treadRects = new Rect[]{new Rect(27 - xo, 152 - yo, 56, 73), new Rect(24 - xo, 51 - 9 - yo, 29, 17), new Rect(59 - xo, 18 - 9 - yo, 39, 19)};

            weapons.add(new Weapon("doodle-tank-mod-cax-weapon") {{
                shootSound = DoodleSounds.largeCannon;
                layerOffset = 0.1f;
                reload = 100f;
                shootY = 32.5f;
                shake = 5f;
                recoil = 5f;
                rotate = true;
                rotateSpeed = 0.6f;
                mirror = false;
                x = 0f;
                y = 7f;
                shadow = 50f;
                heatColor = Color.valueOf("f9350f");
                shootWarmupSpeed = 0.06f;
                cooldownTime = 110f;
                heatColor = Color.valueOf("f9350f");
                minWarmup = 0.9f;

                bullet = new BasicBulletType(8f, 360f) {{
                    sprite = "missile-large";
                    width = 12f;
                    height = 20f;
                    lifetime = 35f;
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

            weapons.add(new Weapon("doodle-tank-mod-cax-point-weapon") {{
                reload = 5f;
                x = 3f;
                y = 5.5f;
                shootY = 5.5f;
                recoil = 0f;
                rotate = false;
                mirror = false;

                bullet = new BasicBulletType(4.5f, 25) {{
                    width = 6.5f;
                    height = 11f;
                    shootEffect = Fx.sparkShoot;
                    smokeEffect = Fx.shootBigSmoke;
                    hitColor = backColor = trailColor = Color.valueOf("feb380");
                    frontColor = Color.white;
                    trailWidth = 1.5f;
                    trailLength = 4;
                    hitEffect = despawnEffect = Fx.hitBulletColor;
                }};
            }});
        }};

    }
}
