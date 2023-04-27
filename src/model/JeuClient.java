package model;

import javax.swing.JPanel;

import control.Controle;
import control.Global;
import tools.connexion.Connection;

/**
 * Gestion du jeu côté client
 *
 */
public class JeuClient extends Jeu implements Global {
	private Connection connection;
	private boolean mursOk = false;

	/**
	 * Controleur
	 */
	public JeuClient(Controle controle) {
		super.controle = controle;
	}

	@Override
	public void connexion(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void reception(Connection connection, Object info) {
		if (info instanceof JPanel) {
			if (!this.mursOk) {
				this.controle.evenementJeuClient(AJOUT_PANEL_MURS, info);
				this.mursOk = true;
			} else {
				this.controle.evenementJeuClient(AJOUT_PANEL_JEU, info);
			}
		}
	}

	@Override
	public void deconnexion() {
	}

	/**
	 * Envoi d'une information vers le serveur fais appel une fois à l'envoi dans la
	 * classe Jeu
	 */
	public void envoi(String info) {
		super.envoi(this.connection, info);
	}
}
