package org.emoji.module.BaseModule;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.emoji.Emoji;
import org.emoji.Enum.Message.BaseMessage;

// message 모듈
// String + String 하기 귀찮아서
// 그냥 합쳤음
// 중간에 Josa 는 명사의 조사를 붙이기 위함
public class MessageModule {
    private final HangulModule hangulModule = new HangulModule();    //    private final String prefix = BaseMessage.PREFIX.getMessage();
    private final String prefix = initialize().toString();

    public Component initialize() {
        MiniMessage miniMessage = MiniMessage.miniMessage();
        return miniMessage.deserialize("<gradient:green:blue>" + BaseMessage.PREFIX.getMessage() + "</gradient>");
    }

    public void logInfo(String arg) {
        Emoji.log.info(prefix + arg);
    }

    public void logInfo(Component arg) {
        Emoji.log.info(prefix + arg);
    }

    public void logInfoNoPrefix(String arg) {
        Emoji.log.info(arg);
    }

    public void logInfoNoPrefix(Component arg) {
        Emoji.log.info(arg.toString());
    }

    public void sendPlayer(Player player, String... arg) {
        player.sendMessage(prefix + makeString(arg));
    }

    public void sendPlayer(CommandSender player, String... arg) {
        player.sendMessage(prefix + makeString(arg));
    }

    public void sendPlayer(Player player, String word, HangulModule.Josa josa, String... arg) {
        player.sendMessage(prefix + hangulModule.getJosa(word, josa) + makeString(arg));
    }

    public void sendPlayer(Player player, Component component) {
        player.sendMessage(prefix + component);
    }
    public void sendPlayerNoPrefix(Player player, String word, HangulModule.Josa josa, String... arg) {
        player.sendMessage(hangulModule.getJosa(word, josa) + makeString(arg));
    }

    public void sendPlayerNoPrefix(CommandSender player, String... arg) {
        player.sendMessage(makeString(arg));
    }

    public void sendPlayerNoPrefix(Player player, Component component) {
        player.sendMessage("" + component);
    }
    public void sendAllPlayer(String... arg) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            sendPlayer(player, prefix , makeString(arg));
        });
    }
    public void sendAllPlayerNoPrefix(String... arg) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            sendPlayerNoPrefix(player, arg);
        });
    }
    public void sendAllPlayerTitle(String main, String sub, Integer fadeIn, Integer duration, Integer fadeOut) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            sendTitle(player, main, sub, fadeIn, duration, fadeOut);
        });
    }

    public void sendAllPlayerTitle(String main, Component sub, Integer fadeIn, Integer duration, Integer fadeOut) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            sendTitle(player, main, "" + sub, fadeIn, duration, fadeOut);
        });
    }

    public void sendAllPlayerTitle(String main, Integer fadeIn, Integer duration, Integer fadeOut) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            sendTitle(player, main, fadeIn, duration, fadeOut);
        });
    }

    public void sendAllPlayerTitle(Component main, Component sub, Integer fadeIn, Integer duration, Integer fadeOut) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            sendTitle(player, "" + main, "" + sub, fadeIn, duration, fadeOut);
        });
    }

    public void sendAllPlayerTitle(Component main, String sub, Integer fadeIn, Integer duration, Integer fadeOut) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            sendTitle(player, "" + main, sub, fadeIn, duration, fadeOut);
        });
    }

    public void sendTitle(Player player, String main, String sub, Integer fadeIn, Integer duration, Integer fadeOut) {
        player.sendTitle(main, sub, fadeIn, duration, fadeOut);
    }

    public void sendTitle(Player player, Component main, String sub, Integer fadeIn, Integer duration, Integer fadeOut) {
        player.sendTitle("" + main, sub, fadeIn, duration, fadeOut);
    }

    public void sendTitle(Player player, Component main, Component sub, Integer fadeIn, Integer duration, Integer fadeOut) {
        player.sendTitle("" + main, "" + sub, fadeIn, duration, fadeOut);
    }

    public void sendTitle(Player player, String main, Integer fadeIn, Integer duration, Integer fadeOut) {
        player.sendTitle(main, null, fadeIn, duration, fadeOut);
    }

    public void sendTitle(Player player, Component main, Integer fadeIn, Integer duration, Integer fadeOut) {
        player.sendTitle("" + main, null, fadeIn, duration, fadeOut);
    }

    public void broadcastMessage(String... arg) {
        Bukkit.broadcastMessage(prefix + makeString(arg));
    }

    public void broadcastMessage(Component arg) {
        Bukkit.broadcastMessage(prefix + arg);
    }

    public void broadcastMessageNoPrefix(String... arg) {
        Bukkit.broadcastMessage(makeString(arg));
    }

    public void broadcastMessageNoPrefix(Component arg) {
        Bukkit.broadcastMessage("" + arg);
    }

    public void broadcastMessage(HangulModule.Josa josa, String... arg) {
        Bukkit.broadcastMessage(prefix + makeString(arg));
    }

    public void broadcastMessageNoPrefix(HangulModule.Josa josa, String... arg) {
        Bukkit.broadcastMessage(makeString(arg));
    }

    public void sendPlayerActionBar(Player player, String... arg) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(makeString(arg)));
    }

    public void sendPlayerActionBar(Player player, Component arg) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("" + arg));
    }

    public void clearChat(Player player) {
        for (int i = 0; i < 100; i++) {
            player.sendMessage("");
        }
    }

    public String makeString(String... arg) {
        StringBuilder temp = new StringBuilder();
        for (String string : arg) {
            temp.append(" ").append(string);
        }
        return temp.toString();
    }
}
