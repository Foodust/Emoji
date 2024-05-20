package org.emoji.data.info;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

@Getter
@Setter
@Builder
public class EmojiInfo {
    private String emojiName;
    private ItemStack emojiItem;
    private Integer emojiSlot;
}
