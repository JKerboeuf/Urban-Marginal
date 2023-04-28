package model;

import javax.swing.JPanel;

import control.Controle;
import control.Global;
import tools.connexion.Connection;

/**
 * Gestion du jeu côté client
 *
 * @author JKerboeuf
 */
public class JeuClient extends Jeu implements Global {
	/**
	 * la connection qui lie le client au serveur
	 */
	private Connection connection;
	/**
	 * booleen pour verifier qu'on pose les murs qu'une seule fois
	 */
	private boolean mursOk = false;

	/**
	 * Constructeur
	 *
	 * @param controle l'instance du controleur
	 */
	public JeuClient(Controle controle) {
		super.controle = controle;
	}

	/**
	 * Méthode qui connecte un client au serveur
	 *
	 * @param connection l'instance de la connection a lier
	 */
	@Override
	public void connexion(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Méthode qui gère la reception de données
	 *
	 * @param connection la connection dont vient l'information à traiter
	 * @param info       l'information à traiter
	 */
	@Override
	public void reception(Connection connection, Object info) {
		if (info instanceof JPanel) {
			if (!this.mursOk) {
				this.controle.evenementJeuClient(AJOUT_PANEL_MURS, info);
				this.mursOk = true;
			} else {
				this.controle.evenementJeuClient(AJOUT_PANEL_JEU, info);
			}
		} else if (info instanceof String) {
			this.controle.evenementJeuClient(MODIF_CHAT, info);
		} else if(info instanceof Integer) {
			this.controle.evenementJeuClient(JOUE_SON, info);
		}
	}

	/**
	 * Méthode qui gère les déconnexions
	 */
	@Override
	public void deconnexion() {
	}

	/**
	 * Envoi d'une information vers le serveur fais appel une fois à l'envoi dans la
	 * classe Jeu
	 *
	 * @param info l'information à envoyer
	 */
	public void envoi(String info) {
		super.envoi(this.connection, info);
	}
}
