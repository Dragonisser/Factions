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

import de.prwh.factions.main.FactionsMain;

public class FactionHelper {

	private static FactionHelper instance;
	private ArrayList<Faction> factionArray = new ArrayList<Faction>();
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
	
	public Faction getFactionByOwner(UUID uuid) {
		for(Faction f : factionArray) {
			if(f.getFactionOwnerUUID().equals(uuid) ) {
				return f;
			}
		}
		return null;
	}
	
	public void createNewFaction(String name, UUID ownerUUID) {
		Faction faction = new Faction(name, ownerUUID);
		if (factionArray.stream().anyMatch(o -> o.getFactionOwnerUUID().equals(ownerUUID))) {
			System.out.println("Faction with current player as owner already exist");
		} else {
			if(factionArray.stream().anyMatch(o -> o.getFactionName().equals(name))) {
				System.out.println("Faction with the supplied name already exist");
			} else {
				factionArray.add(faction);
			}
		}
	}
	
	public ArrayList<Faction> getFactionlist() {
		return this.factionArray;
	}
	
	public void removeOwnFaction(UUID ownerUUID) {
		for(Faction f : factionArray) {
			if(f.getFactionOwnerUUID().equals(ownerUUID)) {
				factionArray.remove(f);
			}
		}
	}
	
	public void removeFaction(String name) {
		for(Faction f : factionArray) {
			if(f.getFactionName().equalsIgnoreCase(name)) {
				factionArray.remove(f);
			}
		}
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
		if (file == null || !file.exists())
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			factionArray = (ArrayList<Faction>) ois.readObject();
			String nominator = factionArray.size() == 1 ? " faction" : " factions";
			FactionsMain.sendToConsole("Factionlist loaded successfully");
			FactionsMain.sendToConsole("Loaded " + factionArray.size() + nominator);
			return true;
		} catch (IOException | ClassNotFoundException | ClassCastException e) {
			FactionsMain.getLoggerMain().log(Level.SEVERE, "[Factions] Could not load towerlist to file", e);
		}
		return false;
	}
	
	public void setFilePath(String path) {
		//TODO change to json
		try {
			file = new File(path + "/factionlist.dat");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
