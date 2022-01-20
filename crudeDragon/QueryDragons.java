package crudeDragon;

import java.sql.Statement;
import java.util.Arrays;
import java.util.Scanner;


import java.nio.channels.GatheringByteChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class QueryDragons extends MyConnexion{
	
	static boolean testCreation = false;
	
	static Scanner saisir = new Scanner(System.in);
	
	
	public static void choixUser() {
		System.out.println("Bienvenue sur la page ! Le sujet est la base de donnee dragons");
		System.out.println("Que voulez-vous faire ? creer / modifier (tableau existant) / supprimer (un dragon) ?");
		
		boolean testEntree = true;
		
		while (testEntree) {
			String choixEntree = saisir.nextLine();
			
			
			if(!choixEntree.equals("creer") && !choixEntree.equals("modifier") && !choixEntree.equals("supprimer")) {
				System.out.println("Votre choix n'est pas valid. On recommence (creer ou modifier ou supprimer)");
	//			testEntree = true;
			} else {
				if (choixEntree.equals("creer")) {
					saisieChoixDragons();
				}
				else if (choixEntree.equals("modifier")) {
					updateNomDragons(choixEntree, choixEntree);
					readAll();  //ENLEVER quand code plus clair
				} else {
					deleteByNamePrepared(choixEntree);
				}
			}
		}
	}
	
	
	public static void saisieChoixDragons() {
			
		do {
			testCreation = true;
			
			System.out.println("Voulez-vous creer un nouveau dragon ? (oui / non)");
			String monChoix = saisir.nextLine();
			
			if (monChoix.equals("oui")) {
				System.out.println("Veuillez lui attribuer un numero d'ID (nombre > 20) : ");
				int choixId = saisir.nextInt();
				saisir.nextLine();
				System.out.println("Veuillez lui donner un nom : ");
				String choixNom = saisir.nextLine();
				System.out.println("Quel est son sexe ? (M / F) ");
				String choixSexe = saisir.nextLine();
				System.out.println("Quel est sa taille / longueur ? ");
				int choixLong = saisir.nextInt();
//				saisir.nextLine();
				System.out.println("Quels sont les nombres d'ecailles ? ");
				int choixEcailles = saisir.nextInt();
				saisir.nextLine();
				System.out.println("Crache-t-il du feu ce dragon (true/false) : ");
				boolean choixFeu = saisir.nextBoolean();
				saisir.nextLine();
				System.out.println("Quel est son comportement amoureux ?");
				String choixCompo = saisir.nextLine();
				
				createDragons(choixId, choixNom, choixSexe, choixLong, choixEcailles, choixFeu, choixCompo);
				System.out.println("La derniere ligne correspond a votre creation");
				readAll();
			}
			else if (monChoix.equals("non")) {
				System.out.println("Voici le tableau de dragon existant : ");
				readAll();
			}
			else {
				System.out.println("Votre choix n'est pas valide. On recommence");
				testCreation = false;
			}
	
		} while (!testCreation);
	}
	
	
	public static boolean createDragons (int Id_Dragon, String n, String sexe, int longueur, int nombreEcailles, Boolean cracheDuFeu, String comportementAmoureux) {
		 boolean flag = false;
		 try {	
			 System.out.println("Debut");
			 /*Requete*/
				 String query = "INSERT INTO dragons (Id_Dragon, dragon, sexe, longueur, nombreEcailles, cracheDuFeu, comportementAmoureux) VALUES (?,?,?,?,?,?,?)";
				 
				 PreparedStatement declaration = accessDataBase.prepareStatement(query);
				 
				 declaration.setInt(1, Id_Dragon); 
				 declaration.setString(2, n); 
				 declaration.setString(3, sexe); 
				 declaration.setInt(4, longueur); 
				 declaration.setInt(5, nombreEcailles); 
				 declaration.setBoolean(6, cracheDuFeu); 
				 declaration.setString(7, comportementAmoureux); 
				 
				 int executeUpdate = declaration.executeUpdate();
				 
				 flag = (executeUpdate == 1);
			 
		 } catch (Exception e) {
			 	System.err.println("Erreur d'insertion de dragon: " + e.getMessage());
		 	}  
		 System.out.println("Fin");
		 return flag;	 
	}
	
	
	/**
	 * Action de lire les tous les dragons
	 */
	public static void readAll() {
		 try {
			 System.out.println("Debut");
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
				 //Au lieu de récupérer les données dans un tableau d'objet, on va récupérer les données dans un objet.
				 // Un moule parfaitement adapté au données de l'entité dragons
		/*		 Dragons drag = new Dragons();
				 drag.setId_Dragon(resultat.getInt("id_Dragon"));
				 drag.setDragon(resultat.getString("dragon"));
				// drag.setSexe(resultat.getChar("sexe"));
				 drag.setLongueur(resultat.getInt("longueur"));
				 drag.setNombreEcailles(resultat.getInt("nombreEcailles"));
				 drag.setCracheDuFeu(resultat.getBoolean("cracheDuFeu"));
				 drag.setComportementAmoureux(resultat.getString("comportementAmoureux"));

				 
				 //Lire 
				 System.out.println(drag.toString());
		*/
			 }
		 } catch (Exception e) {
			 System.err.println("Erreur d'affichage de Dragon: " + e.getMessage());
		 }
		 System.out.println("Fin");
	}
	
	
	/**
	 * Action de mettre a jour toutes les colonnes de la table dragons
	 */
	/*public static void updateDragons() {
		updateNomDragons(nom, nom2);
	*/
	
	public static void saisieChoixUpdate() {
		boolean testUpdate = false;
		System.out.println("");
	}

	
	//Update by name using prepared Statement
	public static boolean updateNomDragons(String nom, String nom2) {
		 boolean success = false;
		 try {
			 System.out.println("Debut");
			 
			 //Indiquer les endroits ou seront places les variables avec ?
			 String query = "UPDATE dragons SET dragon = ? WHERE dragon = ?"; 
			  
			 //Les requetes préparées : PrepareStatement remplace l'ancien Statement.
			 PreparedStatement declaration = accessDataBase.prepareStatement(query);
			 
			 
			 //la méthode demande la requete en parametre // correspond a la position du ? qui est en position 1   
			 declaration.setString(1, nom); //Faire correspondre le premier point d'interrogation à notre paramètre nom
			 declaration.setString(2, nom2);
			 
			 int executeUpdate = declaration.executeUpdate();
			 
			 success = (executeUpdate == 1);
		 } catch (SQLException e) {
			 System.err.println("Erreur de mise a jour de nom: "+ e.getMessage());
		 }
		 	System.out.println("Fin");
		 	return success;
	}
	
	public static boolean updateIdDragons(int idDrag, int idDrag2) {
		 boolean success = false;
		 try {
			 System.out.println("Debut");
			 
			 //Indiquer les endroits ou seront places les variables avec ?
			 String query = "UPDATE dragons SET Id_Dragon = ? WHERE Id_Dragon = ?"; 
			  
			 //Les requetes préparées : PrepareStatement remplace l'ancien Statement.
			 PreparedStatement declaration = accessDataBase.prepareStatement(query);
			   
			 declaration.setInt(1, idDrag);
			 declaration.setInt(2, idDrag2);
			 
			 int executeUpdate = declaration.executeUpdate();
			 
			 success = (executeUpdate == 1);
		 } catch (SQLException e) {
			 System.err.println("Erreur de mise a jour de d'Id de Dragon: "+ e.getMessage());
		 }
		 	System.out.println("Fin");
		 	return success;
	}
	
	public static boolean updateSexeDragons(String sexeDrag, String sexeDrag2) {
		 boolean success = false;
		 try {
			 System.out.println("Debut");
			 
			 //Indiquer les endroits ou seront places les variables avec ?
			 String query = "UPDATE dragons SET sexe = ? WHERE sexe = ?"; 
			  
			 //Les requetes préparées : PrepareStatement remplace l'ancien Statement.
			 PreparedStatement declaration = accessDataBase.prepareStatement(query);
			   
			 declaration.setString(1, sexeDrag);
			 declaration.setString(2, sexeDrag2);
			 
			 int executeUpdate = declaration.executeUpdate();
			 
			 success = (executeUpdate == 1);
		 } catch (SQLException e) {
			 System.err.println("Erreur de mise a jour de sexe de Dragon: "+ e.getMessage());
		 }
		 	System.out.println("Fin");
		 	return success;
	}
	
	public static boolean updateLongueurDragons(String longDrag, String longDrag2) {
		 boolean success = false;
		 try {
			 System.out.println("Debut");
			 
			 //Indiquer les endroits ou seront places les variables avec ?
			 String query = "UPDATE dragons SET longueur = ? WHERE longueur = ?"; 
			  
			 //Les requetes préparées : PrepareStatement remplace l'ancien Statement.
			 PreparedStatement declaration = accessDataBase.prepareStatement(query);
			   
			 declaration.setString(1, longDrag);
			 declaration.setString(2, longDrag2);
			 
			 int executeUpdate = declaration.executeUpdate();
			 
			 success = (executeUpdate == 1);
		 } catch (SQLException e) {
			 System.err.println("Erreur de mise a jour de longueur de Dragon: "+ e.getMessage());
		 }
		 	System.out.println("Fin");
		 	return success;
	}
	
	public static boolean updateNombEcaillesDragons(int nbEcDrag, int nbEcDrag2) {
		 boolean success = false;
		 try {
			 System.out.println("Debut");
			 
			 //Indiquer les endroits ou seront places les variables avec ?
			 String query = "UPDATE dragons SET longueur = ? WHERE longueur = ?"; 
			  
			 //Les requetes préparées : PrepareStatement remplace l'ancien Statement.
			 PreparedStatement declaration = accessDataBase.prepareStatement(query);
			   
			 declaration.setInt(1, nbEcDrag);
			 declaration.setInt(2, nbEcDrag2);
			 
			 int executeUpdate = declaration.executeUpdate();
			 
			 success = (executeUpdate == 1);
		 } catch (SQLException e) {
			 System.err.println("Erreur de mise a jour de nombre d'ecailles de Dragon: "+ e.getMessage());
		 }
		 	System.out.println("Fin");
		 	return success;
	}
	
	public static boolean updateFeuDragons(Boolean cracheFeuDrag, Boolean cracheFeuDrag2) {
		 boolean success = false;
		 try {
			 System.out.println("Debut");
			 
			 //Indiquer les endroits ou seront places les variables avec ?
			 String query = "UPDATE dragons SET longueur = ? WHERE longueur = ?"; 
			  
			 //Les requetes préparées : PrepareStatement remplace l'ancien Statement.
			 PreparedStatement declaration = accessDataBase.prepareStatement(query);
			   
			 declaration.setBoolean(1, cracheFeuDrag);
			 declaration.setBoolean(2, cracheFeuDrag2);
			 
			 int executeUpdate = declaration.executeUpdate();
			 
			 success = (executeUpdate == 1);
		 } catch (SQLException e) {
			 System.err.println("Erreur de mise a jour de Dragon qui crache du feu: "+ e.getMessage());
		 }
		 	System.out.println("Fin");
		 	return success;
	}
	
	public static boolean updateComportementDragons(Boolean compAmoureuxDrag, Boolean compAmoureuxDrag2) {
		 boolean success = false;
		 try {
			 System.out.println("Debut");
			 
			 //Indiquer les endroits ou seront places les variables avec ?
			 String query = "UPDATE dragons SET longueur = ? WHERE longueur = ?"; 
			  
			 //Les requetes préparées : PrepareStatement remplace l'ancien Statement.
			 PreparedStatement declaration = accessDataBase.prepareStatement(query);
			   
			 declaration.setBoolean(1, compAmoureuxDrag);
			 declaration.setBoolean(2, compAmoureuxDrag2);
			 
			 int executeUpdate = declaration.executeUpdate();
			 
			 success = (executeUpdate == 1);
		 } catch (SQLException e) {
			 System.err.println("Erreur de mise a jour de comportement amoureux du Dragon: "+ e.getMessage());
		 }
		 	System.out.println("Fin");
		 	return success;
	}
	
	//Delete by name using prepared Statement
	public static boolean deleteByNamePrepared(String nom) {
		 boolean success = false;
		 try {
			 
			 System.out.println("Debut");
			 //Indiquer les endroits ou seront places les variables avec ?
			 String query = "DELETE FROM dragons WHERE dragon = ?";
			 
			 
			 //Les requetes préparées : PrepareStatement remplace l'ancien Statement.
			 PreparedStatement declaration = accessDataBase.prepareStatement(query);
			 
			 
			 //la méthode demande la requete en parametre // correspond a la position du ? qui est en position 1   
			 declaration.setString(1, nom); //Faire correspondre le premier point d'interrogation à notre paramètre nom
			 
			 
			 int executeUpdate = declaration.executeUpdate();
			 
			 success = (executeUpdate == 1);
		 } catch (SQLException e) {
			 System.err.println("Erreur suppression de dragon: "+ e.getMessage());
		 }
		 	System.out.println("Fin");
		 	return success;
	}
	
	
	

	 /**
	 * Ici on test
	 * @param args the command line arguments
	 */
	 public static void main(String[] args) {
		 openConnection();
		 testConnection();
		 
		 choixUser();
		 
	//	 saisieChoixDragons();
		 
	//	 readAll();
		 
	//	 createDragons(10, "Bruv", "F", 126, 1547, true, "adala");
		 
		 //Apres
//		 readAll();
		 
	//	 updateDragons();
		 
		//Apres
		 readAll();
		 
		 deleteByNamePrepared("d");
		 
		 readAll();
			//Apres
//		 readAll();
		 
//		 closeConnection();
	 }


	
}
