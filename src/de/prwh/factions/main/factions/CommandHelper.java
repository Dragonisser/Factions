package de.prwh.factions.main.factions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.prwh.factions.main.commands.CommandCreateFaction;

public class CommandHelper implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			//Player player = (Player)sender;
			if(args.length == 2 && args[0] == "create") {
				CommandCreateFaction command = new CommandCreateFaction();
				command.onCommand(sender, cmd, label, args);
			}
		}
		return false;
	}

}
