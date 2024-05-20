package org.emoji;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.emoji.Command.CommandManager;
import org.emoji.Event.EventManager;
import org.emoji.data.EmojiData;
import org.emoji.module.BaseModule.ConfigModule;

import java.util.logging.Logger;

public final class Emoji extends JavaPlugin {
    @Getter
    private static Plugin plugin;
    @Getter
    public static Logger log = Bukkit.getLogger();

    private final ConfigModule configModule =new ConfigModule();
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        CommandManager commandManager = new CommandManager(this);
        EventManager eventManager = new EventManager(this.getServer(), this);

        // emoji get
        configModule.getEmojiConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        EmojiData.release();
    }
}
