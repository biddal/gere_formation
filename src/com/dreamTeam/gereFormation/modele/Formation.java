package com.dreamTeam.gereFormation.modele;

import java.sql.Date;

public class Formation {
	
	private int id;
	private String name;
	private int duration;
	private Date date_debut;
	private String lieu;
	
	
	public Formation() {
	}
	public Formation(int id, String name, int duration, Date date_debut,
			String lieu) {
		super();
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.date_debut = date_debut;
		this.lieu = lieu;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public Date getDate_debut() {
		return date_debut;
	}
	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
}
