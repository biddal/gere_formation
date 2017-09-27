package com.dreamTeam.gereFormation;

import java.sql.SQLException;
import java.util.List;

import com.dreamTeam.gereFormation.dao.FormationDao;
import com.dreamTeam.gereFormation.dao.EcfDao;
import com.dreamTeam.gereFormation.dao.ModuleDao;
import com.dreamTeam.gereFormation.dao.SequenceDao;
import com.dreamTeam.gereFormation.dao.StagiaireDao;
import com.dreamTeam.gereFormation.modele.Ecf;
import com.dreamTeam.gereFormation.modele.Formation;
import com.dreamTeam.gereFormation.modele.Module;
import com.dreamTeam.gereFormation.modele.Sequence;
import com.dreamTeam.gereFormation.modele.Stagiaire;


public class gereFormation {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		
		
		List<Stagiaire> stagiaires = StagiaireDao.findAll();
		List<Sequence> sequences = SequenceDao.findAll();
		List<Formation> formations = FormationDao.findAll();
		List<Module> modules = ModuleDao.findAll();
		List<Ecf> ecfs = EcfDao.findAll();
		// TODO Auto-generated method stub
		Stagiaire biddal = StagiaireDao.findById(2);
		biddal.setFirstname("JC");
		try {
			StagiaireDao.update(biddal);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		for (Stagiaire stagiaire : stagiaires) {
			System.out.println(stagiaire);
		}
		
		for (Sequence sequence : sequences) {
			System.out.println(sequence);
		}
		
		for (Formation formation : formations) {
			System.out.println(formation);
		}
		
		for (Module module : modules) {
			System.out.println(module);
		}
	}

}
