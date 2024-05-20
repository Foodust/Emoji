package org.emoji.data;

import org.bukkit.inventory.Inventory;
import org.emoji.data.info.EmojiInfo;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class EmojiData {
    public static Inventory emojiInventory = null;
    public static String emojiInventoryName = "";
    public static Integer emojiInventorySize = 9;
    public static HashMap<String, EmojiInfo> emojiList = new LinkedHashMap<>(); // emoji 리스트
    public static void release(){
        emojiList.clear();
    }
}
