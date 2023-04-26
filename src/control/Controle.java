package control;

import model.*;
import tools.connexion.*;
import view.*;

public class Controle implements AsyncResponse, Global {
	private EntreeJeu frmEntreeJeu;
	private Arene frmArene;
	private ChoixJoueur frmChoixJoueur;
	private Jeu leJeu;

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
			new ServeurSocket(this, PORT);
			this.leJeu = new JeuServeur(this);
			this.frmEntreeJeu.dispose();
			this.frmArene = new Arene();
			this.frmArene.setVisible(true);
		} else {
			new ClientSocket(this, info, PORT);
		}
	}

	public void evenementChoixJoueur(String pseudo, int numPerso) {
		this.frmChoixJoueur.dispose();
		this.frmArene.setVisible(true);
		((JeuClient) this.leJeu).envoi(PSEUDO + STR_SEPARATOR + pseudo + STR_SEPARATOR + numPerso);
	}

	@Override
	public void reception(Connection connection, String ordre, Object info) {
		switch (ordre) {
		case CONNEXION:
			if (!(this.leJeu instanceof JeuServeur)) {
				this.leJeu = new JeuClient(this);
				this.leJeu.connexion(connection);
				this.frmEntreeJeu.dispose();
				this.frmArene = new Arene();
				this.frmChoixJoueur = new ChoixJoueur(this);
				this.frmChoixJoueur.setVisible(true);
			} else {
				this.leJeu.connexion(connection);
			}
			break;
		case RECEPTION:
			this.leJeu.reception(connection, info);
			break;
		case DECONNEXION:

			break;
		}
	}

	public void envoi(Connection connection, Object info) {
		connection.envoi(info);
	}
}
