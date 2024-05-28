package vue;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import controleur.Controleur;
import controleur.Tableau;
import controleur.ViewTech;

public class PanelStats extends PanelPrincipal{
		
	private JTable tableView;
	private JScrollPane uneScroll;
	private Tableau unTableau;
	
	public PanelStats()
	{
		super(new Color ( 181, 135, 79  ));
		
		String entetes [] = {"nom", "Prénom", "Email", "Nb Interventions", "Total Bénéfices"};
		
		this.unTableau = new Tableau(entetes, this.obtenirDonnees());
		this.tableView = new JTable(this.unTableau);
		this.uneScroll = new JScrollPane(tableView);
		
		this.uneScroll.setBounds(80, 80, 800, 200);
		this.add(this.uneScroll);
	}
		
		
	public Object [][] obtenirDonnees (){
		ArrayList<ViewTech> lesViewTechs = Controleur.selectAllViewTechs(); 
		Object [][] matrice = new Object[lesViewTechs.size()][5];
		int i = 0; 
		for (ViewTech unViewTech : lesViewTechs) {
			matrice [i][0] = unViewTech.getNom(); 
			matrice [i][1] = unViewTech.getPrenom(); 
			matrice [i][2] = unViewTech.getEmail();
			matrice [i][3] = unViewTech.getNbInterventions(); 
			matrice [i][4] = unViewTech.getTotalPrix(); 


			i++;
		}
		return matrice;
	}
	 
}
