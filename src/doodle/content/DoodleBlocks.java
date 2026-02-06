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
            requirements(Category.units, with(Items.graphite, 2000));
            size = 9;
            plans = Seq.with(
                    new UnitPlan(DoodleUnits.cax, 60f * 35f, with(Items.silicon, 900, Items.plastanium, 700, Items.graphite, 600, Items.sporePod, 400, Items.surgeAlloy, 1)),
                    new UnitPlan(DoodleUnits.unit103, 60f * 35f, with(Items.silicon, 900, Items.plastanium, 700, Items.graphite, 600, Items.sporePod, 400, Items.surgeAlloy, 2))
            );
            fogRadius = 3;
        }};

    }
}
