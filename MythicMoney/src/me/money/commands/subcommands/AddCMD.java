package me.money.commands.subcommands;

import java.util.List;

import org.bukkit.entity.Player;

import me.money.APIs.SubCommand;

public class AddCMD extends SubCommand {

	@Override
	public String getName() {
		return "add";
	}

	@Override
	public String getDescription() {
		return "Adiciona money a conta de um player";
	}

	@Override
	public String getSyntax() {
		return "/money add <player> <quantidade>";
	}

	@Override
	public void perform(Player player, String[] args) {
		
	}

	@Override
	public List<String> getSubcommandArguments(Player player, String[] args) {
		return null;
	}

}
