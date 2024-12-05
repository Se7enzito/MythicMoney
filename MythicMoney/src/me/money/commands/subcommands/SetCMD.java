package me.money.commands.subcommands;

import java.util.List;

import org.bukkit.entity.Player;

import me.money.APIs.SubCommand;

public class SetCMD extends SubCommand {

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
		
	}

	@Override
	public List<String> getSubcommandArguments(Player player, String[] args) {
		return null;
	}

}
