package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controleur.Controleur;
import controleur.Technicien;

public class PanelProfil extends PanelPrincipal implements ActionListener
{
	private JTextArea txtInfos = new JTextArea();
	private JButton btModifier = new JButton("Modifier");
	
	private JPanel panelForm = new JPanel (); 
	private JTextField txtNom = new JTextField(); 
	private JTextField txtPrenom = new JTextField();
	private JTextField txtAdresse = new JTextField();
	private JTextField txtTelephone= new JTextField();
	private JTextField txtEmail= new JTextField();
	private JPasswordField txtMdp = new JPasswordField();
	private JTextField txtSpecialite= new JTextField();
	private JTextField txtDateEntree= new JTextField();
	private JTextField txtPoste= new JTextField();

	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btEnregistrer = new JButton("Enregistrer"); 
	
	private Technicien unTechnicien ; 
	
	public PanelProfil( Technicien unTechnicien ) {
		super(new Color ( 181, 135, 79  ));
		
		this.unTechnicien = unTechnicien; 
		
		this.txtInfos.setBounds(40, 40, 260, 310);
		String infos ="\n --Informations de votre profil --" 
					 + "\n\n Nom : "+unTechnicien.getNom()
					 + "\n\n Prénom : "+unTechnicien.getPrenom()
					 + "\n\n Adresse : "+unTechnicien.getAdresse()
					 + "\n\n Telephone : "+unTechnicien.getEmail()
					 + "\n\n Email : "+unTechnicien.getEmail()
					 + "\n\n Specialite : "+unTechnicien.getSpecialite()
					 + "\n\n Date d'entrée : "+unTechnicien.getDateEntree()
					 + "\n\n Poste : "+unTechnicien.getPoste();
		
		
		
		this.txtInfos.setText(infos);
		this.txtInfos.setBackground(new Color ( 181, 135, 79  ));
		this.add(this.txtInfos);
		
		//construction du panel Form 
		this.panelForm.setBounds(350, 50, 450, 250);
		this.panelForm.setBackground(new Color ( 181, 135, 79  ));
		this.panelForm.setLayout(new GridLayout(10,2));
		this.panelForm.add(new JLabel("Nom : ")); 
		this.panelForm.add(this.txtNom); 
		this.panelForm.add(new JLabel("Prénom : ")); 
		this.panelForm.add(this.txtPrenom); 
		this.panelForm.add(new JLabel("Adresse : ")); 
		this.panelForm.add(this.txtAdresse); 
		this.panelForm.add(new JLabel("Telephone : ")); 
		this.panelForm.add(this.txtTelephone);
		this.panelForm.add(new JLabel("Email : ")); 
		this.panelForm.add(this.txtEmail);
		this.panelForm.add(new JLabel("Mot de passe : ")); 
		this.panelForm.add(this.txtMdp);
		this.panelForm.add(new JLabel("Specialite : ")); 
		this.panelForm.add(this.txtSpecialite);
		this.panelForm.add(new JLabel("Date d'entrée : ")); 
		this.panelForm.add(this.txtDateEntree);
		this.panelForm.add(new JLabel("Poste : ")); 
		this.panelForm.add(this.txtPoste);
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btEnregistrer); 
		
		this.panelForm.setVisible(false);
		this.add(this.panelForm);
		
		this.btModifier.setBounds(100, 350, 100, 30);
		this.add(this.btModifier);
		
		//rendre les boutons ecoutables 
		this.btModifier.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btAnnuler.addActionListener(this);
		
		//remplir les données 
		this.txtNom.setText(unTechnicien.getNom());
		this.txtPrenom.setText(unTechnicien.getPrenom());
		this.txtAdresse.setText(unTechnicien.getAdresse());
		this.txtTelephone.setText(unTechnicien.getTelephone());
		this.txtEmail.setText(unTechnicien.getEmail());
		this.txtSpecialite.setText(unTechnicien.getSpecialite());
		this.txtDateEntree.setText(unTechnicien.getDateEntree());
		this.txtPoste.setText(unTechnicien.getPoste());

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btModifier) {
			this.panelForm.setVisible(true);
		}
		else if (e.getSource() == this.btAnnuler) {
			this.panelForm.setVisible(false);
			//on peut vider les champs 
		}
		else if (e.getSource() == this.btEnregistrer) {
			boolean ok = true;
		
			String nom = this.txtNom.getText(); 
			String prenom = this.txtPrenom.getText(); 
			String Adresse = this.txtAdresse.getText(); 
			String telephone = this.txtTelephone.getText();
			String email = this.txtEmail.getText(); 
			String mdp =new String(this.txtMdp.getPassword());
			String specialite = this.txtSpecialite.getText(); 
			String dateEntree = this.txtDateEntree.getText(); 
			String poste = this.txtPoste.getText(); 

			
			
			//on controle les données avant modification
			String regex = "^(.+)@(.+$)$";
			ok = Pattern.compile(regex).matcher(email).matches();
			
			if (ok)
			{
				
				//on va enregistrer les modifs dans la base. 
				this.unTechnicien.setNom(nom); 
				this.unTechnicien.setPrenom(prenom); 
				this.unTechnicien.setAdresse(Adresse);
				this.unTechnicien.setTelephone(telephone);
				this.unTechnicien.setEmail(email);
				this.unTechnicien.setMdp(mdp);
				this.unTechnicien.setSpecialite(specialite);
				this.unTechnicien.setDateEntree(dateEntree);
				this.unTechnicien.setPoste(poste);


				Controleur.updateTechnicien (unTechnicien);
				
				JOptionPane.showMessageDialog(this, "Modification effectuée");
				this.panelForm.setVisible(false);
				//actualiser les infos technicien dans la txtInfos 
				String infos ="\n --Informations de votre profil --" 
						 + "\n\n Nom : "+unTechnicien.getNom()
						 + "\n\n Prénom : "+unTechnicien.getPrenom()
						 + "\n\n Adresse : "+unTechnicien.getAdresse()
						 + "\n\n Telephone : "+unTechnicien.getTelephone()
						 + "\n\n Email : "+unTechnicien.getEmail()
						 + "\n\n Specialite : "+unTechnicien.getSpecialite()
						 + "\n\n Date d'entree : "+unTechnicien.getDateEntree()
						 + "\n\n Poste : "+unTechnicien.getPoste();
				
			
				this.txtInfos.setText(infos);
			}else
			{
				System.out.println(ok);
			}

		}
		
	}
}




























