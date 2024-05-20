package org.emoji.module.BaseModule;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.emoji.Emoji;
import org.emoji.Enum.CSN;
import org.emoji.Enum.Message.BaseMessage;
import org.emoji.data.EmojiData;
import org.emoji.data.info.EmojiInfo;
import org.emoji.module.inventory.InventoryModule;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ConfigModule {
    public String emojiConfig = "emoji.yml";
    public final String errorName = "error";
    private final InventoryModule inventoryModule = new InventoryModule();
    private final ItemModule itemModule = new ItemModule();

    public FileConfiguration getConfig(String fileName) {
        File configFile = new File(Emoji.getPlugin().getDataFolder(), fileName);
        if (!configFile.exists()) {
            // 파일이 존재하지 않으면 기본 설정을 생성
            Emoji.getPlugin().saveResource(fileName, false);
        }
        return YamlConfiguration.loadConfiguration(configFile);
    }

    public void saveConfig(FileConfiguration config, String fileName) {
        File configFile = new File(Emoji.getPlugin().getDataFolder(), fileName);
        try {
            config.save(configFile);
        } catch (IOException e) {
            Bukkit.getLogger().info(e.getMessage());
        }
    }

    public void getEmojiConfig() {
        EmojiData.release();

        FileConfiguration config = getConfig(emojiConfig);

        EmojiData.emojiInventoryName = config.getString(CSN.INVENTORY.getLower(), BaseMessage.ERROR.getMessage());
        EmojiData.emojiInventorySize = config.getInt(CSN.INVENTORY.getLower() + "." + CSN.SIZE.getLower(), 9);
        EmojiData.emojiInventory = inventoryModule.makeInventory(null, EmojiData.emojiInventoryName, EmojiData.emojiInventorySize);

        for (String emojiNumber : Objects.requireNonNull(config.getConfigurationSection(CSN.EMOJI.getLower())).getKeys(false)) {
            String prefix = CSN.EMOJI.getLower() + "." + emojiNumber + ".";
            String emojiName = config.getString(prefix + CSN.NAME.getLower(), errorName);
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
}
