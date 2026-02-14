package doodle.type.weapons;

import arc.audio.Sound;
import arc.math.Angles;
import arc.math.Mathf;
import arc.util.Time;
import arc.util.Tmp;
import doodle.content.DoodleSounds;
import mindustry.ai.types.MissileAI;
import mindustry.content.Bullets;
import mindustry.entities.Effect;
import mindustry.entities.Mover;
import mindustry.entities.bullet.BulletType;
import mindustry.entities.units.WeaponMount;
import mindustry.gen.Entityc;
import mindustry.gen.Unit;
import mindustry.type.Weapon;

/**
 * A weapon that fires a devastating strike every Xth shot.
 */
public class DevastatingWeapon extends Weapon {
    /**
     * The normal bullet type used for regular attacks
     */
    public BulletType normalBullet = Bullets.placeholder;
    /**
     * The powerful bullet type used for the devastating strike (Xth attack).
     */
    public BulletType devastatingBullet = Bullets.placeholder;
    /**
     * How many normal shots before a devastating strike. Default is 3 (so 4th shot is devastating).
     */
    public int shotsBeforeDevastating = 3;
    /**
     * Sound for normal shots. If not set, uses the weapon's shootSound.
     */
    public Sound normalShootSound = DoodleSounds.pew;
    /**
     * Sound for devastating strikes. If not set, uses the weapon's shootSound.
     */
    public Sound devastatingShootSound = DoodleSounds.pew;
    /**
     * Recoil multiplier for normal shots. Default is 1.0 (uses weapon's recoil).
     */
    public float normalRecoilMult = 1f;
    /**
     * Recoil multiplier for devastating strikes. Default is 1.0 (uses weapon's recoil).
     */
    public float devastatingRecoilMult = 1f;

    /**
     * Shake multiplier for normal shots. Default is 1.0 (uses weapon's shake).
     */
    public float normalShakeMult = 1f;
    /**
     * Shake multiplier for devastating strikes. Default is 1.0 (uses weapon's shake).
     */
    public float devastatingShakeMult = 1f;

    public DevastatingWeapon(String name) {
        super(name);
    }

    @Override
    protected void shoot(Unit unit, WeaponMount mount, float shootX, float shootY, float rotation) {
        unit.apply(shootStatus, shootStatusDuration);

        if (shoot.firstShotDelay > 0) {
            mount.charging = true;
            chargeSound.at(shootX, shootY, Mathf.random(soundPitchMin, soundPitchMax));
            //use the appropriate bullet for charge effect
            BulletType currentBullet = getCurrentBullet(mount);
            currentBullet.chargeEffect.at(shootX, shootY, rotation, currentBullet.keepVelocity || parentizeEffects ? unit : null);
        }

        shoot.shoot(mount.barrelCounter, (xOffset, yOffset, angle, delay, mover) -> {
            mount.totalShots++;

            if (delay > 0f) {
                Time.run(delay, () -> bulletWithType(unit, mount, xOffset, yOffset, angle, mover));
            } else {
                bulletWithType(unit, mount, xOffset, yOffset, angle, mover);
            }
        }, () -> mount.barrelCounter++);
    }

    /**
     * Determines which bullet type to use based on the shot counter.
     */
    protected BulletType getCurrentBullet(WeaponMount mount) {
        //Every (shotsBeforeDevastating + 1)th shot is devastating
        return (mount.totalShots % (shotsBeforeDevastating + 1)) == shotsBeforeDevastating ? devastatingBullet : normalBullet;
    }

    /**
     * Modified bullet method that uses the appropriate bullet type, sound, and recoil.
     */
    protected void bulletWithType(Unit unit, WeaponMount mount, float xOffset, float yOffset, float angleOffset, Mover mover) {
        if (!unit.isAdded()) return;

        mount.charging = false;

        //Determine which bullet to use
        BulletType currentBullet = getCurrentBullet(mount);
        boolean isDevastating = currentBullet == devastatingBullet;

        float
                xSpread = Mathf.range(xRand),
                weaponRotation = unit.rotation - 90 + (rotate ? mount.rotation : baseRotation),
                mountX = unit.x + Angles.trnsx(unit.rotation - 90, x, y),
                mountY = unit.y + Angles.trnsy(unit.rotation - 90, x, y),
                bulletX = mountX + Angles.trnsx(weaponRotation, this.shootX + xOffset + xSpread, this.shootY + yOffset),
                bulletY = mountY + Angles.trnsy(weaponRotation, this.shootX + xOffset + xSpread, this.shootY + yOffset),
                shootAngle = bulletRotation(unit, mount, bulletX, bulletY) + angleOffset,
                lifeScl = currentBullet.scaleLife ? Mathf.clamp(Mathf.dst(bulletX, bulletY, mount.aimX, mount.aimY) / currentBullet.range) : 1f,
                angle = angleOffset + shootAngle + Mathf.range(inaccuracy + currentBullet.inaccuracy);

        Entityc shooter = unit.controller() instanceof MissileAI ai ? ai.shooter : unit;
        mount.bullet = currentBullet.create(unit, shooter, unit.team, bulletX, bulletY, angle, -1f, (1f - velocityRnd) + Mathf.random(velocityRnd), lifeScl, null, mover, mount.aimX, mount.aimY);
        handleBullet(unit, mount, mount.bullet);

        if (!continuous) {
            //Use appropriate sound for this bullet type
            Sound soundToUse = isDevastating && devastatingShootSound != DoodleSounds.pew ? devastatingShootSound :
                              !isDevastating && normalShootSound != DoodleSounds.pew ? normalShootSound :
                              shootSound;
            soundToUse.at(bulletX, bulletY, Mathf.random(soundPitchMin, soundPitchMax));
        }

        ejectEffect.at(mountX, mountY, angle * Mathf.sign(this.x));
        currentBullet.shootEffect.at(bulletX, bulletY, angle, currentBullet.hitColor, unit);
        currentBullet.smokeEffect.at(bulletX, bulletY, angle, currentBullet.hitColor, unit);

        unit.vel.add(Tmp.v1.trns(shootAngle + 180f, currentBullet.recoil));
        
        //Apply appropriate recoil multiplier
        float recoilMult = isDevastating ? devastatingRecoilMult : normalRecoilMult;
        mount.recoil = recoilMult;
        
        if (recoils > 0) {
            if (mount.recoils == null) mount.recoils = new float[recoils];
            mount.recoils[mount.barrelCounter % recoils] = recoilMult;
        }

        //Apply appropriate shake multiplier
        float shakeMult = isDevastating ? devastatingShakeMult : normalShakeMult;
        Effect.shake(shake * shakeMult, shake * shakeMult, bulletX, bulletY);

        mount.heat = 1f;
    }

    @Override
    public float range() {
        //Use the maximum range between both bullet types
        return Math.max(normalBullet.range, devastatingBullet.range);
    }
}
