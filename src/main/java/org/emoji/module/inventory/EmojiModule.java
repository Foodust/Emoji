package org.emoji.module.inventory;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Display;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.emoji.data.EmojiData;
import org.emoji.data.TickData;
import org.emoji.data.info.EmojiInfo;
import org.emoji.module.BaseModule.DisplayModule;
import org.emoji.module.BaseModule.TaskModule;

public class EmojiModule {
    private final TaskModule taskModule = new TaskModule();
    private final DisplayModule displayModule = new DisplayModule();


    public void openEmojiInventory(Player player) {
        player.openInventory(EmojiData.emojiInventory);
    }

    public void usingEmoji(InventoryClickEvent event) {
        Player player = (Player) event.getView().getPlayer();
        if (!event.getView().getTitle().equals(EmojiData.emojiInventoryName)) {
            return;
        }
        ItemStack item = event.getCurrentItem();
        if (item == null) return;
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta == null) return;

        String itemName = itemMeta.getDisplayName();
        if (EmojiData.emojiList.containsKey(itemName)) {
            EmojiInfo emojiInfo = EmojiData.emojiList.get(itemName);
            ArmorStand temp = player.getWorld().spawn(player.getLocation(), ArmorStand.class);
            temp.setVisible(false);
            temp.setInvisible(true);
            ItemDisplay emojiDisplay = displayModule.makeItemDisplay(player, player.getLocation().add(0, 1.8, 0), emojiInfo.getEmojiItem(), 1.0, Display.Billboard.CENTER);
            temp.addPassenger(emojiDisplay);
            player.addPassenger(temp);
            EmojiData.tempStandList.add(temp);
            EmojiData.emojiDisplayList.add(emojiDisplay);
            taskModule.runBukkitTaskLater(temp::remove, TickData.longTick * 3);
            taskModule.runBukkitTaskLater(emojiDisplay::remove, TickData.longTick * 3);
        }
        event.setCancelled(true);
        event.getView().close();
    }
}
