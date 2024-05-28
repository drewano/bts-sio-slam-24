package vue;

 

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Controleur;
import controleur.Materiel;
import controleur.Tableau;
import controleur.Technicien;
import controleur.Client;

public class PanelMateriels extends PanelPrincipal implements ActionListener
{

	private JPanel panelForm = new JPanel(); 
	private JTextField txtDesignation = new JTextField(); 
	private JTextField txtMarque = new JTextField(); 
	private JTextField txtModele = new JTextField(); 
	private JTextField txtDateAchat = new JTextField();
	private JTextField txtPrix = new JTextField();
	private JComboBox<String> txtIdClient = new JComboBox<String>();
	
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btEnregistrer= new JButton("Enregistrer");
	
	private JTable tableMateriels ; 
	private JScrollPane uneScroll ; 
	private Tableau unTableau ; 
	
	private JPanel panelRech = new JPanel();
	private JTextField txtFiltre = new JTextField(); 
	private JButton btFiltre = new JButton("Filtrer");
	
	private JLabel lbMateriels;
	
	public PanelMateriels( ) {
		super(new Color ( 181, 135, 79  ));

		//placement du panel formulaire
		this.panelForm.setBounds(50, 50, 300, 250);
		this.panelForm.setBackground(new Color ( 181, 135, 79  ));
		this.panelForm.setLayout(new GridLayout(8,2));
		this.panelForm.add(new JLabel("Désignation : ")); 
		this.panelForm.add(this.txtDesignation); 
		this.panelForm.add(new JLabel("Marque : ")); 
		this.panelForm.add(this.txtMarque); 
		this.panelForm.add(new JLabel("Modèle : ")); 
		this.panelForm.add(this.txtModele); 
		this.panelForm.add(new JLabel("Date Achat : ")); 
		this.panelForm.add(this.txtDateAchat);
		this.panelForm.add(new JLabel("Prix Achat : ")); 
		this.panelForm.add(this.txtPrix);
		this.panelForm.add(new JLabel("Id user : "));
		this.panelForm.add(this.txtIdClient);
		
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btEnregistrer);
		this.add(this.panelForm); 
		this.panelForm.setVisible(true);
		
		//construction de la Jtable des materiels 
		String entetes [] = {"ID Matériel", "Désignation", "Marque", "Modèle", "Prix d'achat", "Date d'achat", "ID User", "Nom", "Prénom"};
		this.unTableau = new Tableau (entetes, this.obtenirDonnees(""));
		this.tableMateriels = new JTable(this.unTableau); 
		this.uneScroll = new JScrollPane(this.tableMateriels); 
		this.uneScroll.setBounds(400, 80,400, 220);
		this.add(this.uneScroll); 
		
		//placement du panel filtre 
		this.panelRech.setBounds(450, 10, 300, 20);
		this.panelRech.setBackground(new Color ( 181, 135, 79  ));
		this.panelRech.setLayout(new GridLayout(1,3));
		this.panelRech.add(new JLabel("Filtrer par : ")); 
		this.panelRech.add(this.txtFiltre);
		this.panelRech.add(this.btFiltre);
		this.panelRech.setVisible(true);
		this.add(this.panelRech);
		
		this.remplirCBX();
		
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btFiltre.addActionListener(this);
		
		//interdiction de déplacement des colonnes
		this.tableMateriels.getTableHeader().setReorderingAllowed(false);
		
