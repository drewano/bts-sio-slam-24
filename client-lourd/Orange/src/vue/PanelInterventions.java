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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controleur.Controleur;
import controleur.Intervention;
import controleur.Materiel;
import controleur.Tableau;
import controleur.Technicien;

public class PanelInterventions extends PanelPrincipal implements ActionListener {
	
	private JTextArea txtDescription = new JTextArea();
	private JTextField txtDateInter = new JTextField();
	private JTextField txtDuree = new JTextField();
	private JTextField txtStatut = new JTextField();
	private JComboBox<String> txtIdTechnicien = new JComboBox<String>();
	private JComboBox<String> txtIdMateriel = new JComboBox<String>();
	private JTextField txtPrix = new JTextField();
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JTable tableInterventions ; 
	private JScrollPane uneScroll ; 
	private Tableau unTableau ;
	
	private JPanel panelRech = new JPanel();
	private JTextField txtFiltre = new JTextField(); 
	private JButton btFiltre = new JButton("Filtrer");
	
	private JPanel panelForm = new JPanel();
	
	private JLabel lbInterventions;
	
	

	public PanelInterventions() {
		super(new Color ( 181, 135, 79  ));
		
		this.panelForm.setBounds(50, 50, 300, 250);
		this.panelForm.setBackground(new Color ( 181, 135, 79  ));
		this.panelForm.setLayout(new GridLayout(8,2));
		this.panelForm.add(new JLabel("Date Intervention : ")); 
		this.panelForm.add(this.txtDescription); 
		this.panelForm.add(new JLabel("Date Inter : ")); 
		this.panelForm.add(this.txtDateInter);
		this.panelForm.add(new JLabel("Duree : ")); 
		this.panelForm.add(this.txtDuree);
		this.panelForm.add(new JLabel("Statut : ")); 
		this.panelForm.add(this.txtStatut);
		this.panelForm.add(new JLabel("Le Technicien : ")); 
		this.panelForm.add(this.txtIdTechnicien);
		this.panelForm.add(new JLabel("Le Matériel : ")); 
		this.panelForm.add(this.txtIdMateriel);
		this.panelForm.add(new JLabel("Prix : "));
		this.panelForm.add(this.txtPrix);
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btEnregistrer);
		this.add(this.panelForm); 
		this.panelForm.setVisible(true);
		
		
		
