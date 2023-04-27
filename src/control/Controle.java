package control;

import javax.swing.JLabel;
import javax.swing.JPanel;

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

	public void evenementChoixJoueur(String pseudo, int numPerso) {
		this.frmChoixJoueur.dispose();
		this.frmArene.setVisible(true);
		((JeuClient) this.leJeu).envoi(PSEUDO + STR_SEPARATOR + pseudo + STR_SEPARATOR + numPerso);
	}

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
		}
	}

	public void evenementArene(String info) {
		((JeuClient) this.leJeu).envoi(CHAT + STR_SEPARATOR + info);
	}

	public void envoi(Connection connection, Object info) {
		connection.envoi(info);
	}

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
