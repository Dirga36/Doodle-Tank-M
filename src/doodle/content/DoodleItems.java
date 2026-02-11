package doodle.content;

import arc.graphics.Color;
import mindustry.type.Item;

/**
 * Contains all custom items used in the Doodle Tank mod.
 * 
 * These items represent art supplies and are used as resources for constructing
 * doodle-style tanks. All items are set to {@code alwaysUnlocked = true} for
 * testing and immediate availability.
 * 
 * Naming convention: Code uses short names (e.g., "paper") while assets
 * use the "dt-" prefix (e.g., "dt-paper.png") to avoid collisions.
 */
public class DoodleItems {
    
    public static Item
    paper,
    pencil,
    eraser,
    ruler,
    pen;

    /**
     * Initializes all custom items with their properties.
     * 
     * Uses double-brace initialization pattern for clean inline configuration.
     * Each item is configured with appropriate color and flammability values.
     */
    public static void load() {

        paper = new Item("paper", Color.valueOf("#ebeef5")) {{
            alwaysUnlocked = true;
            flammability = 0.7f;  // 70% flammable
        }};
        
        pencil = new Item("pencil", Color.valueOf("#ffffa3")) {{
            alwaysUnlocked = true;
            flammability = 0.2f;  // 20% flammable
        }};
        
        eraser = new Item("eraser", Color.valueOf("#ebeef5")) {{
            alwaysUnlocked = true;
        }};
        
        ruler = new Item("ruler", Color.valueOf("#ffd37f")) {{
            alwaysUnlocked = true;
            flammability = 0.4f;  // 40% flammable
       }};
       
        pen = new Item("pen", Color.valueOf("#a4b8fa")) {{
            alwaysUnlocked = true;
        }};

    }
}
