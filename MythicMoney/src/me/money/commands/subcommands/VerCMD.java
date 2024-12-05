package me.money.commands.subcommands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.money.APIs.SubCommand;
import me.money.APIs.money.MoneyAPI;

public class VerCMD extends SubCommand {

	@Override
	public String getName() {
		return "ver";
	}

	@Override
	public String getDescription() {
		return "Vê a quantidade de money que um player possui.";
	}

	@Override
	public String getSyntax() {
		return "/money ver <player>";
	}

	@Override
	public void perform(Player player, String[] args) {
		if (args.length > 2) {
			player.sendMessage(ChatColor.RED + "Existem argumentos a mais no comando!");
			return;
		}
		
		if (args.length == 2) {
			Player target = Bukkit.getPlayer(args[1]);
			
			if (target == null) {
				player.sendMessage(ChatColor.RED + "Player inválido.");
				return;
			}
			
			double quantidade = MoneyAPI.verMoney(target.getUniqueId().toString());
			
			player.sendMessage(ChatColor.GRAY + "Dinheiro de " + target.getName() + ": " + ChatColor.GREEN + quantidade);
		} else {
			double quantidade = MoneyAPI.verMoney(player.getUniqueId().toString());
			
			player.sendMessage(ChatColor.GRAY + "Seu dinheiro: " + ChatColor.GREEN + quantidade);
		}
	}

	@Override
	public List<String> getSubcommandArguments(Player player, String[] args) {
		if (args.length == 2) {
			List<String> playersList = new ArrayList<>();
			Bukkit.getServer().getOnlinePlayers().forEach(p -> playersList.add(p.getName()));
			return playersList;
		}
		
		return null;
	}

}
