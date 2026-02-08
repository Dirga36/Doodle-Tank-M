package doodle.content;

import arc.graphics.Color;
import mindustry.type.Item;

public class DoodleItems {
    public static Item
            paper, pencil, eraser, ruler, pen;

    public static void load() {

        paper = new Item("paper", Color.valueOf("#ebeef5")) {
        };
        pencil = new Item("pencil", Color.valueOf("#ffffa3")) {
        };
        eraser = new Item("eraser", Color.valueOf("#ebeef5")) {
        };
        ruler = new Item("ruler", Color.valueOf("#ffd37f")) {
        };
        pen = new Item("pen", Color.valueOf("#a4b8fa")) {
        };

    }
}
