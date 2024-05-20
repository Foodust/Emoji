package org.emoji.Event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.emoji.module.inventory.EmojiModule;

public class InventoryEvent implements Listener {
    private final EmojiModule emojiModule = new EmojiModule();
    @EventHandler
    public void clickInventory(InventoryClickEvent event){
        emojiModule.usingEmoji(event);
    }
}
