package doodle.content;

import arc.graphics.Color;
import mindustry.type.Item;

public class DoodleItems {
    public static Item
            paper, pencil, eraser, ruler, pen;

    public static void load() {

        paper = new Item("paper", Color.valueOf("#ebeef5")) {{
            alwaysUnlocked = true;
            flammability = 0.7f;
        }};
        pencil = new Item("pencil", Color.valueOf("#ffffa3")) {{
            alwaysUnlocked = true;
            flammability = 0.2f;
        }};
        eraser = new Item("eraser", Color.valueOf("#ebeef5")) {{
            alwaysUnlocked = true;
        }};
        ruler = new Item("ruler", Color.valueOf("#ffd37f")) {{
            alwaysUnlocked = true;
            flammability = 0.4f;
       }};
        pen = new Item("pen", Color.valueOf("#a4b8fa")) {{
            alwaysUnlocked = true;
        }};

    }
}
