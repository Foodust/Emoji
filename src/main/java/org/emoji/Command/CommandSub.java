package org.emoji.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import org.emoji.Enum.Message.BaseMessage;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class CommandSub implements TabCompleter {
    Set<String> mainSub = EnumSet.range(BaseMessage.COMMAND_EMOJI, BaseMessage.COMMAND_EMOJI).stream().map(BaseMessage::getMessage).collect(Collectors.toSet());
    Set<String> subSub = EnumSet.range(BaseMessage.COMMAND_RELOAD, BaseMessage.COMMAND_RELOAD).stream().map(BaseMessage::getMessage).collect(Collectors.toSet());

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender,@NotNull Command command,@NotNull String label, String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            StringUtil.copyPartialMatches(args[0], mainSub, completions);
        } else if (args.length == 2) {
            if (Objects.requireNonNull(BaseMessage.getByMessage(args[0])) == BaseMessage.COMMAND_EMOJI) {
                StringUtil.copyPartialMatches(args[1], subSub, completions);
            }
        }
        Collections.sort(completions);
        return completions;
    }
}
