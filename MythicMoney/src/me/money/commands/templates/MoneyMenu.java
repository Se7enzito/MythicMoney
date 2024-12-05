package me.money.commands.templates;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.money.APIs.HeadsAPI;
import me.money.APIs.money.MoneyAPI;

public class MoneyMenu implements Listener {

	public void abrirMenu(Player player) {
		Inventory inv = Bukkit.createInventory(null, 3 * 9, ChatColor.GRAY + "Menu de Money");
		
		inv.setItem(13, perfilPlayer(player));

		player.openInventory(inv);
	}
	
	private ItemStack perfilPlayer(Player player) {
		String nome = player.getName();
		String uuidString = player.getUniqueId().toString();
		
		double quantidade = MoneyAPI.verMoney(uuidString);
		
		ItemStack item = HeadsAPI.getPlayerSkull(nome);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(ChatColor.YELLOW + "Perfil de " + nome);
		meta.setLore(Arrays.asList(ChatColor.GRAY + "Money: " + ChatColor.GREEN + quantidade));
		
		item.setItemMeta(meta);
		
		return item;
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (!(e.getWhoClicked() instanceof Player) || e.getView().getTitle() == null || e.getClickedInventory() == null
				|| e.getCurrentItem() == null) {
			return;
		}

		String title = e.getView().getTitle();
		
		if (title.equalsIgnoreCase(ChatColor.GRAY + "Menu de Money")) {
			e.setCancelled(true);
		}
	}

}
