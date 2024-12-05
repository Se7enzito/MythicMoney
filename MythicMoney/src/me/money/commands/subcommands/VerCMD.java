package me.money.commands.subcommands;

import java.util.List;

import org.bukkit.entity.Player;

import me.money.APIs.SubCommand;

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
		return "/money ver";
	}

	@Override
	public void perform(Player player, String[] args) {
		
	}

	@Override
	public List<String> getSubcommandArguments(Player player, String[] args) {
		return null;
	}

}
