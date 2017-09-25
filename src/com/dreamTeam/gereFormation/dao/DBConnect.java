package com.dreamTeam.gereFormation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	
	 private static Connection conn =null;

	 final static String URL = "jdbc:mysql://localhost/gere_formation";
        /**
         * 
         * @return RunTimeException() if any pb
         */
        public static Connection  getConnection () 
        {
            if (conn==null) {

                     try
                     {
                         Class.forName("com.mysql.jdbc.Driver").newInstance();

                       // 
                         conn=  DriverManager.getConnection(URL,"root","Stackable25");
                     }
                       catch(SQLException sqlE)
                       {
                               //TODO Logging
                           System.out.println("Sql Erreur " + sqlE.getMessage());
                           throw new RuntimeException();
                       }
                       catch(Exception e)
                       {
                          e.printStackTrace();
                          throw new RuntimeException(e);
                       }
            }

            return conn;

}
}
