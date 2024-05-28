package controleur;

public class Intervention {
	private int idinter; 
	private String date_inter, statut, description;
	private float duree, prix;
	private int idTechnicien, idMateriel;
	
	public Intervention(int idinter, String date_inter, float duree, String statut, String description, int idMateriel, int idTechnicien, float prix) {
		super();
		this.idinter = idinter;
		this.date_inter = date_inter;
		this.duree = duree;
		this.statut = statut;
		this.description = description;
		this.idMateriel = idMateriel;
		this.idTechnicien = idTechnicien;
		this.prix = prix;
	}
	
	
	public Intervention( String date_inter, float duree, String statut, String description, int idMateriel, int idTechnicien, float prix) {
		super();
		this.idinter = 0;
		this.date_inter = date_inter;
		this.duree = duree;
		this.statut = statut;
		this.description = description;
		this.idMateriel = idMateriel;
		this.idTechnicien = idTechnicien;
		this.prix = prix;
	}
	

	
	public float getPrix() {
		return prix;
	}


	public void setPrix(float prix) {
		this.prix = prix;
	}

	public int getIdinter() {
		return idinter;
	}
	
	public void setIdinter(int idinter) {
		this.idinter = idinter;
	}
	
	public String getDate_inter() {
		return date_inter;
	}
	
	public void setDate_inter(String date_inter) {
		this.date_inter = date_inter;
	}
	
	public String getStatut() {
		return statut;
	}
	
	public void setStatut(String statut) {
		this.statut = statut;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public float getDuree() {
		return duree;
	}
	
	public void setDuree(float duree) {
		this.duree = duree;
	}
	
	public int getIdTechnicien() {
		return idTechnicien;
	}
	
	public void setIdTechnicien(int idTechnicien) {
		this.idTechnicien = idTechnicien;
	}
	
	public int getIdMateriel() {
		return idMateriel;
	}
	
	public void setIdMateriel(int idMateriel) {
		this.idMateriel = idMateriel;
	}
}
