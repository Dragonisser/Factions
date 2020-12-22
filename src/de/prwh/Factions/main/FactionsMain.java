package de.prwh.Factions.main;

import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class FactionsMain extends JavaPlugin {

	private CreateConfig cfg = new CreateConfig();
	public static final String PLUGINID = "ressourcetowers";
	private static final Logger log = LogManager.getLogManager().getLogger(PLUGINID.toUpperCase());
	
	
	public void onEnable() {
		cfg.create(this);
	}
	
	public void onDisable() {

	}
	
	public static Logger getLoggerMain() {
		return log;
	}
}
