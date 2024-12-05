package me.money.commands.templates;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

public class MoneyMenu implements Listener {

	public void abrirMenu(Player player) {
		Inventory inv = Bukkit.createInventory(null, 5 * 9, ChatColor.GRAY + "Menu de Money");

		player.openInventory(inv);
	}

}
