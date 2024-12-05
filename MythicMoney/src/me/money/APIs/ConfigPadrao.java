package me.money.APIs;

import org.bukkit.configuration.file.FileConfiguration;

import me.money.Main;

public class ConfigPadrao {

	private FileAPI fileAPI;
	private FileConfiguration config;
	private String folder = "";
	private Main plugin;

	public ConfigPadrao() {
		this.plugin = Main.m;
		this.fileAPI = new FileAPI(this.plugin, "config.yml", folder);
		this.config = this.fileAPI.getConfig();
	}
	
	private void saveAndReload() {
		fileAPI.saveConfig();
		fileAPI.reloadConfig();
	}
	
	public void setNome() {
		config.set("Nome", "Teste");
		
		saveAndReload();
	}
	
}
