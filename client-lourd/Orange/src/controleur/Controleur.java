package controleur;

import java.util.ArrayList;

import modele.Modele;

public class Controleur {

	public static Technicien selectWhereTechnicien (String email, String mdp) {
		//hasher le mdp
		String hashKey = Modele.hashKey();
		
		return Modele.selectWhereTechnicien(email, mdp, hashKey);
	}
	
	public static ArrayList<Technicien> selectAllTechniciens(String filtre){
		return Modele.selectAllTechniciens(filtre); 
	}
	
	public static ArrayList<Intervention> selectAllInterventions(String filtre)
	{
		return Modele.selectAllInterventions(filtre);
	}
	
	public static void updateTechnicien (Technicien unTechnicien)
	{
		Modele.updateTechnicien (unTechnicien); 
	}
	public static void insertMateriel (Materiel unMateriel)
	{
		Modele.insertMateriel (unMateriel); 
	}
	
	public static ArrayList<Materiel> selectAllMateriels(String filtre){
		return Modele.selectAllMateriels(filtre); 
	}
	
	public static Materiel selectWhereMateriel (int idmateriel) {
		return Modele.selectWhereMateriel(idmateriel);
	}
	
	public static Materiel selectWhereMateriel (String designation, String dateAchat) {
		return Modele.selectWhereMateriel(designation, dateAchat);
	}
	
	public static void deleteMateriel (int idMateriel)
	{
		Modele.deleteMateriel (idMateriel); 
	}
	
	public static void updateMateriel (Materiel unMateriel)
	{
		Modele.updateMateriel (unMateriel); 
	}
	
	public static void insertIntervention (Intervention uneIntervention)
	{
		Modele.insertIntervention(uneIntervention);
	}

	public static ArrayList<ViewTech> selectAllViewTechs() {

		return Modele.selectAllViewTechs();
	}
	
	public static void insererTechnicien(Technicien unTechnicien)
	{
		Modele.insertTechnicien(unTechnicien);
	}
	
	public static ArrayList<Client> selectAllClients(String filtre)
	{
		return Modele.selectAllClients(filtre);
	}
	
	public static Client selectWhereClient(String filtre)
	{
		return Modele.selectWhereClient(filtre);
	}

	public static void insererClient(Client unClient) {
		Modele.insererClient(unClient);
	}

	public static void updateClient(Client unClient) {
		Modele.updateClient(unClient);
	}
	
	public static void deleteIntervention(int idIntervention)
	{
		Modele.deleteIntervention(idIntervention);
	}
	
	public static void updateIntervention(Intervention uneIntervention)
	{
		Modele.updateIntervention(uneIntervention);
	}
}













