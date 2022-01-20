package crudeDragon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MyConnexion {
	static Connection accessDataBase = null;
	
	 /**
	 * Connexion à ma base de donnée NESTI
	 * @throws SQLException
	 */
	public static void openConnection() {
		/* Parametres de connexion */
		 String url = "jdbc:mysql://127.0.0.1/bddragon"; //127.0.0.1 equivaut a localhost
		 
		 // nesti = nom de la bdd
		 String utilisateur = "root";
		 String motDePasse = "";
		 try {
			 System.out.println("try to connect");
			 // on ajoute nos paramètres
			 accessDataBase = DriverManager.getConnection(url, utilisateur, motDePasse);
			 
		 } catch (SQLException ex) {
			 Logger.getLogger(MyConnexion.class.getName()).log(Level.SEVERE, null,
			ex);
		 }

	}
	
	/**
	 * True si la connexion est OK
	 *
	 * @return
	 */
	 public static boolean testConnection() {
		 boolean flag = false;
		 try {
			 if (accessDataBase != null) {
				 if (!accessDataBase.isClosed()) {
					 System.out.println("You are now connected to server!");
					 flag = true;
				 }
			 }
	 } catch (SQLException ex) {
		 Logger.getLogger(MyConnexion.class.getName()).log(Level.SEVERE, null,ex);
	 	}
		 return flag;
	 }
	 
	 /**
		 * Pour fermer la connection, a faire en fin de script
		 *
		 * @throws SQLException
		 */
	 public static void closeConnection() {
		 if (accessDataBase != null) {
			 try {
				 accessDataBase.close();
				 System.out.println("Close connection");
		 } catch (SQLException e) {
			 System.err.println("Erreur fermeture: " + e.getMessage());
		 	}
		 }
	 }


	/**
	 * Testons la connexion
	 * @param args
	 * */
	
	public static void main(String[] args) {
		openConnection();
		testConnection();
//		closeConnection();
	}
}
