package de.prwh.factions.main.players;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;

import de.prwh.factions.main.FactionsMain;
import de.prwh.factions.main.factions.FactionPlayer;
import de.prwh.factions.main.factions.FactionPlayer.FactionPlayerType;

public class PlayerHelper {

	private static PlayerHelper instance;
	private ArrayList<FactionPlayer> playerArray = new ArrayList<FactionPlayer>();
	private ArrayList<FactionPlayer> playerRemoval = new ArrayList<FactionPlayer>();
	private static File file;

	public static PlayerHelper getInstance() {
		if (instance == null)
			instance = new PlayerHelper();
		return instance;
	}
	
	public void cleanUpPlayerList() {
		System.out.println("Trying to remove player");
		
		//TODO Remove player that havent played for over a year
//		for(FactionPlayer factionPlayer : playerArray) {
//		}
		
		for(FactionPlayer factionPlayer : playerRemoval) {
			System.out.println(factionPlayer.getPlayer().getName());
			removePlayer(factionPlayer.getPlayerUUID());
		}
		playerRemoval.clear();
	}
	
	public void addPlayer(UUID playerUUID) {
		addPlayer(playerUUID, FactionPlayerType.MEMBER);
	}
	
	public void addPlayer(UUID playerUUID, FactionPlayerType type) {
		FactionPlayer fp = new FactionPlayer(playerUUID, type);
		playerArray.add(fp);
	}
	
	public void removePlayer(UUID playerUUID) {
		playerArray.removeIf(fp -> fp.getPlayerUUID().equals(playerUUID));
	}
	
	public FactionPlayer getFactionPlayer(UUID uuid) {
		for(FactionPlayer fp : playerArray) {
			if(fp.getPlayerUUID().equals(uuid)) {
				return fp;
			}
		}
		return null;
	}
	
	public ArrayList<FactionPlayer> getPlayerlist() {
		return this.playerArray;
	}
	
	public boolean savePlayerList() {
		FactionsMain.sendToConsole("Trying to save the Playerlist");

		try {
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
				oos.writeObject(playerArray);
				FactionsMain.sendToConsole("Playerlist saved successfully");
				return true;
			}
		} catch (IOException e) {
			FactionsMain.getLoggerMain().log(Level.SEVERE, "[Factions] Could not save Playerlist to file", e);
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public boolean loadPlayerList() {
		FactionsMain.sendToConsole("Trying to load the Playerlist");
		if (file == null || !file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			playerArray = (ArrayList<FactionPlayer>) ois.readObject();
			FactionsMain.sendToConsole("Playerlist loaded successfully");
			FactionsMain.sendToConsole("Loaded " + playerArray.size() + (playerArray.size() == 1 ? " faction player" : " faction players"));
			return true;
		} catch (IOException | ClassNotFoundException | ClassCastException e) {
			FactionsMain.getLoggerMain().log(Level.SEVERE, "[Factions] Could not load Playerlist from file", e);
		}
		return false;
	}
	
	public void setFilePath(String path) {
		try {
			file = new File(path + "/playerlist.dat");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
