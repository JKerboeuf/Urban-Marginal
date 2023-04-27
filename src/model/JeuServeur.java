package model;

import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.JLabel;

import control.Controle;
import control.Global;
import tools.connexion.Connection;

/**
 * Gestion du jeu côté serveur
 *
 */
public class JeuServeur extends Jeu implements Global {

	/**
	 * Collection de murs
	 */
	private ArrayList<Mur> lesMurs = new ArrayList<Mur>();
	/**
	 * Collection de joueurs
	 */
	private Hashtable<Connection, Joueur> lesJoueurs = new Hashtable<Connection, Joueur>();

	/**
	 * Constructeur
	 */
	public JeuServeur(Controle controle) {
		super.controle = controle;
	}

	@Override
	public void connexion(Connection connection) {
		this.lesJoueurs.put(connection, new Joueur(this));
	}

	@Override
	public void reception(Connection connection, Object info) {
		String[] infos = ((String) info).split(STR_SEPARATOR);
		String ordre = infos[0];
		switch (ordre) {
			case PSEUDO:
				controle.evenementJeuServeur(AJOUT_PANEL_MURS, connection);
				String pseudo = infos[1];
				int numPerso = Integer.parseInt(infos[2]);
				this.lesJoueurs.get(connection).initPerso(pseudo, numPerso, this.lesJoueurs.values(), this.lesMurs);
				break;
		}
	}

	@Override
	public void deconnexion() {
	}

	/**
	 * Envoi d'une information vers tous les clients fais appel plusieurs fois à
	 * l'envoi de la classe Jeu
	 */
	public void envoi() {
	}

	public void envoiJeuATous() {
		for (Connection connection : this.lesJoueurs.keySet()) {
			this.controle.evenementJeuServeur(AJOUT_PANEL_JEU, connection);
		}
	}

	/**
	 * Génération des murs
	 */
	public void constructionMurs() {
		for (int i = 0; i < WALLS_MAX; i++) {
			Mur newMur = new Mur();
			this.lesMurs.add(newMur);
			this.controle.evenementJeuServeur(AJOUT_MUR, newMur.getLabel());
		}
	}

	public void ajoutLabelJeuArene(JLabel label) {
		this.controle.evenementJeuServeur(AJOUT_LABEL_JEU, label);
	}
}
