package org.emoji.Enum.Message;

import lombok.Getter;
import org.bukkit.ChatColor;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

@Getter
public enum BaseMessage {

    // prefix
    PREFIX(""),

    // 기본
    DEFAULT("기본"),

    // Command
    COMMAND_EMOJI("이모지"),
    COMMAND_RELOAD("리로드"),

    // Error
    ERROR("에러"),
    ERROR_COMMAND(ChatColor.DARK_RED + "잘못된 명령어입니다.");

    private final String message;

    BaseMessage(String message) {
        this.message = message;
    }

    private static final Map<String, BaseMessage> commandInfo = new HashMap<>();

    static {
        for (BaseMessage baseMessage : EnumSet.range(COMMAND_EMOJI, COMMAND_RELOAD)) {
            commandInfo.put(baseMessage.message, baseMessage);
        }
    }

    public static BaseMessage getByMessage(String message) {
        return commandInfo.getOrDefault(message, ERROR);
    }

}