		//Ajout d'un mouseListener pour suppression et modification
		this.tableMateriels.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int numLigne = 0;
				int idMateriel = 0;
				if (e.getClickCount() >= 2)
				{
					numLigne = tableMateriels.getSelectedRow();
					idMateriel = Integer.parseInt(tableMateriels.getValueAt(numLigne, 0).toString());
					int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce matériel", "Supression matérielle", JOptionPane.YES_NO_OPTION);
					
					if (reponse == 0)
					{
						//suppression dans la BDD
						Controleur.deleteMateriel(idMateriel);
						//supression dans l'affichage
						unTableau.supprimerLigne(numLigne);
						lbMateriels.setText("Nombre de matériels disponibles : " + unTableau.getRowCount());
					}
				}else
				{
					numLigne = tableMateriels.getSelectedRow();
					idMateriel = Integer.parseInt(tableMateriels.getValueAt(numLigne, 0).toString());
					String designation = tableMateriels.getValueAt(numLigne, 1).toString();
					String marque = tableMateriels.getValueAt(numLigne, 2).toString();
					String modele = tableMateriels.getValueAt(numLigne, 3).toString();
					String prixAchat = tableMateriels.getValueAt(numLigne, 4).toString();
					String dateAchat = tableMateriels.getValueAt(numLigne, 5).toString();
					
					//remplissage du formulaire
					txtDesignation.setText(designation);
					txtMarque.setText(marque);
					txtModele.setText(modele);
					txtDateAchat.setText(dateAchat);
					txtPrix.setText(prixAchat);
					btEnregistrer.setText("Modifier");
				}
				
			}
		});
		
		
		//Nombre de matériels dans la table
		int nbMateriels = this.unTableau.getRowCount();
		lbMateriels = new JLabel("Nombre de matériels disponibles : " + nbMateriels);
		lbMateriels.setBounds(100, 440, 300, 20);
		this.add(lbMateriels);
	}
	
	
	public void remplirCBX()
	{
		ArrayList<Client> lesClients = Controleur.selectAllClients("");
		for (Client unClient : lesClients)
		{
			this.txtIdClient.addItem(unClient.getIdClient() + " - " + unClient.getNom());
			System.out.println(this.txtIdClient);
		}
	}
	
	
	public Object [][] obtenirDonnees (String filtre){
		ArrayList<Materiel> lesMateriels = Controleur.selectAllMateriels(filtre);
		Object [][] matrice = new Object[lesMateriels.size()][9];
		int i = 0; 
		for (Materiel unMateriel : lesMateriels) {
			Client unClient = Controleur.selectWhereClient(String.valueOf(unMateriel.getIdUser()));
			matrice [i][0] = unMateriel.getIdmateriel(); 
			matrice [i][1] = unMateriel.getDesignation();
			matrice [i][2] = unMateriel.getMarque(); 
			matrice [i][3] = unMateriel.getModele(); 
			matrice [i][4] = unMateriel.getPrixAchat(); 
			matrice [i][5] = unMateriel.getDateAchat(); 
			matrice [i][6] = unMateriel.getIdUser();
			matrice [i][7] = unClient.getNom();
			matrice [i][8] = unClient.getPrenom();
			i++;
		}
		return matrice;
	}
	public void viderChamps () {
		
		this.txtDesignation.setText("");
		this.txtMarque.setText("");
		this.txtModele.setText("");
		this.txtDateAchat.setText("");
		this.txtPrix.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==this.btAnnuler) {
			this.viderChamps();
			this.btEnregistrer.setText("Enregistrer");
		}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
			boolean ok = true;
			String designation = this.txtDesignation.getText();
			String marque = this.txtMarque.getText();
			String modele = this.txtModele.getText();
			String dateAchat = this.txtDateAchat.getText(); 
			int idUser = Integer.parseInt(String.valueOf(this.txtIdClient.getSelectedItem().toString().charAt(0)));
			System.out.println(idUser);
			float prix = 0; 
			try {
				prix = Float.parseFloat(this.txtPrix.getText()); 
			}
			catch (NumberFormatException exp) {
				JOptionPane.showMessageDialog(this, "Erreur format du prix");
			}
			//on vérifie les données avant insertion dans la base 
			
			if (prix <= 0)
			{
				this.txtPrix.setBackground(Color.red);
				ok = false;
			}else
			{
				this.txtPrix.setBackground(Color.white);
			}
			
			
			if (ok)
			{
				//on enregistre le new materiel dans la base 
				Materiel unMateriel = new Materiel(designation, marque, modele, prix, dateAchat, idUser);
				Controleur.insertMateriel (unMateriel);
				Client unClient = Controleur.selectWhereClient(String.valueOf(unMateriel.getIdUser()));
				String nom = unClient.getNom();
				String prenom = unClient.getPrenom();
				
				
				
				JOptionPane.showMessageDialog(this, "Matériel inséré avec succés dans la BDD");
				//insertion dans l'affichage graphique 
				Object ligne[]= {unMateriel.getIdmateriel(), designation, marque, modele, prix, dateAchat, idUser, nom, prenom};
				this.unTableau.ajouterLigne(ligne);
				lbMateriels.setText("Nombre de matériels disponibles : " + unTableau.getRowCount());
				
				this.viderChamps();
			}
			
		}
		
		else if (e.getSource()== this.btFiltre)
		{
			String filtre = this.txtFiltre.getText(); 
			Object matrice [][ ] = this.obtenirDonnees(filtre); 
			this.unTableau.setDonnees(matrice);
		}
		
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier"))
		{
			String designation = this.txtDesignation.getText();
			String marque = this.txtMarque.getText();
			String modele = this.txtModele.getText();
			String dateAchat = this.txtDateAchat.getText(); 
			float prix = 0; 
			try {
				prix = Float.parseFloat(this.txtPrix.getText()); 
			}
			catch (NumberFormatException exp) {
				JOptionPane.showMessageDialog(this, "Erreur format du prix");
			}
			
			int numLigne = 0;
			int idMateriel = 0;
			numLigne = tableMateriels.getSelectedRow();
			idMateriel = Integer.parseInt(tableMateriels.getValueAt(numLigne, 0).toString());
			int idUser = Integer.parseInt(tableMateriels.getValueAt(numLigne, 6).toString());
			
			//Instanciation d'un materiel
			Materiel unMateriel = new Materiel(idMateriel, designation, marque, modele, prix, dateAchat, idUser);
			//Modification dans la base de données
			Controleur.updateMateriel(unMateriel);
			//Modification dans l'affichage
			Object ligne [] = {idMateriel, designation, marque, modele, prix, dateAchat, idUser};
			this.unTableau.modifierLigne(numLigne, ligne);
			JOptionPane.showMessageDialog(this, "Modification effectué");
			this.viderChamps();
			this.btEnregistrer.setText("Enregistrer");
		
		}
	}
}









