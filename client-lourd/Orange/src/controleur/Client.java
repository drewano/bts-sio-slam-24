package controleur;

public class Client {
	private int idClient; 
	private String nom, prenom, adresse, telephone, email, mdp, type, fidelite;
	
	public Client(int idClient, String nom, String prenom, String adresse, String telephone, String email, String mdp, String type, String fidelite) {
		super();
		this.idClient = idClient;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.telephone = telephone;
		this.email = email;
		this.mdp = mdp;
		this.type = type;
		this.fidelite = fidelite;
	} 
	
	public Client( String nom, String prenom, String adresse, String telephone, String email, String mdp, String type, String fidelite) {
		super();
		this.idClient = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.telephone = telephone;
		this.email = email;
		this.mdp = mdp;
		this.type = type;
		this.fidelite = fidelite;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFidelite() {
		return fidelite;
	}

	public void setFidelite(String fidelite) {
		this.fidelite = fidelite;
	}
	
	

}