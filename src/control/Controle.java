package control;

import tools.connexion.*;
import view.*;

public class Controle implements AsyncResponse {
	private static final int PORT = 6666;
	private String typeJeu;
	private EntreeJeu frmEntreeJeu;
	private Arene frmArene;
	private ChoixJoueur frmChoix;

	/**
	 * Methode de demarrage
	 * 
	 * @param args non utilise
	 */
	public static void main(String[] args) {
		new Controle();
	}

	/**
	 * Constructeur
	 */
	private Controle() {
		this.frmEntreeJeu = new EntreeJeu(this);
		this.frmEntreeJeu.setVisible(true);
	}

	public void evenementEntreeJeu(String info) {
		if (info.equals("serveur")) {
			typeJeu = "serveur";
			new ServeurSocket(this, PORT);
			frmEntreeJeu.dispose();
			frmArene = new Arene();
			frmArene.setVisible(true);
		} else {
			typeJeu = "client";
			new ClientSocket(this, info, PORT);
		}
	}

	public void evenementChoixJoueur(String pseudo, int numPerso) {
		frmChoix.dispose();
		frmArene = new Arene();
		frmArene.setVisible(true);
	}
	
	@Override
	public void reception(Connection connection, String ordre, Object info) {
		switch (ordre) {
		case "connexion":
			if (this.typeJeu.equals("client")) {
				frmEntreeJeu.dispose();
				frmChoix = new ChoixJoueur(this);
				frmChoix.setVisible(true);
				frmArene = new Arene();
			}
			break;
		case "réception":

			break;
		case "déconnexion":

			break;
		}
	}
}
