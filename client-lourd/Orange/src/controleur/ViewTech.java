package controleur;

public class ViewTech {
	
	private String nom, prenom, email;
	private int nbInterventions, totalPrix;
	
	
	
	
	public ViewTech(String nom, String prenom, String email, int nbInterventions, int totalPrix) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.nbInterventions = nbInterventions;
		this.totalPrix = totalPrix;
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




	public int getNbInterventions() {
		return nbInterventions;
	}




	public void setNbInterventions(int nbInterventions) {
		this.nbInterventions = nbInterventions;
	}




	public int getTotalPrix() {
		return totalPrix;
	}




	public void setTotalPrix(int totalPrix) {
		this.totalPrix = totalPrix;
	}

}