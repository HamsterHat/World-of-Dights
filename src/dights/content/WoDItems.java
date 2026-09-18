package dights.content;

import arc.graphics.Color;
import mindustry.type.Item;

public class WoDItems {
    public static Item zero, one, bit, byteItem;

    public static void load() {

        zero = new Item("zero", Color.valueOf("000000")) {{
            cost = 0.1f;
            hardness = 1;
        }};


        one = new Item("one", Color.valueOf("00ffff")) {{
            cost = 0.1f;
            hardness = 1;
        }};

        bit = new Item("bit", Color.valueOf("00ffff")) {{
            cost = 0.1f;
            charge = 0.1f;
        }};

        byteItem = new Item("byte", Color.valueOf("00ffff")) {{
            cost = 0.8f;
            charge = 0.8f;
        }};
    }
}