		String entetes [] = {"ID Intervention", "Date Intervention", "Duree", "Statut", "Description", "Id Materiel", "ID Technicien", "Prix"};
		this.unTableau = new Tableau (entetes, this.obtenirDonnees(""));
		this.tableInterventions = new JTable(this.unTableau); 
		this.uneScroll = new JScrollPane(this.tableInterventions); 
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
		
		
		//Nombre de matériels dans la table
		int nbInterventions = this.unTableau.getRowCount();
		lbInterventions = new JLabel("Nombre de matériels disponibles : " + nbInterventions);
		lbInterventions.setBounds(100, 440, 300, 20);
		this.add(lbInterventions);
		
		
		//rendre les boutons écoutables
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btFiltre.addActionListener(this);
		
		
			this.tableInterventions.addMouseListener(new MouseListener() {
			
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
				int idIntervention = 0;
				if (e.getClickCount() >= 2)
				{
					numLigne = tableInterventions.getSelectedRow();
					idIntervention = Integer.parseInt(tableInterventions.getValueAt(numLigne, 0).toString());
					int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce matériel", "Supression matérielle", JOptionPane.YES_NO_OPTION);
					
					if (reponse == 0)
					{
						//suppression dans la BDD
						Controleur.deleteIntervention(idIntervention);
						//supression dans l'affichage
						unTableau.supprimerLigne(numLigne);
						lbInterventions.setText("Nombre de matériels disponibles : " + unTableau.getRowCount());
					}
				}else
				{
					numLigne = tableInterventions.getSelectedRow();
					idIntervention = Integer.parseInt(tableInterventions.getValueAt(numLigne, 0).toString());
					String dateInter = tableInterventions.getValueAt(numLigne, 1).toString();
					String duree = tableInterventions.getValueAt(numLigne, 2).toString();
					String statut = tableInterventions.getValueAt(numLigne, 3).toString();
					String description = tableInterventions.getValueAt(numLigne, 4).toString();
					String idMateriel = tableInterventions.getValueAt(numLigne, 5).toString();
					String idTechnicien = tableInterventions.getValueAt(numLigne, 6).toString();
					String prix = tableInterventions.getValueAt(numLigne, 7).toString();
					
					//remplissage du formulaire
					txtDateInter.setText(dateInter);
					txtDuree.setText(duree);
					txtStatut.setText(statut);
					txtDescription.setText(description);

					txtPrix.setText(prix);
					btEnregistrer.setText("Modifier");
				}
				
			}
		});
		
		
	}
	
	
	
	public void remplirCBX()
	{
		ArrayList<Technicien> lesTechniciens = Controleur.selectAllTechniciens("");
		for (Technicien unTechnicien : lesTechniciens)
		{
			this.txtIdTechnicien.addItem(unTechnicien.getIdtechnicien() + "-" + unTechnicien.getSpecialite());
		}
		
		ArrayList<Materiel> lesMateriels = Controleur.selectAllMateriels("");
		for (Materiel unMateriel : lesMateriels)
		{
			this.txtIdMateriel.addItem(unMateriel.getIdmateriel() + "-" + unMateriel.getDesignation());
		}
	}
	
	
	public Object [][] obtenirDonnees (String filtre){
		ArrayList<Intervention> lesInterventions = Controleur.selectAllInterventions(filtre); 
		Object [][] matrice = new Object[lesInterventions.size()][8];
		int i = 0; 
		for (Intervention uneIntervention : lesInterventions) {
			matrice [i][0] = uneIntervention.getIdinter(); 
			matrice [i][1] = uneIntervention.getDate_inter();
			matrice [i][2] = uneIntervention.getDuree(); 
			matrice [i][3] = uneIntervention.getStatut(); 
			matrice [i][4] = uneIntervention.getDescription(); 
			matrice [i][5] = uneIntervention.getIdMateriel(); 
			matrice [i][6] = uneIntervention.getIdTechnicien();
			matrice [i][7] = uneIntervention.getPrix();
			i++;
		}
		return matrice;
	}
	
	
	
	public void viderChamps()
	{
		txtDateInter.setText("");
		txtDuree.setText("");
		txtStatut.setText("");
		txtDescription.setText("");
		txtPrix.setText("");
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler)
		{
			this.txtDescription.setText("");
			this.txtDateInter.setText("");
			this.txtDuree.setText("");
			this.txtStatut.setText("");
		}
		else if (e.getSource()== this.btFiltre)
		{
			String filtre = this.txtFiltre.getText(); 
			Object matrice [][ ] = this.obtenirDonnees(filtre); 
			this.unTableau.setDonnees(matrice);
		}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer"))
		{
			String description = this.txtDescription.getText();
			String dateInter = this.txtDateInter.getText();
			float duree = Float.valueOf(this.txtDuree.getText());
			String statut = this.txtStatut.getText();
			float prix = Float.valueOf(this.txtPrix.getText());
			
			
			//récupération des ID :
			String chaine = this.txtIdTechnicien.getSelectedItem().toString();
			String tab [] = chaine.split("-");
			int idTechnicien = Integer.parseInt(tab[0]);
			
			chaine = this.txtIdTechnicien.getSelectedItem().toString();
			tab = chaine.split("-");
			int idMateriel = Integer.parseInt(tab[0]);
			
			//instancier une intervention
			Intervention uneIntervention = new Intervention(dateInter, duree, statut, description, idTechnicien, idMateriel, prix);
			
			//On l'insère dans la bdd
			Controleur.insertIntervention(uneIntervention);
			JOptionPane.showMessageDialog(this, "Insertion réussie de l'intervention");
			this.txtDescription.setText("");
			this.txtDateInter.setText("");
			this.txtDuree.setText("");
			this.txtStatut.setText("");
			this.txtPrix.setText("");
			
		}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier"))
		{
			String description = this.txtDescription.getText();
			String dateInter = this.txtDateInter.getText();
			float duree = Float.valueOf(this.txtDuree.getText());
			String statut = this.txtStatut.getText();
			float prix = Float.valueOf(this.txtPrix.getText());
			
			try {
				prix = Float.parseFloat(this.txtPrix.getText()); 
			}
			catch (NumberFormatException exp) {
				JOptionPane.showMessageDialog(this, "Erreur format du prix");
			}
			
			int numLigne = 0;
			int idIntervention = 0;
			numLigne = tableInterventions.getSelectedRow();
			idIntervention = Integer.parseInt(tableInterventions.getValueAt(numLigne, 0).toString());
			int idTechnicien = Integer.parseInt(tableInterventions.getValueAt(numLigne, 5).toString());
			int idMateriel = Integer.parseInt(tableInterventions.getValueAt(numLigne, 6).toString());
			
			//Instanciation d'un materiel
			Intervention uneIntervention = new Intervention(idIntervention, dateInter, duree, statut, description, idTechnicien, idMateriel, prix);
			//Modification dans la base de données
			Controleur.updateIntervention(uneIntervention);
			//Modification dans l'affichage
			Object ligne [] = {idIntervention, dateInter, duree, statut, description, idTechnicien, idMateriel, prix};
			this.unTableau.modifierLigne(numLigne, ligne);
			JOptionPane.showMessageDialog(this, "Modification effectué");
			this.viderChamps();
			this.btEnregistrer.setText("Enregistrer");
		
		}
		
	}

}
