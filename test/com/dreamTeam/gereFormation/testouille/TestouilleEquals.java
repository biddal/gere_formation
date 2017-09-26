package com.dreamTeam.gereFormation.testouille;

import com.dreamTeam.gereFormation.modele.Stagiaire;

public class TestouilleEquals {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Stagiaire s1 = new Stagiaire(1,"TOTO");
		
		Stagiaire s2 = new Stagiaire(1,"KIKOU");

		if (s1.equals(s2)){
			System.out.println("kiku");
		}
		
	}

}
