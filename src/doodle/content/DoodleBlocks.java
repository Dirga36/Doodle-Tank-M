package doodle.content;

import arc.struct.Seq;
import mindustry.content.Items;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.units.UnitFactory;

import static mindustry.type.ItemStack.with;

public class DoodleBlocks {
    public static Block SketchBook;

    public static void load() {

        SketchBook = new UnitFactory("sketch-book") {{
            requirements(Category.units, with(Items.sporePod, 1000, Items.graphite, 2000, Items.plastanium, 2000));
            size = 9;
            configurable = false;
            plans = Seq.with(
                    new UnitPlan(DoodleUnits.cax, 60f * 35f, with(Items.silicon, 900, Items.plastanium, 700, Items.graphite, 600, Items.sporePod, 400, Items.surgeAlloy, 300)),
                    new UnitPlan(DoodleUnits.unit103, 60f * 35f, with(Items.silicon, 900, Items.plastanium, 700, Items.graphite, 600, Items.sporePod, 400, Items.surgeAlloy, 300))
            );
            fogRadius = 3;
        }};

    }
}
