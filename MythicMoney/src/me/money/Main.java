package me.money;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import me.money.commands.CommandMoney;
import me.money.commands.templates.MoneyMenu;
import me.money.database.DatabaseSetup;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {

	public static Main m;
	private ConsoleCommandSender console = Bukkit.getConsoleSender();
	private PluginManager pm = Bukkit.getPluginManager();
	private final Logger logger = Bukkit.getLogger();
	private final long start_ms = System.currentTimeMillis();

	private static Economy economy;

	@Override
	public void onEnable() {
		console.sendMessage(ChatColor.RED + "MythicMoney - Online");

		if (!setupEconomy()) {
			getLogger().severe("Vault não foi encontrado! Desativando o plugin.");
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		}
		
		getCommand("money").setExecutor(new CommandMoney());
		
		pm.registerEvents(new MoneyMenu(), this);

		final long loadms = System.currentTimeMillis() - start_ms;
		logger.log(Level.INFO, "[MythicMoney] Loaded in %s" + loadms + "ms");

		super.onEnable();
	}

	@Override
	public void onDisable() {
		console.sendMessage(ChatColor.RED + "MythicMoney - Desligado");

		HandlerList.unregisterAll();

		super.onDisable();
	}

	@Override
	public void onLoad() {
		m = this;

		if (!getDataFolder().exists()) {
			getDataFolder().mkdirs();
		}
		
		DatabaseSetup.initializeDatabase();

		super.onLoad();
	}

	public static Main getInstance() {
		return m;
	}

	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}

		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);

		if (rsp == null) {
			return false;
		}

		economy = rsp.getProvider();
		return economy != null;
	}

	public static Economy getEconomy() {
		return economy;
	}
}
