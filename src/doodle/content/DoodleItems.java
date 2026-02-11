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
    
    /** White paper item - primary building material, highly flammable */
    public static Item paper;
    
    /** Yellow pencil item - basic writing tool, slightly flammable */
    public static Item pencil;
    
    /** White eraser item - correction material, non-flammable */
    public static Item eraser;
    
    /** Orange ruler item - measuring tool, moderately flammable */
    public static Item ruler;
    
    /** Blue pen item - advanced writing tool, non-flammable */
    public static Item pen;

    /**
     * Initializes all custom items with their properties.
     * 
     * Uses double-brace initialization pattern for clean inline configuration.
     * Each item is configured with appropriate color and flammability values.
     */
    public static void load() {

        // White paper - highly flammable due to material properties
        paper = new Item("paper", Color.valueOf("#ebeef5")) {{
            alwaysUnlocked = true;
            flammability = 0.7f;  // 70% flammable - paper burns easily
        }};
        
        // Yellow pencil - contains wood, moderately flammable
        pencil = new Item("pencil", Color.valueOf("#ffffa3")) {{
            alwaysUnlocked = true;
            flammability = 0.2f;  // 20% flammable
        }};
        
        // White eraser - rubber material, non-flammable
        eraser = new Item("eraser", Color.valueOf("#ebeef5")) {{
            alwaysUnlocked = true;
        }};
        
        // Orange ruler - wood or plastic, moderately flammable
        ruler = new Item("ruler", Color.valueOf("#ffd37f")) {{
            alwaysUnlocked = true;
            flammability = 0.4f;  // 40% flammable
       }};
       
        // Blue pen - plastic body, non-flammable
        pen = new Item("pen", Color.valueOf("#a4b8fa")) {{
            alwaysUnlocked = true;
        }};

    }
}
