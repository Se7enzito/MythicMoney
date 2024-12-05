package me.money.commands.subcommands;

import java.util.List;

import org.bukkit.entity.Player;

import me.money.APIs.SubCommand;

public class RemoverCMD extends SubCommand {

	@Override
	public String getName() {
		return "remover";
	}

	@Override
	public String getDescription() {
		return "Remove money de um player";
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
