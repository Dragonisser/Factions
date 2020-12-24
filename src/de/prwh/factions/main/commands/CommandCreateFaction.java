package de.prwh.factions.main.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.prwh.factions.main.factions.FactionHelper;

public class CommandCreateFaction implements CommandExecutor  {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		FactionHelper helper = new FactionHelper();
		Player player = (Player)sender;
		helper.createNewFaction(args[1], player.getUniqueId());
		return true;
	}
}
