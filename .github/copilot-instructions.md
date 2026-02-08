# Doodle Tank Mod – AI Agent Notes

- Mindustry Java mod adding “doodle” tanks; Java 17 syntax compiled to Java 8 via jabel. Entrypoint is [src/doodle/DoodleTankMod.java](src/doodle/DoodleTankMod.java#L38-L41) with content load order `DoodleSounds → DoodleUnits → DoodleBlocks` (sounds first for weapon hooks, units before factories).
- Builds: `./gradlew jar` (desktop, outputs build/libs/dtDesktop.jar); `./gradlew jarAndroid` (dex via d8, requires ANDROID_HOME); `./gradlew deploy` (bundles desktop+android, deletes temp Android jar). JARs include mod.hjson and assets via shading in [build.gradle](build.gradle#L47-L66).
- Content packaging: jars target Mindustry v154.2; compile uses `--release 8` despite Java 17 source (see [build.gradle](build.gradle#L16-L33)).

## Content architecture
- Units live in [src/doodle/content/DoodleUnits.java](src/doodle/content/DoodleUnits.java#L17-L139); uses double-brace init and `DoodleUnitType` base for defaults (outline color, beryllium ammo, env config) in [src/doodle/type/unit/DoodleUnitType.java](src/doodle/type/unit/DoodleUnitType.java#L8-L22).
- Blocks go in [src/doodle/content/DoodleBlocks.java](src/doodle/content/DoodleBlocks.java#L13-L23); `UnitFactory` plans must reference already-loaded units (e.g., `DoodleUnits.cax`). Maintain units→blocks ordering.
- Sounds load through `Vars.tree.loadSound` with full mod prefix names in [src/doodle/content/DoodleSounds.java](src/doodle/content/DoodleSounds.java#L6-L17); add `!Vars.headless` guards if introducing new asset loads for server/headless safety.

## Naming and assets
- Code uses short identifiers (`"cax"`, `"sketch-book"`); asset files and sound keys use full prefix `dt-{name}` to avoid collisions. Example weapon sprite prefix `dt-cax-weapon` in [DoodleUnits.java](src/doodle/content/DoodleUnits.java#L37-L114).
- Sprite layout: `assets/sprites/units/{name}.png`, `{name}-cell.png`, `{name}-preview.png`, `{name}-treads.png`; weapon parts `assets/sprites/units/weapons/{weapon-name}-{part}.png`; factory sprites in `assets/sprites/blocks/units/` with optional `-top`/`-out` layers.
- Sounds live in `assets/sounds/` as `.ogg`; ensure filenames exactly match load keys (`dt-cax-mediumCannon.ogg`, etc.).

## Patterns and pitfalls
- Keep weapon/part sprites and names aligned; `RegionPart` animations expect suffix matching weapon name (see recoil part in [DoodleUnits.java](src/doodle/content/DoodleUnits.java#L53-L60)).
- Avoid circular references: units should not depend on blocks during init; blocks may reference units already registered.
- When adding effects/sounds, mind headless servers—defer asset lookups when `Vars.headless`.
- Research costs and ammo defaults come from `DoodleUnitType`; override locally only when necessary.
