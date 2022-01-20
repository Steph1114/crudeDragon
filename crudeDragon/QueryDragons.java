package crudeDragon;

import java.sql.Statement;
import java.util.Arrays;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class QueryDragons extends MyConnexion{
	
	
	public static boolean createByName(String drag) {
		 boolean flag = false;
		 try {
			 Statement declaration = accessDataBase.createStatement();
			 
			 /*Requete*/
			 String query = "INSERT INTO `dragons`(`dragon`) VALUES(?)";
			 
			 int executeUpdate = declaration.executeUpdate(query);
			 
			 ((PreparedStatement) declaration).setString(1, drag);

			 flag = (executeUpdate == 1);
			 
		 }catch (Exception e) {
		 System.err.println("Erreur d'insertion ingredient: " + e.getMessage());
	 }
		 return flag;
}
	
	
	/**
	 * Action de lire les tous les dragons
	 */
	public static void readAll() {
		 try {
			 Statement declaration = accessDataBase.createStatement();
			 
			 /*Requete*/
			 String query = "SELECT * FROM dragons;";
			 
			 ResultSet resultat = declaration.executeQuery(query);
		 
			 /* Récupération des données */
			 while (resultat.next()) {
				 Object[] row = new Object[]{
						 resultat.getInt("id_Dragon"),
						 resultat.getString("dragon"),
						 resultat.getString("sexe"),
						 resultat.getInt("longueur"),
						 resultat.getInt("nombreEcailles"),
						 resultat.getBoolean("cracheDuFeu"),
						 resultat.getString("comportementAmoureux")
				 };
				 System.out.println(Arrays.toString(row));
				 
				 //Optimisattion de la 1ere requete de selection
				 //A lieu de récupérer les données dans un tableau d'objet, on va récupérer lesdonnées dans un objet.
				 // Un moule parfaitement adapté au données de l'entité Ingrédient.
				 Dragons drag = new Dragons();
				 drag.setId_Dragon(resultat.getInt("id_Dragon"));
				 drag.setDragon(resultat.getString("dragon"));
				// drag.setSexe(resultat.getChar("sexe"));
				 drag.setLongueur(resultat.getInt("longueur"));
				 drag.setNombreEcailles(resultat.getInt("nombreEcailles"));
				 drag.setCracheDuFeu(resultat.getBoolean("cracheDuFeu"));
				 drag.setComportementAmoureux(resultat.getString("comportementAmoureux"));

				 
				 //Lire 
				 System.out.println(drag.toString());

			 }
		 } catch (Exception e) {
			 System.err.println("Erreur d'affichage d'ing: " + e.getMessage());
		 }
	}
	
	
	/**
	 * Action de mettre a jour toutes les colonnes de la table dragons
	 */
	
	public static boolean update(String nom, String nom2) {
		 boolean success = false;
		 try {
			 //Indiquer les endroits ou seront places les variables avec ?
			 String query = "UPDATE dragons SET dragon = ? WHERE dragon = ?"; // Cible la case de nomIngredient avec un Id = 36
			 
			//String query2 = "UPDATE ingredients SET nesti_nomIngredient = ? WHERE Id_Ingredient = 36  "; //Remplace tout dans le tableau de nomIngredient par ce qu'on a set dans update() donc update(strin
			 
			 
			 //Les requetes préparées : PrepareStatement remplace l'ancien Statement.
			 PreparedStatement declaration = accessDataBase.prepareStatement(query);
			 
			 
			 //la méthode demande la requete en parametre // correspond a la position du ? qui est en position 1   
			 declaration.setString(1, nom); //Faire correspondre le premier point d'interrogation à notre paramètre nom
			 declaration.setString(2, nom2);
			 
			 int executeUpdate = declaration.executeUpdate();
			 
			 success = (executeUpdate == 1);
		 } catch (SQLException e) {
			 System.err.println("Erreur suppression ingredient: "+ e.getMessage());
		 }
		 	return success;
	}
	
	
	

	 /**
	 * Ici on test
	 * @param args the command line arguments
	 */
	 public static void main(String[] args) {
		 openConnection();
		 testConnection();
		 
		 readAll();
		 
		 createByName("Bruv");
		 //avant
//		 readAll();
//		 System.out.println("fin first read");
		 
		 //Creation
//		 create("sel");
//		 System.out.println("fin create");
//		 //La repetition est due a Auto-Implement (Auto-completion)
//		 
//		 //Creation
//		 create("farine");
//		 System.out.println("fin create");
//		 
//		 //Creation
//		 create("sucre");
//		 System.out.println("fin create");
//		 
//		 create("eau");
//		 System.out.println("fin create");
//		 
//		 //Creation
//		 create("poivre");
//		 System.out.println("fin create");
		 
		 //Delete par id
//		 delete(9);
//		 delete(8);
//		 delete(10);
//		 delete(11);
//		 delete(12);
//		 delete(13);
		 
		//Delete par nomIngredient
//		 deleteByName("sel"); //Supprime le nom d'ingredient "sel"
		 
//		 deleteByName("sel\" OR \"\" = \""); //Supprime sel ou une ligne de qqchose = vide

		 
//		 deleteByNamePrepared("sel"); //Supprime le nom ingredient sel dans colonne nomIngredient
		 
		 
//		 createByName("levure");
		 
		 
//		 update("poire", "mangue");
		 
		//apres
//		 readAll();		
//		 System.out.println("fin second read");
		 
		 
//		 closeConnection();
	 }
}
