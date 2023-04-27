package model;

import java.awt.Font;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import control.Global;

/*
 * Gestion des joueurs
 */
public class Joueur extends Objet implements Global {
	// pseudo saisi
	private String pseudo;
	// numéro correspondant au personnage (avatar) pour le fichier correspondant
	private int numPerso;
	// instance de JeuServeur pour communiquer avec lui
	private JeuServeur jeuServeur;
	// numéro d'étape dans l'animation (de la marche, touché ou mort)
	private int etape;
	// la boule du joueur
	private Boule boule;
	// vie restante du joueur
	private int vie;
	// tourné vers la gauche (0) ou vers la droite (1)
	private int orientation;
	private JLabel message;

	// Constructeur
	public Joueur(JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur;
		this.vie = HP_MAX;
		this.orientation = DROITE;
		this.etape = 1;
	}

	/**
	 * Initialisation d'un joueur (pseudo et numéro, calcul de la 1ère position,
	 * affichage, création de la boule)
	 */
	public void initPerso(String pseudo, int numPerso, Collection<Joueur> lesJoueurs, ArrayList<Mur> lesMurs) {
		this.pseudo = pseudo;
		this.numPerso = numPerso;
		System.out.println("joueur " + pseudo + " - num perso " + numPerso + " créé");
		super.label = new JLabel();
		this.message = new JLabel();
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setFont(new Font("Dialog", Font.PLAIN, 8));
		this.premierePosition(lesJoueurs, lesMurs);
		this.jeuServeur.ajoutLabelJeuArene(label);
		this.jeuServeur.ajoutLabelJeuArene(message);
		this.affiche(WALK, this.etape);
	}

	/**
	 * Calcul de la première position aléatoire du joueur (sans chevaucher un autre
	 * joueur ou un mur)
	 */
	private void premierePosition(Collection<Joueur> lesJoueurs, ArrayList<Mur> lesMurs) {
		label.setBounds(0, 0, CHAR_WIDTH, CHAR_HEIGHT);
		do {
			posX = (int) Math.round(Math.random() * (ARENA_WIDTH - CHAR_WIDTH));
			posY = (int) Math.round(Math.random() * (ARENA_HEIGHT - CHAR_HEIGHT - CHAR_TITLE_HEIGHT));
		} while (this.toucheJoueur(lesJoueurs) || this.toucheMur(lesMurs));
	}

	/**
	 * Affiche le personnage et son message
	 */
	public void affiche(String etat, int etape) {
		super.label.setBounds(posX, posY, CHAR_WIDTH, CHAR_HEIGHT);
		String fileName = CHAR + this.numPerso + etat + etape + "d" + orientation;
		URL resource = getClass().getClassLoader().getResource(CHAR_PATH + fileName + SPRITE_EXT);
		label.setIcon(new ImageIcon(resource));
		this.message.setBounds(posX - 10, posY + CHAR_HEIGHT, CHAR_WIDTH + 10, CHAR_TITLE_HEIGHT);
		this.message.setText(pseudo + " : " + vie);
		this.jeuServeur.envoiJeuATous();
	}

	/**
	 * Gère une action reçue et qu'il faut afficher (déplacement, tire de boule...)
	 */
	public void action() {
	}

	/**
	 * Gère le déplacement du personnage
	 */
	private void deplace() {
	}

	/**
	 * Contrôle si le joueur touche un des autres joueurs
	 *
	 * @return true si deux joueurs se touchent
	 */
	private Boolean toucheJoueur(Collection<Joueur> lesJoueurs) {
		for (Joueur unJoueur : lesJoueurs) {
			if (!this.equals(unJoueur)) {
				if (super.toucheObjet(unJoueur)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Contrôle si le joueur touche un des murs
	 *
	 * @return true si un joueur touche un mur
	 */
	private Boolean toucheMur(ArrayList<Mur> lesMurs) {
		for (Mur unMur : lesMurs) {
			if (super.toucheObjet(unMur)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gain de points de vie après avoir touché un joueur
	 */
	public void gainVie() {
	}

	/**
	 * Perte de points de vie après avoir été touché
	 */
	public void perteVie() {
	}

	/**
	 * vrai si la vie est à 0
	 *
	 * @return true si vie = 0
	 */
	public Boolean estMort() {
		return null;
	}

	/**
	 * Le joueur se déconnecte et disparait
	 */
	public void departJoueur() {
	}

}
