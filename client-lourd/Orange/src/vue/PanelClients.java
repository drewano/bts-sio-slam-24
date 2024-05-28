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
import controleur.Client;


public class PanelClients   extends PanelPrincipal implements ActionListener
{

	private JPanel panelForm = new JPanel(); 
	private JTextField txtNom = new JTextField(); 
	private JTextField txtPrenom = new JTextField(); 
	private JTextField txtAdresse = new JTextField(); 
	private JTextField txtTelephone = new JTextField();
	private JTextField txtEmail = new JTextField();
	private JTextField txtMdp = new JTextField();
	private JComboBox<String> txtType = new JComboBox<String>();
	private JTextField txtFidelite = new JTextField();
	
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btEnregistrer= new JButton("Enregistrer");
	
	private JTable tableClient ; 
	private JScrollPane uneScroll ; 
	private Tableau unTableau ; 
	
	private JPanel panelRech = new JPanel();
	private JTextField txtFiltre = new JTextField(); 
	private JButton btFiltre = new JButton("Filtrer");
	
	private JLabel lbMateriels;
	
	public PanelClients() {
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
				this.panelForm.add(new JLabel("type : "));
				this.panelForm.add(this.txtType);
				this.panelForm.add(new JLabel("fidelite : "));
				this.panelForm.add(this.txtFidelite);
				this.panelForm.add(this.btAnnuler); 
				this.panelForm.add(this.btEnregistrer);
				this.add(this.panelForm); 
				this.panelForm.setVisible(true);
				
				
				// remplir txtType
				this.txtType.addItem("Prive");
				this.txtType.addItem("Public");
				this.txtType.addItem("inconnu");
				
				//construction de la Jtable des materiels 
				String entetes [] = {"ID Client", "Nom", "Prenom", "Adresse", "Telephone", "Email", "Type", "Fidelite"};
				this.unTableau = new Tableau (entetes, this.obtenirDonnees(""));
				this.tableClient = new JTable(this.unTableau); 
				this.uneScroll = new JScrollPane(this.tableClient); 
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
				
				this.tableClient.getTableHeader().setReorderingAllowed(false);
				
				
				
				this.tableClient.addMouseListener(new MouseListener() {
					
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
							numLigne = tableClient.getSelectedRow();
							idMateriel = Integer.parseInt(tableClient.getValueAt(numLigne, 0).toString());
							int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce Client", "Supression matérielle", JOptionPane.YES_NO_OPTION);
							
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
							numLigne = tableClient.getSelectedRow();
							idMateriel = Integer.parseInt(tableClient.getValueAt(numLigne, 0).toString());
							String nom = tableClient.getValueAt(numLigne, 1).toString();
							String prenom = tableClient.getValueAt(numLigne, 2).toString();
							String adresse = tableClient.getValueAt(numLigne, 3).toString();
							String telephone = tableClient.getValueAt(numLigne, 4).toString();
							String email = tableClient.getValueAt(numLigne, 5).toString();
							String mdp = "";
							String type = tableClient.getValueAt(numLigne, 6).toString();
							String fidelite = tableClient.getValueAt(numLigne, 7).toString();
							
							//remplissage du formulaire
							txtNom.setText(nom);
							txtPrenom.setText(prenom);
							txtAdresse.setText(adresse);
							txtTelephone.setText(telephone);
							txtEmail.setText(email);
							txtMdp.setText(mdp);
							txtFidelite.setText(fidelite);
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
		this.txtFidelite.setText("");

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
			String type = this.txtType.getSelectedItem().toString(); 
			String fidelite = this.txtFidelite.getText(); 

			
			if (ok)
			{
				//on enregistre le new Client dans la base 
				Client unClient = new Client(nom, prenom, adresse, telephone, email, mdp, type, fidelite);
				Controleur.insererClient (unClient); 
				
				
				
				JOptionPane.showMessageDialog(this, "Matériel inséré avec succés dans la BDD");
				//insertion dans l'affichage graphique 
				Object ligne[]= {unClient.getIdClient(), nom, prenom, adresse, telephone, email, mdp, type, fidelite};
				this.unTableau.ajouterLigne(ligne);
				
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
			String nom = this.txtNom.getText();
			String prenom = this.txtPrenom.getText();
			String adresse = this.txtAdresse.getText();
			String telephone = this.txtTelephone.getText();
			String email = this.txtEmail.getText(); 
			String mdp = this.txtMdp.getText(); 
			String type = this.txtType.getSelectedItem().toString(); 
			String fidelite = this.txtFidelite.getText();

			int numLigne = 0;
			int idClient = 0;
			numLigne = tableClient.getSelectedRow();
			idClient = Integer.parseInt(tableClient.getValueAt(numLigne, 0).toString());
			
			//Instanciation d'un materiel
			Client unClient = new Client(idClient, nom, prenom, adresse, telephone, email, mdp, type, fidelite);
			//Modification dans la base de données
			Controleur.updateClient(unClient);
			//Modification dans l'affichage
			Object ligne[]= {unClient.getIdClient(), nom, prenom, adresse, telephone, email, mdp, type, fidelite};
			this.unTableau.modifierLigne(numLigne, ligne);
			JOptionPane.showMessageDialog(this, "Modification effectué");
			this.viderChamps();
			this.btEnregistrer.setText("Enregistrer");
		
		}
	}
	
	
	
	public Object [][] obtenirDonnees (String filtre){
		ArrayList<Client> lesClients = Controleur.selectAllClients(filtre); 
		Object [][] matrice = new Object[lesClients.size()][9];
		int i = 0;
		for (Client unClient : lesClients) {
			matrice [i][0] = unClient.getIdClient(); 
			matrice [i][1] = unClient.getNom();
			matrice [i][2] = unClient.getPrenom(); 
			matrice [i][3] = unClient.getAdresse(); 
			matrice [i][4] = unClient.getTelephone(); 
			matrice [i][5] = unClient.getEmail(); 
			matrice [i][6] = unClient.getType();
			matrice [i][7] = unClient.getFidelite();
			i++;
		}
		return matrice;
	}

}  

