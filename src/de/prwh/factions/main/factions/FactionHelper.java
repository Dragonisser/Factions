package de.prwh.factions.main.factions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;

import com.google.gson.JsonArray;

import de.prwh.factions.main.FactionsMain;
import de.prwh.factions.main.factions.FactionPlayer.FactionPlayerType;

public class FactionHelper {

	private static FactionHelper instance;
	private ArrayList<Faction> factionArray = new ArrayList<Faction>();
	private ArrayList<Faction> factionRemoval = new ArrayList<Faction>();
	private static File file;

	public static FactionHelper getInstance() {
		if (instance == null)
			instance = new FactionHelper();
		return instance;
	}

	public Faction getFactionByName(String name) {
		for(Faction f : factionArray) {
			if(f.getFactionName().equalsIgnoreCase(name)) {
				return f;
			}
		}
		return null;
	}
	
	public void changeFactionOwner(UUID uuid, Faction faction) {
		UUID oldOwnerUUID = faction.getFactionOwnerUUID();
		faction.setOwner(uuid);
		faction.changeMemberRole(uuid, FactionPlayerType.OWNER);
		faction.changeMemberRole(oldOwnerUUID, FactionPlayerType.MEMBER);
	}
	
	public boolean createNewFaction(String name, UUID ownerUUID) {
		Faction faction = new Faction(name, ownerUUID);
		if (factionArray.stream().anyMatch(o -> o.getFactionOwnerUUID().equals(ownerUUID))) {
			System.out.println("Faction with current player as owner already exist");
			return false;
		} else {
			if(factionArray.stream().anyMatch(o -> o.getFactionName().equals(name))) {
				System.out.println("Faction with the supplied name already exist");
				return false;
			} else {
				factionArray.add(faction);
				return true;
			}
		}
	}
	
	public boolean joinFaction(String name, UUID playerUUID) {
		Faction faction = getFactionByName(name);
		if (factionArray.stream().anyMatch(o -> o.getFactionOwnerUUID().equals(playerUUID))) {
			System.out.println("Faction with current player as owner already exist");
			return false;
		} else {
			faction.addMember(playerUUID);
			return true;
		}
	}
	
	public boolean leaveFaction(UUID playerUUID) {	
		for(Faction faction : getFactionlist()) {
			faction.getMembers().remove(playerUUID);
			if (faction.checkIfFactionIsForRemoval()) {
				factionRemoval.add(faction);
				return true;
			}
		}
		return false;
	}
	
	public void cleanUpFactionList() {
		System.out.println("Trying to remove faction");
		for(Faction faction : factionRemoval) {
			System.out.println(faction.getFactionName());
			removeFaction(faction.getFactionName());
		}
		factionRemoval.clear();
	}
	
	public ArrayList<Faction> getFactionlist() {
		return this.factionArray;
	}
	
	public void removeOwnedFaction(UUID ownerUUID) {
		factionArray.removeIf(f -> f.getFactionOwnerUUID().equals(ownerUUID));
	}
	
	public void removeFaction(String name) {
		System.out.println("Removing " + name);
		factionArray.removeIf(f -> f.getFactionName().equals(name));
	}
	
	public boolean saveFactionList() {
		FactionsMain.sendToConsole("Trying to save the Factionlist");

		try {
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
				oos.writeObject(factionArray);
				FactionsMain.sendToConsole("Factionlist saved successfully");
				return true;
			}
		} catch (IOException e) {
			FactionsMain.getLoggerMain().log(Level.SEVERE, "[Factions] Could not save Factionlist to file", e);
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public boolean loadFactionList() {
		FactionsMain.sendToConsole("Trying to load the Factionlist");
		if (file == null || !file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			factionArray = (ArrayList<Faction>) ois.readObject();
			FactionsMain.sendToConsole("Factionlist loaded successfully");
			FactionsMain.sendToConsole("Loaded " + factionArray.size() + (factionArray.size() == 1 ? " faction" : " factions"));
			return true;
		} catch (IOException | ClassNotFoundException | ClassCastException e) {
			FactionsMain.getLoggerMain().log(Level.SEVERE, "[Factions] Could not load Factionlist from file", e);
		}
		return false;
	}
	
	public void setFilePath(String path) {
		try {
			file = new File(path + "/factionlist.dat");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//TODO Maybe use it? Probably bad performance so pointless.
	public String toJson() {
		
		JsonArray factArr = new JsonArray();
//		for(Faction f : factionArray) {
//			JsonObject factionObj = new JsonObject();
//			factionObj.addProperty("name", f.getFactionName());
//			factionObj.addProperty("ownerUUID", f.getFactionOwnerUUID().toString());
//			
//			
//			JsonArray playArr = new JsonArray();
//			for(FactionPlayer fp : f.getMembers()) {
//				JsonObject playerObj = new JsonObject();	
//				
//				playerObj.addProperty("uuid", fp.getPlayerUUID().toString());
//				playerObj.addProperty("factionPlayerType", fp.getFactionPlayerType().getType());
//				
//				playArr.add(playerObj);
//			}
//			factionObj.addProperty("factionPlayers", playArr.toString());
//			
//			factArr.add(factionObj);	
//		}
		
		return factArr.toString();
	}
}
