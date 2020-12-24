package de.prwh.factions.main;

import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import de.prwh.factions.main.factions.CommandHelper;

public class FactionsMain extends JavaPlugin {

	private CreateConfig cfg = new CreateConfig();
	public static final String PLUGINID = "ressourcetowers";
	private static final Logger log = LogManager.getLogManager().getLogger(PLUGINID.toUpperCase());
	
	
	public void onEnable() {
		cfg.create(this);
		
		getCommand("factions").setExecutor(new CommandHelper());
	}
	
	public void onDisable() {

	}
	
	public static Logger getLoggerMain() {
		return log;
	}
}
