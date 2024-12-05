package me.money.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.connorlinfoot.actionbarapi.ActionBarAPI;

import me.money.APIs.SubCommand;
import me.money.commands.subcommands.AddCMD;
import me.money.commands.subcommands.GiveCMD;
import me.money.commands.subcommands.RemoverCMD;
import me.money.commands.subcommands.SetCMD;
import me.money.commands.subcommands.VerCMD;
import me.money.commands.templates.MoneyMenu;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class CommandMoney implements CommandExecutor {

	private final ArrayList<SubCommand> subcommands = new ArrayList<>();
	private MoneyMenu moneyMenu = new MoneyMenu();

	public CommandMoney() {
		subcommands.add(new AddCMD());
		subcommands.add(new GiveCMD());
		subcommands.add(new RemoverCMD());
		subcommands.add(new SetCMD());
		subcommands.add(new VerCMD());
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (sender instanceof Player) {
			Player p = (Player) sender;

			if (args.length == 0) {
				moneyMenu.abrirMenu(p);
				
				ActionBarAPI.sendActionBar(p, ChatColor.GREEN + "Menu aberto com sucesso!");
			} else if (args.length > 0) {
				for (int i = 0; i < getSubCommands().size(); i++) {
					if (args[0].equalsIgnoreCase(getSubCommands().get(i).getName())) {
						getSubCommands().get(i).perform(p, args);
					}
				}
			} else {
				p.sendMessage(ChatColor.RED + "Commands");

				for (SubCommand subCommand : getSubCommands()) {
					TextComponent hoverCommand;

					hoverCommand = new TextComponent(
							String.format("%s - %s", subCommand.getSyntax(), subCommand.getDescription()));
					hoverCommand
							.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, subCommand.getSyntax()));

					if (subCommand.getSyntax().split(" ").length > 2) {
						hoverCommand.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
								subCommand.getSyntax().substring(0, subCommand.getSyntax().lastIndexOf(" "))));
					}

					p.spigot().sendMessage(hoverCommand);
				}

				p.sendMessage(" ");
				p.sendMessage(ChatColor.GOLD + "-------------------------------------------");
			}
		}

		return true;
	}

	public ArrayList<SubCommand> getSubCommands() {
		return subcommands;
	}

}
