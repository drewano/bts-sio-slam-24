package controleur;

import vue.VueConnexion;
import vue.VueGenerale;

public class OrangeEvent {

	private static VueConnexion uneVueConnexion;
	private static VueGenerale uneVueGenerale ; 
	
	public static void main (String args[]) {
		//instanciation de la vueConnexion 
		uneVueConnexion = new VueConnexion(); 
	}
	
	public static void rendreVisibleConnexion (boolean action) {
		uneVueConnexion.setVisible(action);
	}
	public static void rendreVisibleGenerale (boolean action, Technicien unTechnicien) {
		if (action == true) {
			uneVueGenerale = new VueGenerale(unTechnicien); 
			uneVueGenerale.setVisible(true);
		}else {
			uneVueGenerale.dispose();
		}
	}
}





















