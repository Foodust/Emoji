package org.emoji.module.inventory;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Display;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.emoji.Enum.CSN;
import org.emoji.Enum.Message.BaseMessage;
import org.emoji.data.EmojiData;
import org.emoji.data.TickData;
import org.emoji.data.info.EmojiInfo;
import org.emoji.module.BaseModule.ConfigModule;
import org.emoji.module.BaseModule.DisplayModule;
import org.emoji.module.BaseModule.ItemModule;
import org.emoji.module.BaseModule.TaskModule;

import java.util.List;
import java.util.Objects;

public class EmojiModule {
    private final ItemModule itemModule = new ItemModule();
    private final TaskModule taskModule = new TaskModule();
    private final InventoryModule inventoryModule = new InventoryModule();
    private final ConfigModule configModule = new ConfigModule();
    private final DisplayModule displayModule = new DisplayModule();

    public void initializeEmojiConfig() {
        EmojiData.release();

        FileConfiguration config = configModule.getConfig(configModule.emojiConfig);

        EmojiData.emojiInventoryName = config.getString(CSN.INVENTORY.getLower(), BaseMessage.ERROR.getMessage());
        EmojiData.emojiInventorySize = config.getInt(CSN.INVENTORY.getLower() + "." + CSN.SIZE.getLower(), 9);
        EmojiData.emojiInventory = inventoryModule.makeInventory(null, EmojiData.emojiInventoryName, EmojiData.emojiInventorySize);

        for (String emojiName : Objects.requireNonNull(config.getConfigurationSection(CSN.EMOJI.getLower())).getKeys(false)) {
            String prefix = CSN.EMOJI.getLower() + "." + emojiName + ".";
            List<String> emojiDescription = config.getStringList(prefix + CSN.DESCRIPTION.getLower());
            Material emojiMaterial = Material.getMaterial(config.getString(prefix + CSN.MATERIAL.getLower(), itemModule.errorRotten));
            int emojiAmount = config.getInt(prefix + CSN.AMOUNT.getLower(), 1);
            int emojiSlot = config.getInt(prefix + CSN.SLOT.getLower(), 0);
            int emojiData = config.getInt(prefix + CSN.DATA.getLower(), 0);
            ItemStack emojiItem = itemModule.setCustomItem(emojiMaterial, emojiName, emojiDescription, emojiData, emojiAmount);
            EmojiInfo emojiInfo = EmojiInfo.builder()
                    .emojiItem(emojiItem)
                    .emojiName(emojiName)
                    .emojiSlot(emojiSlot)
                    .build();
            EmojiData.emojiList.put(emojiName, emojiInfo);
            EmojiData.emojiInventory.setItem(emojiSlot, emojiItem);
        }
    }

    public void openEmojiInventory(Player player) {
        player.openInventory(EmojiData.emojiInventory);
    }

    public void usingEmoji(InventoryClickEvent event) {
        Player player = (Player) event.getView().getPlayer();
        Inventory inventory = event.getInventory();
        if (!event.getView().getTitle().equals(EmojiData.emojiInventoryName)) {
            return;
        }
        int clickSlot = event.getSlot();
        ItemStack item = inventory.getItem(clickSlot);
        if (item == null) return;
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta == null) return;

        String itemName = itemMeta.getDisplayName();
        if (EmojiData.emojiList.containsKey(itemName)) {
            EmojiInfo emojiInfo = EmojiData.emojiList.get(itemName);
            ItemDisplay emojiDisplay = displayModule.makeItemDisplay(player, player.getLocation().add(0, 2, 0), emojiInfo.getEmojiItem(), 1.0, Display.Billboard.CENTER);
            player.addPassenger(emojiDisplay);
            taskModule.runBukkitTaskLater(emojiDisplay::remove, TickData.longTick * 3);
        }
        event.setCancelled(true);
        event.getView().close();
    }
}
