# Doodle Tank Mod - AI Agent Instructions

## Project Overview
This is a **Mindustry Java mod** that adds custom tank units with a "doodle" aesthetic. Built using Gradle, targeting Java 8 bytecode while using Java 17 features (via jabel annotation processor).

## Architecture

### Content Loading Pattern
The mod follows a strict content registration flow in [src/doodle/DoodleTankMod.java](src/doodle/DoodleTankMod.java):
1. `DoodleUnits.load()` - Register units first (dependencies for blocks)
2. `DoodleBlocks.load()` - Register blocks that reference units

**Critical**: Units must be loaded before blocks because `UnitFactory.plans` references unit types.

### Package Structure
- `doodle/` - Main mod class extending `mindustry.mod.Mod`
- `doodle/content/` - Static content loaders (Blocks, Units, Sounds)
- `doodle/type/` - Custom type extensions (e.g., `DoodleUnitType`)

### Asset Naming Convention
All assets use the **mod ID prefix**: `doodle-tank-mod-{name}`
- Java code: Use unprefixed names (`"cax"`, `"sketch-book"`)
- Asset files: Include full prefix (`doodle-tank-mod-cax.png`, `doodle-tank-mod-medium-cannon.ogg`)
- Bundles: Use full dotted path (`unit.doodle-tank-mod-cax.name`)

**Example from [DoodleUnits.java](src/doodle/content/DoodleUnits.java)**:
```java
cax = new DoodleUnitType("cax") {{ // Code uses short name
    weapons.add(new Weapon("doodle-tank-mod-cax-weapon") {{ // Asset references use full prefix
```

## Asset Organization

### Sprites
- `assets/sprites/units/` - Unit sprites: `{name}.png`, `{name}-cell.png`, `{name}-preview.png`, `{name}-treads.png`
- `assets/sprites/units/weapons/` - Weapon sprites and parts: `{weapon-name}.png`, `{weapon-name}-{part}.png`
- `assets/sprites/blocks/units/` - Factory blocks: `{block}.png`, `{block}-top.png`, `{block}-out.png`

### Sounds
Located in `assets/sounds/` as `.ogg` files. Loaded via custom `DoodleSounds.loadSound()` which checks for `.ogg` or falls back to `.mp3`.

## Development Workflows

### Building
```bash
./gradlew jar              # Desktop-only build (outputs: build/libs/doodle-tank-modDesktop.jar)
./gradlew jarAndroid       # Android dexing (requires ANDROID_HOME set)
./gradlew deploy           # Combined desktop + android JAR
```

### Installing Mod for Testing
Copy `build/libs/doodle-tank-modDesktop.jar` to Mindustry's `mods/` folder (typically `%APPDATA%/Mindustry/mods/` on Windows).

### Key Gradle Features
- **Java 8 compatibility**: Uses jabel for Java 17 syntax → Java 8 bytecode
- **Release 8 target**: All compile tasks use `--release 8`
- **Dependency shading**: `jar` task includes all runtime dependencies via `zipTree()`

## Mindustry-Specific Patterns

### Custom Types
Extend Mindustry types in `doodle/type/` to set mod-wide defaults. See [DoodleUnitType.java](src/doodle/type/unit/DoodleUnitType.java):
- Sets `outlineColor`, `envDisabled`, `ammoType`, etc.
- Avoids repetition in content definitions

### Double-Brace Initialization
All content uses double-brace syntax for inline configuration:
```java
new UnitFactory("sketch-book") {{
    requirements(Category.units, with(Items.silicon, 200));
    size = 9;
    // ...
}};
```

### Unit Weapons & Parts
Weapons use `RegionPart` for animated components (e.g., recoil barrels):
```java
parts.addAll(new RegionPart("-suspension-barrel") {{
    progress = PartProgress.recoil;
    moveY = -5f; // Recoil animation
}});
```

Part sprites are suffixed to weapon name: `{weapon-name}-{part-name}.png`

## Common Pitfalls

1. **Sound loading bug**: Line 30 in [DoodleSounds.java](src/doodle/content/DoodleSounds.java) has duplicate assignment (`dullExplosion` assigned twice, should be `pew`)
2. **Asset path case sensitivity**: Ensure sprite filenames exactly match references in code
3. **Circular dependencies**: Never reference blocks in `DoodleUnits.load()` or units in `DoodleBlocks.load()` initialization (but UnitFactory plans can reference already-loaded units)
4. **Headless mode**: Always guard asset loading with `!Vars.headless` checks for server compatibility

## Version Targets
- **Mindustry**: v154.2 (bleeding-edge, see `build.gradle`)
- **Java**: Source 17, target 8 bytecode
- **Minimum game version**: 146 (see `mod.hjson`)
