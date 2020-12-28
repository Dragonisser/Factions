package de.prwh.factions.main;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class CreateConfig {

	private File config_file;
	private FileConfiguration config;

	public FileConfiguration getConfig() {
		return config;
	}

	public void reloadConfig() {
		FactionsMain.sendToConsole("Trying to reload the config");

		config = YamlConfiguration.loadConfiguration(config_file);
		if (config != null) {
			FactionsMain.sendToConsole("Config reloaded successfully");
		} else {
			FactionsMain.sendToConsole("Failed to reload Config");
		}
	}

	public void create(FactionsMain plugin) {

		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}
		config_file = new File(plugin.getDataFolder() + File.separator + "config.yml");
		if (!config_file.exists()) {
			try {
				config_file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		config = YamlConfiguration.loadConfiguration(config_file);
		config.addDefault("autoSaveTime", 10);
		config.options().copyDefaults(true);

		try {
			config.save(config_file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
