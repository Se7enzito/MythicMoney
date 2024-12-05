package me.money.commands.subcommands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.money.APIs.SubCommand;
import me.money.APIs.money.MoneyAPI;

public class GiveCMD extends SubCommand {

	@Override
	public String getName() {
		return "give";
	}

	@Override
	public String getDescription() {
		return "Da parte de seu money a um player.";
	}

	@Override
	public String getSyntax() {
		return "/money give <player> <quantidade>";
	}

	@Override
	public void perform(Player player, String[] args) {
	    if (args.length != 3) {
	        player.sendMessage(ChatColor.RED + "A sintaxe do comando está incorreta!");
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

	    if (MoneyAPI.verMoney(player.getUniqueId().toString()) >= quantidade) {
	    	MoneyAPI.transferirMoney(player.getUniqueId().toString(), target.getUniqueId().toString(), quantidade);
	    	
	        player.sendMessage(ChatColor.GREEN + "Você enviou " + quantidade + " para " + target.getName() + ".");
	        target.sendMessage(ChatColor.GREEN + "Você recebeu " + quantidade + " de " + player.getName() + ".");
	    } else {
	        player.sendMessage(ChatColor.RED + "Você não tem dinheiro suficiente.");
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
