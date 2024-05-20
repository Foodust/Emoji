package org.emoji.module.BaseModule;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;
import org.emoji.Emoji;

public class TaskModule {

    public void runBukkitTask(Runnable task){
        Bukkit.getScheduler().runTask(Emoji.getPlugin(),task);
    }
    public BukkitTask runBukkitTaskLater(Runnable task, Long delay){
       return Bukkit.getScheduler().runTaskLater(Emoji.getPlugin(), task, delay);
    }
    public BukkitTask runBukkitTaskLater(Runnable task, double delay){
        return Bukkit.getScheduler().runTaskLater(Emoji.getPlugin(), task, (long)delay);
    }
    public BukkitTask runBukkitTaskLater(Runnable task, float delay){
        return Bukkit.getScheduler().runTaskLater(Emoji.getPlugin(), task, (long)delay);
    }

    public BukkitTask runBukkitTaskTimer(Runnable task, Long delay, Long tick){
        return Bukkit.getScheduler().runTaskTimer(Emoji.getPlugin(), task, delay,tick);
    }
    public void cancelBukkitTask(BukkitTask bukkitTask){
        Bukkit.getScheduler().cancelTask(bukkitTask.getTaskId());
    }
}
