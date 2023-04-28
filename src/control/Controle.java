package control;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.*;
import tools.connexion.*;
import view.*;

/**
 * Classe du controleur de l'application
 *
 * @author JKerboeuf
 */
public class Controle implements AsyncResponse, Global {
	/**
	 * frame EntreeJeu
	 */
	private EntreeJeu frmEntreeJeu;
	/**
	 * frame Arene
	 */
	private Arene frmArene;
	/**
	 * frame ChoixJoueur
	 */
	private ChoixJoueur frmChoixJoueur;
	/**
	 * Instance du jeu avec son type (serveur ou client)
	 */
	private Jeu leJeu;

	/**
	 * Méthode de demarrage
	 *
	 * @param args non utilisé
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

	/**
	 * Méthode qui gère les évenements de l'entrée du jeu
	 *
	 * @param info l'info à traiter
	 */
	public void evenementEntreeJeu(String info) {
		if (info.equals(SERVER)) {
			new ServeurSocket(this, PORT);
			this.leJeu = new JeuServeur(this);
			this.frmEntreeJeu.dispose();
			this.frmArene = new Arene(this, SERVER);
			((JeuServeur) this.leJeu).constructionMurs();
			this.frmArene.setVisible(true);
		} else {
			new ClientSocket(this, info, PORT);
		}
	}

	/**
	 * Méthode qui gère les évenement du choix du joueur
	 *
	 * @param pseudo   pseudo du joueur qui vient d'etre créé
	 * @param numPerso numero du personnage créé
	 */
	public void evenementChoixJoueur(String pseudo, int numPerso) {
		this.frmChoixJoueur.dispose();
		this.frmArene.setVisible(true);
		((JeuClient) this.leJeu).envoi(PSEUDO + STR_SEPARATOR + pseudo + STR_SEPARATOR + numPerso);
	}

	/**
	 * Méthode qui gère les évenements du jeu du serveur
	 *
	 * @param ordre l'ordre à prendre en charge
	 * @param info  l'info à traiter
	 */
	public void evenementJeuServeur(String ordre, Object info) {
		switch (ordre) {
			case AJOUT_MUR:
				frmArene.ajoutMurs(info);
				break;
			case AJOUT_PANEL_MURS:
				this.leJeu.envoi((Connection) info, this.frmArene.getJpnMurs());
				break;
			case AJOUT_LABEL_JEU:
				this.frmArene.ajoutLabelJeu((JLabel) info);
				break;
			case AJOUT_PANEL_JEU:
				this.leJeu.envoi((Connection) info, this.frmArene.getJpnJeu());
				break;
			case AJOUT_CHAT:
				this.frmArene.ajoutTchat((String) info);
				((JeuServeur) this.leJeu).envoi(this.frmArene.getTxtChat());
				break;
		}
	}

	/**
	 * Méthode qui gère les évenements du jeu des clients
	 *
	 * @param ordre l'ordre à prendre en charge
	 * @param info  l'info à traiter
	 */
	public void evenementJeuClient(String ordre, Object info) {
		switch (ordre) {
			case AJOUT_PANEL_MURS:
				this.frmArene.setJpnMurs((JPanel) info);
				break;
			case AJOUT_PANEL_JEU:
				this.frmArene.setJpnJeu((JPanel) info);
				break;
			case MODIF_CHAT:
				this.frmArene.setTxtChat((String) info);
				break;
			case JOUE_SON:
				this.frmArene.joueSon((int) info);
				break;
		}
	}

	/**
	 * Méthode qui gère les évenements de l'arène
	 *
	 * @param info l'info à envoyer
	 */
	public void evenementArene(Object info) {
		if (info instanceof String) {
			((JeuClient) this.leJeu).envoi(CHAT + STR_SEPARATOR + info);
		} else if (info instanceof Integer) {
			((JeuClient) this.leJeu).envoi(ACTION + STR_SEPARATOR + info);
		}
	}

	/**
	 * Méthode pour l'envoi d'information
	 *
	 * @param connection la connection sur laquelle envoyer l'info
	 * @param info       l'info à envoyer
	 */
	public void envoi(Connection connection, Object info) {
		connection.envoi(info);
	}

	/**
	 * Méthode qui gère la réception d'informations
	 *
	 * @param connection la connection de laquelle l'information provient
	 * @param ordre      l'ordre à prendre en charge
	 * @param info       l'info à traiter
	 */
	@Override
	public void reception(Connection connection, String ordre, Object info) {
		switch (ordre) {
			case CONNEXION:
				if (!(this.leJeu instanceof JeuServeur)) {
					this.leJeu = new JeuClient(this);
					this.leJeu.connexion(connection);
					this.frmEntreeJeu.dispose();
					this.frmArene = new Arene(this, CLIENT);
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
}
