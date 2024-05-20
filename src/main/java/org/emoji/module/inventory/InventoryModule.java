package org.emoji.module.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.emoji.module.BaseModule.ItemModule;

public class InventoryModule {
    public static Material noneBlock = Material.BLACK_STAINED_GLASS_PANE;
    public static Material acceptBlock = Material.LIME_WOOL;
    public static Material cancelBlock = Material.RED_WOOL;
    public static Material deleteBlock = Material.REDSTONE_BLOCK;
    public static Material errorMaterial = Material.ROTTEN_FLESH;
    private final ItemModule itemModule = new ItemModule();
    private final int x = 9;
    private final int y = 6;
    private final int size = x * y;
    public Inventory makeInventory(String title, int size) {
        return Bukkit.createInventory(null, size, title);
    }
    public Inventory makeInventory(Player player, String title, int size) {
        return Bukkit.createInventory(player, size, title);
    }
}
