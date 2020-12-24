package de.prwh.factions.main.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.prwh.factions.main.factions.FactionHelper;

public class CommandHelper implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		FactionHelper helper = FactionHelper.getInstance();
		
		if(sender instanceof Player) {
			Player player = (Player)sender;
			
			System.out.println(sender);
			System.out.println(cmd);
			System.out.println(label);
			System.out.println(args.length);
			
			if(args.length == 2 && args[0].equals("create")) {
				helper.createNewFaction(args[1], player.getUniqueId());
				return true;
			}
			if(args.length == 1 && args[0].equals("list")) {
				System.out.println(helper.getFactionlist());
				return true;
			}
			if(args.length == 2 && args[0].equals("info")) {
				System.out.println(helper.getFactionByName(args[1]));
				return true;
			}
		}
		return false;
	}

}
