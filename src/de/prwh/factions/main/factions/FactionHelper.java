package de.prwh.factions.main.factions;

import java.util.ArrayList;
import java.util.UUID;

public class FactionHelper {

	private static FactionHelper instance;
	private ArrayList<Faction> factionArray;

	public static FactionHelper getInstance() {
		if (instance == null)
			instance = new FactionHelper();
		return instance;
	}

	public Faction getFactionByName(String name) {
		for(Faction f : factionArray) {
			if(f.getFactionName() == name) {
				return f;
			}
		}
		return null;
	}
	
	public Faction getFactionByOwner(UUID uuid) {
		for(Faction f : factionArray) {
			if(f.getFactionOwnerUUID() == uuid) {
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
			if(f.getFactionName().equals(name)) {
				factionArray.remove(f);
			}
		}
	}
}
