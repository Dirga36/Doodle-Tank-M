package dt.content;

import arc.struct.Seq;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.units.UnitFactory;

import static mindustry.type.ItemStack.with;

/**
 * Contains all custom factory blocks used in the Doodle Tank mod.
 * --
 * Factory blocks create units and must be loaded after units are initialized to avoid
 * null references in {@link UnitFactory#plans}. Each factory defines which units it can
 * produce along with their construction requirements and time.
 * --
 * Critical: This class must be loaded last in {@link dt.DoodleTankMod#loadContent()}
 * to ensure all referenced units and items already exist.
 */
public class DTBlocks {
    
    /** 
     * Sketch Book factory - produces doodle-style tank units.
     * --
     * A large 9x9 unit factory that consumes oil and power to construct tanks.
     * Can produce the "unit4005" and such DT units.
     */
    public static Block SketchBook;

    /**
     * Initializes all factory blocks with their properties and unit production plans.
     * --
     * Uses double-brace initialization pattern for inline configuration.
     * Unit plans reference units from {@link DTUnitTypes} which must be preloaded.
     */
    public static void load() {

        // Large 9x9 factory for producing doodle tanks
        SketchBook = new UnitFactory("sketch-book") {{
            alwaysUnlocked = true;
            
            // Building requirements using static ItemStack.with() helper
            requirements(Category.units, with(
                DTItems.paper, 4000,
                Items.silicon, 3000,
                DTItems.eraser, 1200,
                DTItems.pen, 1000,
                DTItems.ruler, 1000,
                Items.plastanium, 600,
                DTItems.pencil, 600,
                Items.surgeAlloy, 800
            ));
            
            // Resource consumption
            consumeLiquid(Liquids.oil, 0.8f);    // 3 units/second oil consumption
            consumePower(10f);                 // 25 power units/second
            
            liquidCapacity = 180f;             // Can store up to 180 units of liquid

            size = 9;  // 9x9 block size
            
            // Unit production plans - defines which units can be built
            // Each plan specifies: unit type, construction time (ticks), and item costs
            plans = Seq.with(
                    // unit4005 tank - 3.5 minutes construction time
                    new UnitPlan(DTUnitTypes.unit4005, 60f * 60f * 1.5f, with(
                        Items.silicon, 500,
                        Items.plastanium, 400,
                        DTItems.pen, 300,
                        DTItems.paper, 200,
                        Items.surgeAlloy, 100
                    )),
                    // unit214 tank - 3.5 minutes construction time
                    new UnitPlan(DTUnitTypes.unit214, 60f * 60f * 1.5f, with(
                        Items.silicon, 500,
                        Items.plastanium, 400,
                        DTItems.pen, 300,
                        DTItems.paper, 200,
                        Items.surgeAlloy, 100
                    ))
            );
            
            fogRadius = 3;  // Reveals 3 tiles of fog of war around the factory
        }};

    }
}
