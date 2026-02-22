package dt;

import dt.content.DTBlocks;
import dt.content.DTItems;
import dt.content.DTSounds;
import dt.content.DTUnitTypes;
import mindustry.mod.Mod;

/**
 * Main entry point for the Doodle Tank mod.
 * 
 * This mod adds doodle-style tanks with custom items, units, and factories to Mindustry.
 * All content is loaded in a specific order to ensure proper dependency resolution:
 * 
 *   Sounds - must load first so weapons can reference them
 *   Items - custom items used in factory requirements
 *   Units - tank units that reference sounds and items
 *   Blocks - factories that create units and consume items
 *
 * 
 * @author Dirge
 * @version 1.0
 */
public class DoodleTankMod extends Mod {
 
    /**
     * Loads all mod content in the correct dependency order.
     * 
     * Critical: The load order must not be changed:
     * 
     *   Sounds load first so weapon definitions can reference them
     *   Items load before units/blocks that consume them
     *   Units load before blocks to avoid null references in UnitFactory.plans
     * 
     */
    @Override
    public void loadContent() {
        // Load sounds first - weapons will reference these
        DTSounds.load();
        
        // Load items - units and blocks will need these
        DTItems.load();
        
        // Load units - blocks will reference these in build plans
        DTUnitTypes.load();
        
        // Load blocks last - they depend on units and items
        DTBlocks.load();
    }

}
