package doodle.content;

import mindustry.content.Items;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.units.UnitFactory;

import static mindustry.type.ItemStack.with;

public class DoodleBlocks {
    public static Block SketchBook;

    public static void load() {

        SketchBook = new UnitFactory("sketch-book") {{
            requirements(Category.units, with(Items.silicon, 200, Items.beryllium, 150));
            size = 9;
            configurable = false;
            plans.add(new UnitPlan(DoodleUnits.cax, 60f * 35f, with(Items.beryllium, 40, Items.silicon, 50)));
            researchCost = with(Items.beryllium, 200, Items.graphite, 80, Items.silicon, 80);
            fogRadius = 3;
            consumePower(2f);
        }};

    }
}
