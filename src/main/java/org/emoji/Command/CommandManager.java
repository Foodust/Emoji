package org.emoji.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.emoji.Emoji;
import org.emoji.Enum.Message.BaseMessage;
import org.emoji.module.BaseModule.ConfigModule;
import org.emoji.module.BaseModule.MessageModule;
import org.emoji.module.inventory.EmojiModule;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

// 커맨드 를 할 수 있게 해줍니다!
public class CommandManager implements CommandExecutor {
    private final EmojiModule emojiModule = new EmojiModule();
    private final ConfigModule configModule = new ConfigModule();
    private final MessageModule messageModule = new MessageModule();

    public CommandManager(Emoji emoji) {
        Objects.requireNonNull(emoji.getCommand(BaseMessage.COMMAND_EMOJI.getMessage())).setExecutor(this);
        Objects.requireNonNull(emoji.getCommand(BaseMessage.COMMAND_EMOJI.getMessage())).setTabCompleter(new CommandSub());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] data) {
        if (Objects.equals(label, BaseMessage.COMMAND_EMOJI.getMessage())) {
            if (data.length >= 1 && BaseMessage.getByMessage(data[0]).equals(BaseMessage.COMMAND_RELOAD)) {
                configModule.getEmojiConfig();
                messageModule.sendPlayer((Player) sender, BaseMessage.MESSAGE_RELOAD.getMessage());
            } else {
                emojiModule.openEmojiInventory((Player) sender);
            }
        }
        return true;
    }
}
