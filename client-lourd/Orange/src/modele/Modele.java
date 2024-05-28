package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.CallableStatement;

import controleur.Technicien;
import controleur.ViewTech;
import controleur.Client;
import controleur.Intervention;
import controleur.Materiel;

public class Modele {
	private static Bdd uneBdd = new Bdd ("localhost","orange","root","");
	
	
	
	
	/************************* Gestion des techniciens ******************/
	public static String hashKey()
	{
		String key = null;
		String requete = "select cle from hashing;";
		
		try {
			uneBdd.seConnecter();
			//création d'un curseur pour exécuter la requete 
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			//execution de la requete et récuperation d'un resultat 
			ResultSet unRes = unStat.executeQuery(requete); 
			//s'il y a un resultat, on récupere les champs 
			
			if (unRes.next())
			{
				key = unRes.getString("cle");
			}
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " +requete);
		}
		
		return key;
	}
	
	
	public static Technicien selectWhereTechnicien (String email, String mdp, String hashKey)
	{
		
		// vérifier le mail et le mdp
		Technicien unTechnicien = null; 
		String requete = "select * from fullTechnicien where email='"+email+"' and mdp = sha1(concat('"+mdp+"', '"+hashKey+"')); ";

		try {
			uneBdd.seConnecter();
			//création d'un curseur pour exécuter la requete 
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			//execution de la requete et récuperation d'un resultat 
			ResultSet unRes = unStat.executeQuery(requete);
			//s'il y a un resultat, on récupere les champs 
			if (unRes.next() == true) {
				unTechnicien = new Technicien (
						unRes.getInt("idUser"),   
						unRes.getString("nom"), 
						unRes.getString("prenom"), 
						unRes.getString("adresse"), 
						unRes.getString("telephone"), 
						unRes.getString("email"),
						unRes.getString("mdp"),
						unRes.getString("specialite"),
						unRes.getString("dateEntree"),
						unRes.getString("poste")
						
						);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " +requete);
		}
		
		return unTechnicien; 
	}
	
	
	
	public static ArrayList<Technicien> selectAllTechniciens(String filtre)
	{
		ArrayList<Technicien> lesTechniciens = new ArrayList<Technicien>(); 		
		String requete =""; 
		if (filtre.equals("")) {
			requete = "select * from fullTechnicien ;"; 
		}else {
			requete = "select * from fullTechnicien where nom like '%"+filtre
					+"%' or prenom like '%"+filtre 
					+"%' or adresse like '%"+filtre
					+"%' or telephone like '%"+filtre
					+"%' or email like '%"+filtre 
					+"%' or specialite like '%"+filtre 
					+"%' or dateEntree like '%"+filtre 
					+"%' or poste like '%"+filtre +"%' ; ";
		}
		
		try {
			uneBdd.seConnecter();
			//création d'un curseur pour exécuter la requete 
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			//execution de la requete et récuperation d'un resultat 
			ResultSet desRes = unStat.executeQuery(requete); 
			//s'il y a un resultat, on récupere les champs 
			while (desRes.next()) {
				Technicien unTechnicien = new Technicien (
						desRes.getInt("idUser"),   
						desRes.getString("nom"), 
						desRes.getString("prenom"), 
						desRes.getString("adresse"), 
						desRes.getString("telephone"), 
						desRes.getString("email"),
						desRes.getString("mdp"),
						desRes.getString("specialite"),
						desRes.getString("dateEntree"),
						desRes.getString("poste")
						);
				lesTechniciens.add(unTechnicien);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " +requete);
		}
		
		return lesTechniciens; 
	}
	
	
	
	
	public static ArrayList<ViewTech> selectAllViewTechs()
	{
		ArrayList<ViewTech> lesViewTechs = new ArrayList<ViewTech>(); 
		String requete = "select * from techniciensinterventions ;";
		
		try {
			uneBdd.seConnecter();
			//création d'un curseur pour exécuter la requete 
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			//execution de la requete et récuperation d'un resultat 
			ResultSet desRes = unStat.executeQuery(requete); 
			//s'il y a un resultat, on récupere les champs 
			while (desRes.next()) {
				ViewTech unViewTech = new ViewTech (   
						desRes.getString("nom"), 
						desRes.getString("prenom"),
						desRes.getString("email"),
						desRes.getInt("nbInterventions"),
						desRes.getInt("totalPrix")
						);
				lesViewTechs.add(unViewTech);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " +requete);
		}
		
		return lesViewTechs; 
	}
	
	
	
	

	public static void updateTechnicien(Technicien unTechnicien) {
		String requete = "";
		
		if (unTechnicien.getMdp() == "")
		{
			requete = "Call modifyTechnicien(" + unTechnicien.getIdtechnicien() + ", '"+unTechnicien.getNom()
		 	+"', '"+unTechnicien.getPrenom()
		 	+"', '"+unTechnicien.getAdresse()
		 	+"', '"+unTechnicien.getTelephone()
		 	+"', '"+unTechnicien.getEmail()
		 	+"', '"+unTechnicien.getMdp()
		 	+"', '"+unTechnicien.getSpecialite()
		 	+"', '"+unTechnicien.getPoste() + "');";
		}else
		{
			requete = "Call modifyTechnicienNoMdp(" + unTechnicien.getIdtechnicien() + ", '"+unTechnicien.getNom()
		 	+"', '"+unTechnicien.getPrenom()
		 	+"', '"+unTechnicien.getAdresse()
		 	+"', '"+unTechnicien.getTelephone()
		 	+"', '"+unTechnicien.getEmail()
		 	+"', '"+unTechnicien.getSpecialite()
		 	+"', '"+unTechnicien.getPoste() + "');";
		}
		 
		
		
		 try {
			 uneBdd.seConnecter();
			 Statement unStat = uneBdd.getMaConnexion().createStatement();
			 unStat.execute(requete); 
			 unStat.close();
			 uneBdd.seDeConnecter();
		 }
		 catch (SQLException exp) {
			 System.out.println("Erreur de requete : " +requete);
		 }
		
	}
	
	
	public static void insertMateriel(Materiel unMateriel) {
		 String requete = "insert into materiel values (null, '"+unMateriel.getDesignation()
		 + "','"+unMateriel.getMarque()+"','"+ unMateriel.getModele()
		 	+ "','"+unMateriel.getPrixAchat()+"','"+ unMateriel.getDateAchat()
		 	+ "','"+unMateriel.getIdUser()+ "') ;";
		 try {
			 uneBdd.seConnecter();
			 Statement unStat = uneBdd.getMaConnexion().createStatement();
			 unStat.execute(requete); 
			 unStat.close();
			 uneBdd.seDeConnecter();
			 System.out.println(requete);
		 }
		 catch (SQLException exp) {
			 System.out.println("Erreur de requete : " +requete);
		 }
	}
	
	
	public static ArrayList<Intervention> selectAllInterventions (String filtre){
		ArrayList <Intervention> lesInterventions = new ArrayList<Intervention>(); 
		String requete =""; 
		if (filtre.equals("")) {
			requete = "select * from intervention ;"; 
		}else {
			requete = "select * from intervention where date_inter like '%"+filtre
					+"%' or duree like '%"+filtre 
					+"%' or statut like '%"+filtre
					+"%' or description like '%"+filtre
					+"%' or idMateriel like '%"+filtre 
					+"%' or idtechnicien like '%"+filtre 
					+"%' or prix like '%"+filtre +"%';";
		}
		 try {
			 uneBdd.seConnecter();
			 Statement unStat = uneBdd.getMaConnexion().createStatement();
			 ResultSet desRes = unStat.executeQuery(requete);
			 while (desRes.next()) {
				 Intervention uneIntervention = new Intervention(
							 desRes.getInt("idIntervention"), 
							 desRes.getString("date_inter"),
							 desRes.getFloat("duree"),
							 desRes.getString("statut"), 
							 desRes.getString("description"), 
							 desRes.getInt("idMateriel"), 
							 desRes.getInt("idtechnicien"),
							 desRes.getFloat("prix")
						 );
			    lesInterventions.add(uneIntervention);
			 }
			 unStat.close();
			 uneBdd.seDeConnecter();
		 }
		 catch (SQLException exp) {
			 System.out.println("Erreur de requete : " +requete);
		 }
		return lesInterventions; 
	}
	
	
	
	public static ArrayList<Materiel> selectAllMateriels (String filtre){
		ArrayList <Materiel> lesMateriels = new ArrayList<Materiel>(); 
		String requete =""; 
		if (filtre.equals("")) {
			requete = "select * from materiel ;"; 
		}else {
			requete = "select * from materiel where designation like '%"+filtre
					+"%' or marque like '%"+filtre 
					+"%' or modele like '%"+filtre
					+"%' or prixachat like '%"+filtre 
					+"%' or date_achat like '%"+filtre +"%' ; ";
		}
		 try {
			 uneBdd.seConnecter();
			 Statement unStat = uneBdd.getMaConnexion().createStatement();
			 ResultSet desRes = unStat.executeQuery(requete);
			 while (desRes.next()) {
				 Materiel unMateriel = new Materiel(
							 desRes.getInt("idMateriel"), 
							 desRes.getString("designation"),
							 desRes.getString("marque"),
							 desRes.getString("modele"), 
							 desRes.getFloat("prixachat"), 
							 desRes.getString("date_achat"), 
							 desRes.getInt("idUser")
						 );
			    lesMateriels.add(unMateriel);
			 }
			 unStat.close();
			 uneBdd.seDeConnecter();
		 }
		 catch (SQLException exp) {
			 System.out.println("Erreur de requete : " +requete);
		 }
		return lesMateriels; 
	}
	
	
	
	public static  Materiel selectWhereMateriel (int idmateriel){
		 
		String requete = "select * from materiel where idmateriel =" + idmateriel; 
		Materiel unMateriel = null; 
		 try {
			 uneBdd.seConnecter();
			 Statement unStat = uneBdd.getMaConnexion().createStatement();
			 ResultSet desRes = unStat.executeQuery(requete);
			 if (desRes.next()) {
				  unMateriel = new Materiel(
						  	 desRes.getInt("idMateriel"), 
							 desRes.getString("designation"),
							 desRes.getString("marque"),
							 desRes.getString("modele"), 
							 desRes.getFloat("prixachat"), 
							 desRes.getString("date_achat"), 
							 desRes.getInt("idUser")
						 );
			 }
			 unStat.close();
			 uneBdd.seDeConnecter();
		 }
		 catch (SQLException exp) {
			 System.out.println("Erreur de requete : " +requete);
		 }
		return unMateriel; 
	}
	
	public static  Materiel selectWhereMateriel (String designation, String dateAchat){
		 
		String requete = "select * from materiel where designation='"+designation
				+ "' and  dateAchat ='"+dateAchat+"';";
		Materiel unMateriel = null; 
		 try {
			 uneBdd.seConnecter();
			 Statement unStat = uneBdd.getMaConnexion().createStatement();
			 ResultSet desRes = unStat.executeQuery(requete);
			 if (desRes.next()) {
				  unMateriel = new Materiel(
						  	 desRes.getInt("idMateriel"), 
							 desRes.getString("designation"),
							 desRes.getString("marque"),
							 desRes.getString("modele"), 
							 desRes.getFloat("prixachat"), 
							 desRes.getString("date_achat"), 
							 desRes.getInt("idUser")
						 );
			 }
			 unStat.close();
			 uneBdd.seDeConnecter();
		 }
		 catch (SQLException exp) {
			 System.out.println("Erreur de requete : " +requete);
		 }
		return unMateriel; 
	}
	
	
	
	public static void deleteMateriel(int idMateriel) {
		 String requete = "delete from materiel where idmateriel = " + idMateriel + ";";
		 try {
			 uneBdd.seConnecter();
			 Statement unStat = uneBdd.getMaConnexion().createStatement();
			 unStat.execute(requete); 
			 unStat.close();
			 uneBdd.seDeConnecter();
		 }
		 catch (SQLException exp) {
			 System.out.println("Erreur de requete : " +requete);
		 }
	}
	
	
	
	
	public static void updateMateriel(Materiel unMateriel) {
		 String requete = "update materiel set designation =  '"+unMateriel.getDesignation()
		 	+ "', marque = '"+unMateriel.getMarque()+"', modele = '"+ unMateriel.getModele()
		 	+ "', date_achat = '"+unMateriel.getDateAchat()+"', prixachat = '"+ unMateriel.getPrixAchat ()
		 	+"' where idmateriel = " + unMateriel.getIdmateriel() + ";";
		 try {
			 uneBdd.seConnecter();
			 Statement unStat = uneBdd.getMaConnexion().createStatement();
			 unStat.execute(requete); 
			 unStat.close();
			 uneBdd.seDeConnecter();
		 }
		 catch (SQLException exp) {
			 System.out.println("Erreur de requete : " +requete);
		 }
	}



	public static void insertIntervention(Intervention uneIntervention) {
		String requete = "insert into intervention values (null, '" + uneIntervention.getDate_inter() + "','"
				+ uneIntervention.getDuree() + "','" + uneIntervention.getStatut() + "', '"
				+ uneIntervention.getDescription() + "', '" + uneIntervention.getIdMateriel() 
				+ "','" + uneIntervention.getIdTechnicien() + "', '" + uneIntervention.getPrix() + "');";
		
		try {
			 uneBdd.seConnecter();
			 Statement unStat = uneBdd.getMaConnexion().createStatement();
			 unStat.execute(requete); 
			 unStat.close();
			 uneBdd.seDeConnecter();
		 }
		 catch (SQLException exp) {
			 System.out.println("Erreur de requete : " +requete);
		 }
		
	}
	
	
	public static void insertTechnicien(Technicien unTechnicien) {
		 String requete = "call insererTechnicien('"+unTechnicien.getNom()
		 + "','"+unTechnicien.getPrenom()+"','"+ unTechnicien.getAdresse()
		 	+ "','"+unTechnicien.getTelephone()+"','"+ unTechnicien.getEmail()
		 	+ "','"+unTechnicien.getMdp()+ "','"+ unTechnicien.getSpecialite()
		 	+ "','"+unTechnicien.getPoste()+ "') ;";
		 try {
			 uneBdd.seConnecter();
			 Statement unStat = uneBdd.getMaConnexion().createStatement();
			 unStat.execute(requete); 
			 unStat.close();
			 uneBdd.seDeConnecter();
			 System.out.println(requete);
		 }
		 catch (SQLException exp) {
			 System.out.println("Erreur de requete : " +requete);
		 }
	}
	
	
	
	public static ArrayList<Client> selectAllClients(String filtre)
	{
		ArrayList<Client> lesClients = new ArrayList<Client>(); 		
		String requete =""; 
		if (filtre.equals("")) {
			requete = "select * from fullclient ;"; 
		}else {
			requete = "select * from fullclient where nom like '%"+filtre
					+"%' or prenom like '%"+filtre 
					+"%' or adresse like '%"+filtre
					+"%' or telephone like '%"+filtre
					+"%' or email like '%"+filtre 
					+"%' or type like '%"+filtre 
					+"%' or fidelite like '%"+filtre +"%' ; ";
		}
		
		try {
			uneBdd.seConnecter();
			//création d'un curseur pour exécuter la requete 
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			//execution de la requete et récuperation d'un resultat 
			ResultSet desRes = unStat.executeQuery(requete); 
			//s'il y a un resultat, on récupere les champs 
			while (desRes.next()) {
				Client unClient = new Client (
						desRes.getInt("idUser"),   
						desRes.getString("nom"), 
						desRes.getString("prenom"), 
						desRes.getString("adresse"), 
						desRes.getString("telephone"), 
						desRes.getString("email"),
						desRes.getString("mdp"),
						desRes.getString("type"),
						desRes.getString("fidelite")
						);
				lesClients.add(unClient);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " +requete);
		}
		
		return lesClients; 
	}
	
	
	
	
	
	
	public static Client selectWhereClient(String filtre)
	{
		String requete =""; 
		Client unClient = null;

			requete = "select * from fullclient where idUser = "+filtre +" ; ";
		
		try {
			uneBdd.seConnecter();
			//création d'un curseur pour exécuter la requete 
			Statement unStat = uneBdd.getMaConnexion().createStatement(); 
			//execution de la requete et récuperation d'un resultat 
			ResultSet desRes = unStat.executeQuery(requete); 
			//s'il y a un resultat, on récupere les champs 
			while (desRes.next()) {
				unClient = new Client (
						desRes.getInt("idUser"),   
						desRes.getString("nom"), 
						desRes.getString("prenom"), 
						desRes.getString("adresse"), 
						desRes.getString("telephone"), 
						desRes.getString("email"),
						desRes.getString("mdp"),
						desRes.getString("type"),
						desRes.getString("fidelite")
						);
			}
			unStat.close();
			uneBdd.seDeConnecter();
		}
		catch (SQLException exp) {
			System.out.println("Erreur de requete : " +requete);
		}
		
		return unClient; 
	}
	
	
	
	
	


	public static void insererClient(Client unClient) {
		
		String requete = "call insererClient('"+unClient.getNom()
		 + "','"+unClient.getPrenom()+"','"+ unClient.getAdresse()
		 	+ "','"+unClient.getTelephone()+"','"+ unClient.getEmail()
		 	+ "','"+unClient.getMdp()+ "','"+ unClient.getType()
		 	+ "','"+unClient.getFidelite()+ "') ;";
		 try {
			 uneBdd.seConnecter();
			 Statement unStat = uneBdd.getMaConnexion().createStatement();
			 unStat.execute(requete); 
			 unStat.close();
			 uneBdd.seDeConnecter();
			 System.out.println(requete);
		 }
		 catch (SQLException exp) {
			 System.out.println("Erreur de requete : " +requete);
		 }
		
	}


	public static void updateClient(Client unClient) {
		
		
String requete = "";
		
		if (unClient.getMdp() == "")
		{
			requete = "Call modifyClient(" + unClient.getIdClient() + ", '"+unClient.getNom()
		 	+"', '"+unClient.getPrenom()
		 	+"', '"+unClient.getAdresse()
		 	+"', '"+unClient.getTelephone()
		 	+"', '"+unClient.getEmail()
		 	+"', '"+unClient.getMdp()
		 	+"', '"+unClient.getType()
		 	+"', '"+unClient.getFidelite() + "');";
		}else
		{
			requete = "Call modifyClientNoMdp(" + unClient.getIdClient() + ", '"+unClient.getNom()
		 	+"', '"+unClient.getPrenom()
		 	+"', '"+unClient.getAdresse()
		 	+"', '"+unClient.getTelephone()
		 	+"', '"+unClient.getEmail()
		 	+"', '"+unClient.getType()
		 	+"', '"+unClient.getFidelite() + "');";
		}
		
		
		
	 try {
		 uneBdd.seConnecter();
		 Statement unStat = uneBdd.getMaConnexion().createStatement();
		 unStat.execute(requete); 
		 unStat.close();
		 uneBdd.seDeConnecter();
	 }
	 catch (SQLException exp) {
		 System.out.println("Erreur de requete : " +requete);
	 }
		
	}
	
	
	public static void deleteIntervention (int idIntervention)
	{
		String requete = "delete from intervention where idIntervention = " + idIntervention + ";";
		 try {
			 uneBdd.seConnecter();
			 Statement unStat = uneBdd.getMaConnexion().createStatement();
			 unStat.execute(requete); 
			 unStat.close();
			 uneBdd.seDeConnecter();
		 }
		 catch (SQLException exp) {
			 System.out.println("Erreur de requete : " +requete);
		 }
	}
	
	
public static void updateIntervention(Intervention uneIntervention) {
		
		String requete = "update intervention set date_inter='"+uneIntervention.getDate_inter()
	 	+"', duree = '"+uneIntervention.getDuree()
	 	+"', statut = '"+uneIntervention.getStatut()
	 	+"', description = '"+uneIntervention.getDescription()
	 	+"', idMateriel = '"+uneIntervention.getIdMateriel()
	 	+"', idtechnicien = '"+uneIntervention.getIdTechnicien()
	 	+"', prix = '"+uneIntervention.getPrix()
	 	+"' where  idIntervention = "+uneIntervention.getIdinter()+";";
	 try {
		 uneBdd.seConnecter();
		 Statement unStat = uneBdd.getMaConnexion().createStatement();
		 unStat.execute(requete); 
		 unStat.close();
		 uneBdd.seDeConnecter();
	 }
	 catch (SQLException exp) {
		 System.out.println("Erreur de requete : " +requete);
	 }
		
	}
	
}
















