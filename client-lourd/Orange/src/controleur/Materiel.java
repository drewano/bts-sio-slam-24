package controleur;

public class Materiel {
	private int idmateriel, idUser ;
	private String designation, dateAchat, categorie, marque, modele ; 
	private float prixAchat; 
	
	public Materiel(int idmateriel, String designation, String marque, String modele, float prixachat, String dateAchat, int idUser) {
		super();
		this.idmateriel = idmateriel;
		this.designation = designation;
		this.marque = marque;
		this.modele = modele;
		this.dateAchat = dateAchat;
		this.prixAchat = prixachat;
		this.idUser = idUser;
	} 
	
	public Materiel(String designation, String marque, String modele, float prixachat, String dateAchat, int idUser) {
		super();
		this.idmateriel = 0;
		this.designation = designation;
		this.marque = marque;
		this.modele = modele;
		this.dateAchat = dateAchat;
		this.prixAchat = prixachat;
		this.idUser = idUser;
	}

	
	
	
	
	public int getIdmateriel() {
		return idmateriel;
	}

	public void setIdmateriel(int idmateriel) {
		this.idmateriel = idmateriel;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(String dateAchat) {
		this.dateAchat = dateAchat;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public float getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(float prixAchat) {
		this.prixAchat = prixAchat;
	}


	
}
