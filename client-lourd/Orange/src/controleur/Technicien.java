package controleur;

public class Technicien {
	private int idtechnicien; 
	private String nom, prenom, adresse, telephone, email, mdp, specialite, dateEntree, poste;
	
	public Technicien(int idtechnicien, String nom, String prenom, String adresse, String telephone, String email, String mdp, String specialite, String dateEntree, String poste) {
		super();
		this.idtechnicien = idtechnicien;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.telephone = telephone;
		this.email = email;
		this.mdp = mdp;
		this.specialite = specialite;
		this.dateEntree = dateEntree;
		this.poste = poste;
	} 
	
	public Technicien( String nom, String prenom, String adresse, String telephone, String email, String mdp, String specialite, String dateEntree, String poste) {
		super();
		this.idtechnicien = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.telephone = telephone;
		this.email = email;
		this.mdp = mdp;
		this.specialite = specialite;
		this.dateEntree = dateEntree;
		this.poste = poste;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	public String getDateEntree() {
		return dateEntree;
	}

	public void setDateEntree(String dateEntree) {
		this.dateEntree = dateEntree;
	}

	public String getPoste() {
		return poste;
	}

	public void setPoste(String poste) {
		this.poste = poste;
	}

	public int getIdtechnicien() {
		return idtechnicien;
	}

	public void setIdtechnicien(int idtechnicien) {
		this.idtechnicien = idtechnicien;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	} 
	
	
}
