package me.money.commands.subcommands;

import java.util.List;

import org.bukkit.entity.Player;

import me.money.APIs.SubCommand;

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
		
	}

	@Override
	public List<String> getSubcommandArguments(Player player, String[] args) {
		return null;
	}

}
