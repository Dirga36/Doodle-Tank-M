# Doodle Tank Mod � AI Agent Notes

## Overview
Mindustry Java mod adding "doodle" style tanks with custom items, units, and factories. Uses Java 17 syntax compiled to Java 8 bytecode via jabel annotation processor. Targets Mindustry v154.2 and depends on `betamindy` mod for item shop integration (resources obtained via external dependency).

## Build workflow
- **Desktop JAR**: `./gradlew jar` ? outputs `build/libs/DoodleTankMDesktop.jar`
- **Android DEX**: `./gradlew jarAndroid` ? requires `ANDROID_HOME` env var, uses d8 for desugaring, outputs `build/libs/DoodleTankMAndroid.jar`
- **Combined deployment**: `./gradlew deploy` ? bundles desktop+android into `build/libs/DoodleTankM.jar` and cleans up temp Android jar
- All JARs shade `mod.hjson` and `assets/` via [build.gradle](build.gradle#L70-L82), including sprites, sounds, and bundles

## Content architecture & load order
Entry point [src/doodle/DoodleTankMod.java](src/doodle/DoodleTankMod.java#L11-L16) loads content in critical order:
1. **DoodleSounds**: `Vars.tree.loadSound` for weapon audio hooks; loaded first so sounds exist when weapons reference them
2. **DoodleItems**: Custom items (`paper`, `pencil`, `eraser`, `ruler`, `pen`) used in factory requirements; [DoodleItems.java](src/doodle/content/DoodleItems.java#L8-L32) uses double-brace init with `alwaysUnlocked = true`
3. **DoodleUnits**: Tank units (e.g., `cax`, `unit103`) extend [DoodleUnitType](src/doodle/type/unit/DoodleUnitType.java#L10-L22) for shared defaults (dark outline, graphite ammo, tank movement configs like `squareShape`, `omniMovement = false`)
4. **DoodleBlocks**: Factory blocks like `sketch-book` with `UnitPlan` entries that reference already-loaded units from step 3

**Critical**: Units must load before blocks to avoid null references in `UnitFactory.plans`. Items must load before units/blocks that consume them.

## Custom types & patterns
- **DoodleUnitType** ([src/doodle/type/unit/DoodleUnitType.java](src/doodle/type/unit/DoodleUnitType.java)): Base class sets `outlineColor = Pal.darkOutline`, `ammoType = ItemAmmoType(Items.graphite)`, `researchCostMultiplier = 10f`, tank movement (`squareShape = true`, `rotateMoveFirst = true`)
- **LinkedWeapon** ([src/doodle/type/weapons/LinkedWeapon.java](src/doodle/type/weapons/LinkedWeapon.java)): Weapon that synchronizes rotation/aiming with a primary weapon via `linkedIndex`; updates `mount.rotation` and `mount.aimX/Y` to follow `unit.mounts[linkedIndex]` each frame (see usage pattern for coaxial machine guns)
- **Double-brace initialization**: All content (items, units, blocks) uses anonymous subclass `{{ }}` pattern for inline configuration

## Naming & asset conventions
- **Code identifiers**: Short names (`"cax"`, `"sketch-book"`, `"paper"`)
- **Asset prefixes**: All files use `dt-{name}` to avoid collisions (`dt-cax.png`, `dt-sketch-book.png`)
- **Sprite hierarchy**: Units ? `assets/sprites/units/dt-{name}.png`, `-cell`, `-preview`, `-treads` variants; weapons ? `assets/sprites/units/weapons/dt-{weapon}-{part}.png` (e.g., `dt-cax-weapon-barrel.png`)
- **Sounds**: `.ogg` files in `assets/sounds/` with exact key matching (e.g., `dt-cax-mediumCannon.ogg` loaded as `"dt-cax-mediumCannon"`)
- **Bundles**: [assets/bundles/bundle.properties](assets/bundles/bundle.properties) uses keys like `unit.dt-cax.name`, `block.dt-sketch-book.description`

## Common pitfalls
- **RegionPart naming**: Weapon part animations require sprite names with weapon suffix; e.g., weapon named `"dt-cax-weapon"` needs parts like `dt-cax-weapon-barrel.png` or recoil fails
- **Circular dependencies**: Never reference blocks during unit init; `DoodleBlocks` may import `DoodleUnits.cax` but reverse is forbidden
- **Headless safety**: When adding asset loads (textures, sounds), guard with `!Vars.headless` to avoid crashes on dedicated servers
- **Unit plan timing**: `UnitFactory.plans` must use static fields from content classes (e.g., `DoodleUnits.cax`) which only exist after `.load()` completes


## Conventional Commit keeps history clean and readable.

Format:

```type: short description```

Common types:
```feat``` - new feature
```fix``` - bug fix
```docs``` - changes documentation only
```style``` - formatting, no logic change
```refactor``` - code change that neither fixes a bug nor adds a feature
```test``` - adding or fixing tests
```chore``` - tooling, config, maintenance

Example:
```docs | add README```