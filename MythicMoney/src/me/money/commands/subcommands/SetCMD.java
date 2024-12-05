package me.money.commands.subcommands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.money.APIs.SubCommand;
import me.money.APIs.money.MoneyAPI;

public class SetCMD extends SubCommand {
	
	// Comando admin

	@Override
	public String getName() {
		return "set";
	}

	@Override
	public String getDescription() {
		return "Seta uma quantidade de money a um player.";
	}

	@Override
	public String getSyntax() {
		return "/money remover <player> <quantidade>";
	}

	@Override
	public void perform(Player player, String[] args) {
		if (!(player.hasPermission("money.admin"))) {
			player.sendMessage(ChatColor.RED + "Sem permissão para utilizar este comando!");
			return;
		}
		
		if (args.length != 3) {
			player.sendMessage(ChatColor.RED + "A sintax do comando está incorreta!");
			return;
		}
		
		Player target = Bukkit.getPlayer(args[1]);

		if (target == null || !target.isOnline()) {
			player.sendMessage(ChatColor.RED + "O jogador é inválido ou está offline.");
			return;
		}

		String quantidadeString = args[2];
		double quantidade;

		try {
			quantidade = Double.parseDouble(quantidadeString);

			if (quantidade <= 0) {
				player.sendMessage(ChatColor.RED + "A quantidade deve ser um número positivo.");
				return;
			}
		} catch (NumberFormatException e) {
			player.sendMessage(ChatColor.RED + "A quantidade deve ser um número válido.");
			return;
		}
		
		MoneyAPI.setarMoney(player.getUniqueId().toString(), quantidade);
		player.sendMessage(ChatColor.GREEN + "Foi setado a quantidade " + quantidade + " de coins na conta de " + target.getName() + ".");
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
