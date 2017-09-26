package com.dreamTeam.gereFormation.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dreamTeam.gereFormation.modele.Formation;
import com.dreamTeam.gereFormation.modele.Stagiaire;

public class StagiaireDao {
	
	public static void update(Stagiaire s) throws Exception {
        Connection c = DBConnect.getConnection();
        PreparedStatement stm;
        try {

            stm = c.prepareStatement("UPDATE stagiaire SET name = ? ,firstname = ?, adresse = ? ,code_postal = ? ,ville = ?, email = ? ,telephone = ?, date_naissance = ?, idformation = ? WHERE id = ?");
            stm.setString(1, s.getName());
            stm.setString(2, s.getFirstname());
            stm.setString(3, s.getAdresse());
            stm.setString(4, s.getCode_postal());
            stm.setString(5, s.getVille());
            stm.setString(6, s.getEmail());
            stm.setString(7, s.getTelephone());
            stm.setDate(8, s.getDate());
            stm.setInt(9, s.getFormation().getId());
            stm.setInt(10, s.getId());

            stm.executeUpdate();

        } catch (SQLException e) {
            //pb if here
            throw new Exception("pb lors de la mise a jour de Stagiaire:" + e.getMessage());
        }
    }

    public static void delete(Stagiaire s) throws Exception {
        Connection c = DBConnect.getConnection();
        PreparedStatement stm;
        try {

            stm = c.prepareStatement("DELETE FROM stagiaire WHERE id = ?");
            stm.setInt(1, s.getId());

            stm.executeUpdate();

        } catch (SQLException e) {

            throw new Exception("pb lors de la suppression de Stagiaire:" + e.getMessage());
        }
    }

    public static void save(Stagiaire s) throws Exception {

        Connection c = DBConnect.getConnection();
        PreparedStatement stm;

        stm = c.prepareStatement("INSERT INTO stagiaire (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, s.getName());

        stm.execute();
        ResultSet rs = stm.getGeneratedKeys();

        if (rs.next()) {
            s.setId(rs.getInt(1));
        }
        stm.close();

    }

    /**
     * retourne la liste des Stagiaire
     *
     * @return
     */
    public static List<Stagiaire> findAll() {

        Connection c = DBConnect.getConnection();

        List<Stagiaire> ps = new ArrayList<>();
        // test avec select
        Statement stm;
        try {
            stm = c.createStatement();

            String sql = "select * from stagiaire" /*left join formation on Stagiaire.idformation = formation.id left join ecf on Stagiaire.id = ecf.idstagiaire*/;
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("stagiaire.id");
                String name = rs.getString("stagiaire.name");
                String firstname = rs.getString("stagiaire.firstname");
                String adresse = rs.getString("stagiaire.adresse");
                String code_postal = rs.getString("stagiaire.code_postal");
                String ville = rs.getString("stagiaire.ville");
                String email = rs.getString("stagiaire.email");
                String telephone = rs.getString("stagiaire.telephone");
                Date date = rs.getDate("stagiaire.date_naissance");
                int id_formation = rs.getInt("stagiaire.idformation");
                
                Formation formation = FormationDao.findById(id_formation);
                
                Stagiaire s = new Stagiaire(id, name, firstname, adresse, code_postal, ville, email, telephone, date, formation);
                
               /* int villeId = rs.getInt("ville.id");
                if (!rs.wasNull()){ 
                    String nameVille = rs.getString("ville.name");
                    int nbhab = rs.getInt("ville.nbhabitant");
                    Capitale cap = new Capitale(nameVille, s, nbhab);
                    cap.setId(villeId);
                    s.setCapitale(cap);
                }*/
                

                ps.add(s);
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ps;
    }

    public static Stagiaire findById(int numStagiaire) {
        Stagiaire s = null;
        Connection c = DBConnect.getConnection();
        Statement stm;
        try {
            stm = c.createStatement();

            String sql = "select * from stagiaire WHERE stagiaire.id=" + numStagiaire;
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
            	int id = rs.getInt("stagiaire.id");
                String name = rs.getString("stagiaire.name");
                String firstname = rs.getString("stagiaire.firstname");
                String adresse = rs.getString("stagiaire.adresse");
                String code_postal = rs.getString("stagiaire.code_postal");
                String ville = rs.getString("stagiaire.ville");
                String email = rs.getString("stagiaire.email");
                String telephone = rs.getString("stagiaire.telephone");
                Date date = rs.getDate("stagiaire.date_naissance");
                int id_formation = rs.getInt("stagiaire.idformation");
                
                Formation formation = FormationDao.findById(id_formation);
                
                s = new Stagiaire(id, name, firstname, adresse, code_postal, ville, email, telephone, date, formation);

            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return s;
    }

}
