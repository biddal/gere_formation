package com.dreamTeam.gereFormation.dao;

import java.util.List;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dreamTeam.gereFormation.modele.Stagiaire;

public class StagiaireDao {
	
	public static void update(Stagiaire s) throws Exception {
        Connection c = DBConnect.getConnection();
        PreparedStatement stm;
        try {

            stm = c.prepareStatement("UPDATE Stagiaire SET name = ? WHERE id = ?");
            stm.setString(1, s.getName());
            stm.setInt(2, s.getId());

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

            stm = c.prepareStatement("DELETE FROM Stagiaire WHERE id = ?");
            stm.setInt(1, s.getId());

            stm.executeUpdate();

        } catch (SQLException e) {

            throw new Exception("pb lors de la suppression de Stagiaire:" + e.getMessage());
        }
    }

    public static void save(Stagiaire s) throws Exception {

        Connection c = DBConnect.getConnection();
        PreparedStatement stm;

        stm = c.prepareStatement("INSERT INTO Stagiaire (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
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

            String sql = "select * from Stagiaire left join formation on Stagiaire.idformation = formation.id left join ecf on Stagiaire.id = ecf.idstagiaire";
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("Stagiaire.id");
                String name = rs.getString("Stagiaire.name");
                
                Stagiaire s = new Stagiaire(id, name);
                
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

            String sql = "select * from Stagiaire WHERE Stagiaire.id=" + numStagiaire;
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                
                s = new Stagiaire(id, name);

            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return s;
    }

}
