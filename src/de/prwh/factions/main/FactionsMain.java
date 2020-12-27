package de.prwh.factions.main;

import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import de.prwh.factions.main.commands.CommandHelper;
import de.prwh.factions.main.factions.FactionHelper;

public class FactionsMain extends JavaPlugin {

	private CreateConfig cfg = new CreateConfig();
	public static final String PLUGINID = "ressourcetowers";
	private static final Logger log = LogManager.getLogManager().getLogger(PLUGINID.toUpperCase());
	private FactionHelper factionHelper = FactionHelper.getInstance();
	
	
	public void onEnable() {
		cfg.create(this);
		factionHelper.setFilePath(getDataFolder().getAbsolutePath());
		
		getCommand("factions").setExecutor(new CommandHelper());
	}
	
	public void onDisable() {

	}
	
	public static Logger getLoggerMain() {
		return log;
	}
	
	public static void sendToConsole(String message) {
		FactionsMain.getPlugin(FactionsMain.class).getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Factions] " + ChatColor.GREEN + message);
	}
}
