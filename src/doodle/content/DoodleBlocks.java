package doodle.content;

import arc.struct.Seq;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.units.UnitFactory;

import static mindustry.type.ItemStack.with;

public class DoodleBlocks {
    public static Block SketchBook;

    public static void load() {

        SketchBook = new UnitFactory("sketch-book") {{
            alwaysUnlocked = true;
            requirements(Category.units, with(DoodleItems.paper, 4000, Items.silicon, 3000, DoodleItems.eraser, 1200, DoodleItems.pen, 1000, DoodleItems.ruler, 1000, Items.plastanium, 600, DoodleItems.pencil, 600, Items.surgeAlloy, 800));
            consumeLiquid(Liquids.oil, 3f);
            consumePower(25f);

            //constructTime = 60f * 60f * 2;
            liquidCapacity = 180f;

            size = 9;
            plans = Seq.with(
                    new UnitPlan(DoodleUnits.cax, 60f * 35f, with(Items.silicon, 900, Items.plastanium, 700, DoodleItems.pen, 600, DoodleItems.paper, 400, Items.surgeAlloy, 500)),
                    new UnitPlan(DoodleUnits.unit103, 60f * 35f, with(Items.silicon, 900, Items.plastanium, 700, DoodleItems.pen, 600, DoodleItems.paper, 400, Items.surgeAlloy, 500))
            );
            fogRadius = 3;
        }};

    }
}
