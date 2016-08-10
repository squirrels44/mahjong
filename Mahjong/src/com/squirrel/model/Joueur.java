package com.squirrel.model;

import java.util.List;

import com.squirrel.model.TuileVen.VenTuile;

public class Joueur {
	
	String name;
	HandFacade hand;
	VenTuile vent;
	boolean appel=false;
	int penalite =0;
	List<Tuile> bonus;
	
	public void incremPenalite() {
		this.penalite ++;
	}

	public Joueur(String name, HandFacade hand,VenTuile vent, List<Tuile> bonus){
		 super();
		 this.name=name;
		 this.hand=hand;
		 this.vent=vent;
		 this.bonus=bonus;
	}
	
	public List<Tuile> getBonus() {
		return bonus;
	}

	public void setHand(HandFacade hand) {
		this.hand = hand;
	}

	public void setBonus(List<Tuile> bonus) {
		this.bonus = bonus;
	}

	public void appel(){
		appel=true;
	}
	public void nAppel(){
		appel=false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HandFacade getHand() {
		return hand;
	}
	
	public VenTuile getVent() {
		return vent;
	}

	public void setAppel(boolean appel) {
		this.appel = appel;
	}

	public boolean isAppel() {
		return appel;
	}
}
