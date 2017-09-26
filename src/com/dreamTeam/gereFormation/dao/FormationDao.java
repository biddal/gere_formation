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


public class FormationDao {
	
	
	public static void update(Formation f) throws Exception {
        Connection c = DBConnect.getConnection();
        PreparedStatement stm;
        try {

            stm = c.prepareStatement("UPDATE formation SET name = ? WHERE id = ?");
            stm.setString(1, f.getName());
            stm.setInt(2, f.getId());

            stm.executeUpdate();

        } catch (SQLException e) {
            //pb if here
            throw new Exception("pb lors de la mise a jour de Formation:" + e.getMessage());
        }
    }

    public static void delete(Formation f) throws Exception {
        Connection c = DBConnect.getConnection();
        PreparedStatement stm;
        try {

            stm = c.prepareStatement("DELETE FROM formation WHERE id = ?");
            stm.setInt(1, f.getId());

            stm.executeUpdate();

        } catch (SQLException e) {

            throw new Exception("pb lors de la suppression de Formation:" + e.getMessage());
        }
    }

    public static void save(Formation s) throws Exception {

        Connection c = DBConnect.getConnection();
        PreparedStatement stm;

        stm = c.prepareStatement("INSERT INTO formation (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, s.getName());

        stm.execute();
        ResultSet rs = stm.getGeneratedKeys();

        if (rs.next()) {
            s.setId(rs.getInt(1));
        }
        stm.close();

    }

    /**
     * retourne la liste des Formation
     *
     * @return
     */
    public static List<Formation> findAll() {

        Connection c = DBConnect.getConnection();

        List<Formation> ps = new ArrayList<>();
        // test avec select
        Statement stm;
        try {
            stm = c.createStatement();

            String sql = "select * from formation" /*left join formation on Formation.idformation = formation.id left join ecf on Formation.id = ecf.idFormation*/;
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("formation.id");
                String name = rs.getString("formation.name");
                int duration= rs.getInt("formation.duration");
                Date date_debut =rs.getDate("formation.date_debut");
                String lieu = rs.getString("formation.lieu");
                
                
                
                Formation f = new Formation(id, name, duration,date_debut,lieu);
                
               /* int villeId = rs.getInt("ville.id");
                if (!rs.wasNull()){ 
                    String nameVille = rs.getString("ville.name");
                    int nbhab = rs.getInt("ville.nbhabitant");
                    Capitale cap = new Capitale(nameVille, s, nbhab);
                    cap.setId(villeId);
                    s.setCapitale(cap);
                }*/
                

                ps.add(f);
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ps;
    }

    public static Formation findById(int numFormation) {
        Formation f = null;
        Connection c = DBConnect.getConnection();
        Statement stm;
        try {
            stm = c.createStatement();

            String sql = "select * from formation WHERE formation.id=" + numFormation;
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                int id = rs.getInt("id");
                
                String name = rs.getString("name");
                int duration = rs.getInt("duration");
                Date date_debut = rs.getDate("date_debut");
                String lieu = rs.getString("lieu");
                
                
                f = new Formation(id, name,duration,date_debut,lieu);

            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return f;
    }


}
