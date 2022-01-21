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
	
	static boolean testEntree = false;
	
	static Scanner saisir = new Scanner(System.in);
	
	
	//La methode sera appelee dans MainDriver
	public static void choixUser() {
		
		
		System.out.println("Bienvenue sur la page ! Le sujet est la base de donnee dragons");
		
		do {
			
			testEntree = true;
			System.out.println("Que voulez-vous faire ? creer / modifier (tableau existant) / supprimer (un dragon) ?");
		
			String choixEntree = saisir.nextLine();
			
		
			if (choixEntree.equals("creer")) {
				saisieChoixCreate();
			}
			else if (choixEntree.equals("modifier")) {
				saisieChoixUpdate();
				
			}else if (choixEntree.equals("supprimer")) {
				saisieChoixDelete();
			}
			else {				
				System.out.println("Votre choix n'est pas valid. On recommence");
				testEntree = false;
			}
		
		} while (!testEntree);
	}
	
	//Creation d'un objet dragon
	public static void saisieChoixCreate() {
			
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
				continueOuPas();
			}
			else if (monChoix.equals("non")) {
				System.out.println("Voici le tableau de dragon existant : ");
				readAll();
				continueOuPas();
			}
			else {
				System.out.println("Votre choix n'est pas valide. On recommence");
				testCreation = false;
			}
	
		} while (!testCreation);
		
	}
	
	/*
	 * Methode de requete insertion / creation
	 * */
	public static boolean createDragons (int id_Dragon, String n, String sexe, int longueur, int nombreEcailles, Boolean cracheDuFeu, String comportementAmoureux) {
		 boolean flag = false;
		 try {	
			 System.out.println("Debut");
			 /*Requete*/
				 String query = "INSERT INTO dragons (id_Dragon, dragon, sexe, longueur, nombreEcailles, cracheDuFeu, comportementAmoureux) VALUES (?,?,?,?,?,?,?)";
				 
				 PreparedStatement declaration = accessDataBase.prepareStatement(query);
				 
				 declaration.setInt(1, id_Dragon); 
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
	 * Action de lire /afficher les tous les dragons
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
				 
			 }
		 } catch (Exception e) {
			 System.err.println("Erreur d'affichage de Dragon: " + e.getMessage());
		 }
		 System.out.println("Fin");
	}
	
	
	/**
	 * Action de mettre a jour toutes les colonnes de la table dragons
	 */
	
	
	public static void saisieChoixUpdate() {
		boolean testUpdate = false;
		
		do {
			testUpdate = true;
			
			System.out.println("Quel dragon voulez-vous modifier ? (indiquer son nom)");
			String nomAChanger = saisir.nextLine();
			
			System.out.println("Que voulez-vous changer (nom / id / sexe / longueur / ecailles / feu / amour) ?");
			String choixAFaire = saisir.nextLine();
			
			if (choixAFaire.equals("nom")) {
				
				System.out.println("Saisissez le nouveau nom : ");
				String nouveauNom = saisir.nextLine();
				
				updateNomDragons(nouveauNom, nomAChanger);
				
				validationUpdate();
				
			} else if (choixAFaire.equals("id")) {
				
				System.out.println("Saisissez le nouveau ID : ");
				int nouveauID = saisir.nextInt();
				
				updateIdDragons(nouveauID, nomAChanger);
				saisir.nextLine();
				validationUpdate();
				
			} else if (choixAFaire.equals("sexe")) {
				saisir.nextLine();
				
				System.out.println("Saisissez le nouveau sexe : ");
				
				String nouveauSexe = saisir.nextLine();
				
				updateSexeDragons(nouveauSexe, nomAChanger);
				
				validationUpdate();
				
			} else if (choixAFaire.equals("longueur")) {

				System.out.println("Saisissez la nouvelle longueur : ");
				int nouvelleLong = saisir.nextInt();
				
				updateLongueurDragons(nouvelleLong, nomAChanger);
				saisir.nextLine();
				validationUpdate();
				
			} else if (choixAFaire.equals("ecailles")) {

				
				System.out.println("Saisissez le nouveau nombres d'ecailles : ");
				
				int nouveauNbEcailles = saisir.nextInt();
				
				updateNombEcaillesDragons(nouveauNbEcailles, nomAChanger);
				saisir.nextLine();
				validationUpdate();
					
			} else if (choixAFaire.equals("feu")) {
				
				System.out.println("Saisissez le nouveau caracteristique du feu : taper : 'true' si il crache du feu et 'false' sans feu");
				
				boolean nouveauFeu = saisir.nextBoolean();
				
				updateFeuDragons(nouveauFeu, nomAChanger);
				saisir.nextLine();
				validationUpdate();
					
			} else if (choixAFaire.equals("amour")) {
				
				System.out.println("Saisissez le nouveau comportement amoureux : ");
				
				String nouveauCompo = saisir.nextLine();
				
				updateComportementDragons(nouveauCompo, nomAChanger);
				
				validationUpdate();
				
			} else {
				System.out.println("Reponse non valide");
				testUpdate = false;

			}					
		} while (!testUpdate);
}
	
	//
	public static void validationUpdate() {
		System.out.println("Modification validee !");
		System.out.println("Voir le changement dans le tableau");
		readAll();
		continueOuPas();
	}
	
	//Update by name using prepared Statement
	public static boolean updateNomDragons(String nouveauNom, String nomAChanger) {
		 boolean success = false;
		 try {
			 System.out.println("Debut");
			 
			 //Indiquer les endroits ou seront places les variables avec ?
			 String query = "UPDATE dragons SET dragon = ? WHERE dragon = ?"; 
			  
			 //Les requetes préparées : PrepareStatement remplace l'ancien Statement.
			 PreparedStatement declaration = accessDataBase.prepareStatement(query);
			 
			   
			 declaration.setString(1, nouveauNom); 
			 declaration.setString(2, nomAChanger);
			 
			 int executeUpdate = declaration.executeUpdate();
			 
			 success = (executeUpdate == 1);
		 } catch (SQLException e) {
			 System.err.println("Erreur de mise a jour de nom: "+ e.getMessage());
		 }
		 	System.out.println("Fin");
		 	return success;
	}
	
	/*Methode de requete UPDATE / modification 
	 * name
	 * */
	public static boolean updateIdDragons(int nouveauID, String nomAChanger) {
		 boolean success = false;
		 try {
			 System.out.println("Debut");
			 
		
			 String query = "UPDATE dragons SET Id_Dragon = ? WHERE dragon = ?"; 
			  
			 PreparedStatement declaration = accessDataBase.prepareStatement(query);
			   
			 declaration.setInt(1, nouveauID);
			 declaration.setString(2, nomAChanger);
			 
			 int executeUpdate = declaration.executeUpdate();
			 
			 success = (executeUpdate == 1);
		 } catch (SQLException e) {
			 System.err.println("Erreur de mise a jour de d'Id de Dragon: "+ e.getMessage());
		 }
		 	System.out.println("Fin");
		 	return success;
	}
	
	public static boolean updateSexeDragons(String nouveauSexe, String nomAChanger) {
		 boolean success = false;
		 try {
			 System.out.println("Debut");
			 
			 //Indiquer les endroits ou seront places les variables avec ?
			 String query = "UPDATE dragons SET sexe = ? WHERE dragon = ?"; 
			  
			 //Les requetes préparées : PrepareStatement remplace l'ancien Statement.
			 PreparedStatement declaration = accessDataBase.prepareStatement(query);
			   
			 declaration.setString(1, nouveauSexe);
			 declaration.setString(2, nomAChanger);
			 
			 int executeUpdate = declaration.executeUpdate();
			 
			 success = (executeUpdate == 1);
		 } catch (SQLException e) {
			 System.err.println("Erreur de mise a jour de sexe de Dragon: "+ e.getMessage());
		 }
		 	System.out.println("Fin");
		 	return success;
	}
	
	public static boolean updateLongueurDragons(int nouvelleLong, String nomAChanger) {
		 boolean success = false;
		 try {
			 System.out.println("Debut");
			 
			 //Indiquer les endroits ou seront places les variables avec ?
			 String query = "UPDATE dragons SET longueur = ? WHERE dragon = ?"; 
			  
			 //Les requetes préparées : PrepareStatement remplace l'ancien Statement.
			 PreparedStatement declaration = accessDataBase.prepareStatement(query);
			   
			 declaration.setInt(1, nouvelleLong);
			 declaration.setString(2, nomAChanger);
			 
			 int executeUpdate = declaration.executeUpdate();
			 
			 success = (executeUpdate == 1);
		 } catch (SQLException e) {
			 System.err.println("Erreur de mise a jour de longueur de Dragon: "+ e.getMessage());
		 }
		 	System.out.println("Fin");
		 	return success;
	}
	
	public static boolean updateNombEcaillesDragons(int nouveauNbEcailles, String nomAChanger) {
		 boolean success = false;
		 try {
			 System.out.println("Debut");
			 
			 //Indiquer les endroits ou seront places les variables avec ?
			 String query = "UPDATE dragons SET nombreEcailles = ? WHERE dragon = ?"; 
			  
			 //Les requetes préparées : PrepareStatement remplace l'ancien Statement.
			 PreparedStatement declaration = accessDataBase.prepareStatement(query);
			   
			 declaration.setInt(1, nouveauNbEcailles);
			 declaration.setString(2, nomAChanger);
			 
			 int executeUpdate = declaration.executeUpdate();
			 
			 success = (executeUpdate == 1);
		 } catch (SQLException e) {
			 System.err.println("Erreur de mise a jour de nombre d'ecailles de Dragon: "+ e.getMessage());
		 }
		 	System.out.println("Fin");
		 	return success;
	}
	
	public static boolean updateFeuDragons(Boolean nouveauFeu, String nomAChanger) {
		 boolean success = false;
		 try {
			 System.out.println("Debut");
			 
			 //Indiquer les endroits ou seront places les variables avec ?
			 String query = "UPDATE dragons SET cracheDuFeu = ? WHERE dragon = ?"; 
			  
			 //Les requetes préparées : PrepareStatement remplace l'ancien Statement.
			 PreparedStatement declaration = accessDataBase.prepareStatement(query);
			   
			 declaration.setBoolean(1, nouveauFeu);
			 declaration.setString(2, nomAChanger);
			 
			 int executeUpdate = declaration.executeUpdate();
			 
			 success = (executeUpdate == 1);
		 } catch (SQLException e) {
			 System.err.println("Erreur de mise a jour de Dragon qui crache du feu: "+ e.getMessage());
		 }
		 	System.out.println("Fin");
		 	return success;
	}
	
	public static boolean updateComportementDragons(String compAmoureuxDrag, String nomAChanger) {
		 boolean success = false;
		 try {
			 System.out.println("Debut");
			 
			 //Indiquer les endroits ou seront places les variables avec ?
			 String query = "UPDATE dragons SET comportementAmoureux = ? WHERE dragon = ?"; 
			  
			 //Les requetes préparées : PrepareStatement remplace l'ancien Statement.
			 PreparedStatement declaration = accessDataBase.prepareStatement(query);
			   
			 declaration.setString(1, compAmoureuxDrag);
			 declaration.setString(2, nomAChanger);
			 
			 int executeUpdate = declaration.executeUpdate();
			 
			 success = (executeUpdate == 1);
		 } catch (SQLException e) {
			 System.err.println("Erreur de mise a jour de comportement amoureux du Dragon: "+ e.getMessage());
		 }
		 	System.out.println("Fin");
		 	return success;
	}
	
	public static void continueOuPas() {
		
		String choixContinue = "";
		
			System.out.println("Voulez-vous continuer a faire autre chose ? (oui / non)");
			choixContinue = saisir.nextLine();
			if (choixContinue.equals("oui")) {
				testEntree = false;
			} else {
				System.out.println("Merci et a bientot !");
			}	
	}
	
	public static void saisieChoixDelete() {
		System.out.println("Quel dragon voulez-vous supprimer ? (indiquer son nom)");
		
		String nomASupprimer = saisir.nextLine();
		
		deleteByNamePrepared(nomASupprimer);
		
		System.out.println("La ligne correspondante a ete supprimee. Ci dessous le tableau dragon");
		readAll();
		continueOuPas();
	}
	
	//Delete by name using prepared Statement
	public static boolean deleteByNamePrepared(String nomASupprimer) {
		 boolean success = false;
		 try {
			 
			 System.out.println("Debut");
			 //Indiquer les endroits ou seront places les variables avec ?
			 String query = "DELETE FROM dragons WHERE dragon = ?";
			 			 
			 //Les requetes préparées : PrepareStatement remplace l'ancien Statement.
			 PreparedStatement declaration = accessDataBase.prepareStatement(query);
			 
			 declaration.setString(1, nomASupprimer);
			 
			 
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
		 
		 
		 closeConnection();
	 }


	
}
