package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JButton;
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

public class PanelTechniciens extends PanelPrincipal implements ActionListener
{

	private JPanel panelForm = new JPanel(); 
	private JTextField txtNom = new JTextField(); 
	private JTextField txtPrenom = new JTextField(); 
	private JTextField txtAdresse = new JTextField(); 
	private JTextField txtTelephone = new JTextField();
	private JTextField txtEmail = new JTextField();
	private JTextField txtMdp = new JTextField();
	private JTextField txtSpecialite = new JTextField();
	private JTextField txtDateEntree = new JTextField();
	private JTextField txtPoste = new JTextField();
	
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btEnregistrer= new JButton("Enregistrer");
	
	private JTable tableTechnicien ; 
	private JScrollPane uneScroll ; 
	private Tableau unTableau ; 
	
	private JPanel panelRech = new JPanel();
	private JTextField txtFiltre = new JTextField(); 
	private JButton btFiltre = new JButton("Filtrer");
	
	private JLabel lbMateriels;
	
	
	public PanelTechniciens( ) {
		super(new Color ( 181, 135, 79  ));
		
		//placement du panel formulaire
				this.panelForm.setBounds(50, 50, 300, 250);
				this.panelForm.setBackground(new Color ( 181, 135, 79  ));
				this.panelForm.setLayout(new GridLayout(10,2));
				this.panelForm.add(new JLabel("nom : ")); 
				this.panelForm.add(this.txtNom); 
				this.panelForm.add(new JLabel("Prenom : ")); 
				this.panelForm.add(this.txtPrenom); 
				this.panelForm.add(new JLabel("Adresse : ")); 
				this.panelForm.add(this.txtAdresse); 
				this.panelForm.add(new JLabel("Telephone : ")); 
				this.panelForm.add(this.txtTelephone);
				this.panelForm.add(new JLabel("Email : ")); 
				this.panelForm.add(this.txtEmail);
				this.panelForm.add(new JLabel("mdp : "));
				this.panelForm.add(this.txtMdp);
				this.panelForm.add(new JLabel("Spécialité : "));
				this.panelForm.add(this.txtSpecialite);
				this.panelForm.add(new JLabel("dateEntree : "));
				this.panelForm.add(this.txtDateEntree);
				this.panelForm.add(new JLabel("Poste : "));
				this.panelForm.add(this.txtPoste);
				this.panelForm.add(this.btAnnuler); 
				this.panelForm.add(this.btEnregistrer);
				this.add(this.panelForm); 
				this.panelForm.setVisible(true);
				
				//construction de la Jtable des materiels 
				String entetes [] = {"ID Technicien", "Nom", "Prenom", "Adresse", "Telephone", "Email", "Spécialité", "Date Entree", "Poste"};
				this.unTableau = new Tableau (entetes, this.obtenirDonnees(""));
				this.tableTechnicien = new JTable(this.unTableau); 
				this.uneScroll = new JScrollPane(this.tableTechnicien); 
				this.uneScroll.setBounds(400, 80,500, 220);
				this.add(this.uneScroll); 
				
				//placement du panel filtre 
				this.panelRech.setBounds(450, 10, 800, 20);
				this.panelRech.setBackground(new Color (181, 135, 79));
				this.panelRech.setLayout(new GridLayout(1,3));
				this.panelRech.add(new JLabel("Filtrer par : ")); 
				this.panelRech.add(this.txtFiltre);
				this.panelRech.add(this.btFiltre);
				this.panelRech.setVisible(true);
				this.add(this.panelRech); 
				
				//rendre les boutons ecoutables 
				this.btAnnuler.addActionListener(this);
				this.btEnregistrer.addActionListener(this);
				this.btFiltre.addActionListener(this);
				
				this.tableTechnicien.getTableHeader().setReorderingAllowed(false);
				
				
				
				this.tableTechnicien.addMouseListener(new MouseListener() {
					
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
							numLigne = tableTechnicien.getSelectedRow();
							idMateriel = Integer.parseInt(tableTechnicien.getValueAt(numLigne, 0).toString());
							int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce technicien", "Supression matérielle", JOptionPane.YES_NO_OPTION);
							
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
							numLigne = tableTechnicien.getSelectedRow();
							idMateriel = Integer.parseInt(tableTechnicien.getValueAt(numLigne, 0).toString());
							String nom = tableTechnicien.getValueAt(numLigne, 1).toString();
							String prenom = tableTechnicien.getValueAt(numLigne, 2).toString();
							String adresse = tableTechnicien.getValueAt(numLigne, 3).toString();
							String telephone = tableTechnicien.getValueAt(numLigne, 4).toString();
							String email = tableTechnicien.getValueAt(numLigne, 5).toString();
							String mdp = "";
							String specialite = tableTechnicien.getValueAt(numLigne, 6).toString();
							String dateEntree = tableTechnicien.getValueAt(numLigne, 7).toString();
							String poste = tableTechnicien.getValueAt(numLigne, 8).toString();
							
							//remplissage du formulaire
							txtNom.setText(nom);
							txtPrenom.setText(prenom);
							txtAdresse.setText(adresse);
							txtTelephone.setText(telephone);
							txtEmail.setText(email);
							txtMdp.setText(mdp);
							txtSpecialite.setText(specialite);
							txtDateEntree.setText(dateEntree);
							txtPoste.setText(poste);
							btEnregistrer.setText("Modifier");
						}
						
					}
				});

	}

	
	
	public void viderChamps()
	{
		this.txtNom.setText("");
		this.txtPrenom.setText("");
		this.txtAdresse.setText("");
		this.txtTelephone.setText("");
		this.txtEmail.setText("");
		this.txtMdp.setText("");
		this.txtSpecialite.setText("");
		this.txtDateEntree.setText("");
		this.txtPoste.setText("");

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==this.btAnnuler) {
			this.viderChamps();
			this.btEnregistrer.setText("Enregistrer");
		}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
			boolean ok = true;
			String nom = this.txtNom.getText();
			String prenom = this.txtPrenom.getText();
			String adresse = this.txtAdresse.getText();
			String telephone = this.txtTelephone.getText();
			String email = this.txtEmail.getText(); 
			String mdp = this.txtMdp.getText(); 
			String txtSpecialite = this.txtSpecialite.getText(); 
			String dateEntree = this.txtDateEntree.getText(); 
			String poste = this.txtPoste.getText(); 

			String regexPattern = "^(.+)@(\\S+)$";
		    if (Pattern.compile(regexPattern).matcher(email).matches() != true)
		    {
		    	ok = false;
		    }
			
			if (ok == true)
			{
				//on enregistre le new technicien dans la base 
				Technicien unTechnicien = new Technicien(nom, prenom, adresse, telephone, email, mdp, txtSpecialite, dateEntree, poste);
				Controleur.insererTechnicien (unTechnicien); 
				
				
				
				JOptionPane.showMessageDialog(this, "Matériel inséré avec succès dans la BDD");
				//insertion dans l'affichage graphique 
				Object ligne[]= {unTechnicien.getIdtechnicien(), nom, prenom, adresse, telephone, email, mdp, txtSpecialite, dateEntree, poste};
				this.unTableau.ajouterLigne(ligne);
				lbMateriels.setText("Nombre de matériels disponibles : " + unTableau.getRowCount());
				
				this.viderChamps();
			}
			else
			{
				this.txtEmail.setBackground(Color.red);
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
			String nom = this.txtNom.getText();
			String prenom = this.txtPrenom.getText();
			String adresse = this.txtAdresse.getText();
			String telephone = this.txtTelephone.getText();
			String email = this.txtEmail.getText(); 
			String mdp = this.txtMdp.getText(); 
			String txtSpecialite = this.txtSpecialite.getText(); 
			String dateEntree = this.txtDateEntree.getText(); 
			String poste = this.txtPoste.getText(); 
			
			int numLigne = 0;
			int idTechnicien = 0;
			numLigne = tableTechnicien.getSelectedRow();
			idTechnicien = Integer.parseInt(tableTechnicien.getValueAt(numLigne, 0).toString());
			
			//Instanciation d'un materiel
			Technicien unTechnicien = new Technicien(idTechnicien, nom, prenom, adresse, telephone, email, mdp, txtSpecialite, dateEntree, poste);
			//Modification dans la base de données
			Controleur.updateTechnicien(unTechnicien);
			//Modification dans l'affichage
			Object ligne[]= {unTechnicien.getIdtechnicien(), nom, prenom, adresse, telephone, email, mdp, txtSpecialite, dateEntree, poste};
			this.unTableau.modifierLigne(numLigne, ligne);
			JOptionPane.showMessageDialog(this, "Modification effectué");
			this.viderChamps();
			this.btEnregistrer.setText("Enregistrer");
		
		}
	}
	
	
	
	public Object [][] obtenirDonnees (String filtre){
		ArrayList<Technicien> lesTechniciens = Controleur.selectAllTechniciens(filtre); 
		Object [][] matrice = new Object[lesTechniciens.size()][9];
		int i = 0;
		for (Technicien unTechnicien : lesTechniciens) {
			matrice [i][0] = unTechnicien.getIdtechnicien(); 
			matrice [i][1] = unTechnicien.getNom();
			matrice [i][2] = unTechnicien.getPrenom(); 
			matrice [i][3] = unTechnicien.getAdresse(); 
			matrice [i][4] = unTechnicien.getTelephone(); 
			matrice [i][5] = unTechnicien.getEmail(); 
			matrice [i][6] = unTechnicien.getSpecialite();
			matrice [i][7] = unTechnicien.getDateEntree(); 
			matrice [i][8] = unTechnicien.getPoste(); 
			i++;
		}
		return matrice;
	}

}
