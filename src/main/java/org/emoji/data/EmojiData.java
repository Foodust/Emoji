package org.emoji.data;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.Inventory;
import org.emoji.data.info.EmojiInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class EmojiData {
    public static Inventory emojiInventory = null;
    public static String emojiInventoryName = "";
    public static Integer emojiInventorySize = 9;
    public static HashMap<String, EmojiInfo> emojiList = new LinkedHashMap<>(); // emoji 리스트
    public static List<ItemDisplay> emojiDisplayList = new ArrayList<>();
    public static List<ArmorStand> tempStandList = new ArrayList<>();

    public static void release() {
        emojiDisplayList.forEach(itemDisplay -> {
            if (itemDisplay != null)
                itemDisplay.remove();
        });
        tempStandList.forEach(armorStand -> {
            if(armorStand != null)
                armorStand.remove();
        });
        emojiList.clear();
    }
}
