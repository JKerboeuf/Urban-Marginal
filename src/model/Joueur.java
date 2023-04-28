package model;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import control.Global;

/**
 * Gestion des joueurs
 *
 * @author JKerboeuf
 */
public class Joueur extends Objet implements Global {
	/**
	 * pseudo saisi
	 */
	private String pseudo;
	/**
	 * numéro correspondant au personnage (avatar) pour le fichier correspondant
	 */
	private int numPerso;
	/**
	 * instance de JeuServeur pour communiquer avec lui
	 */
	private JeuServeur jeuServeur;
	/**
	 * numéro d'étape dans l'animation (de la marche, touché ou mort)
	 */
	private int etape;
	/**
	 * la boule du joueur
	 */
	private Boule boule;
	/**
	 * vie restante du joueur
	 */
	private int vie;
	/**
	 * tourné vers la gauche (0) ou vers la droite (1)
	 */
	private int orientation;
	/**
	 * le message sous le personnage incluant le pseudo et sa vie
	 */
	private JLabel message;

	/**
	 * Constructeur
	 *
	 * @param jeuServeur l'instance du jeu serveur
	 */
	public Joueur(JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur;
		this.vie = HP_MAX;
		this.etape = 1;
		this.orientation = DROITE;
	}

	/**
	 * getter sur le pseudo
	 *
	 * @return le pseudo du joueur
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * getter sur l'orientation du joueur
	 *
	 * @return l'entier correspondant au sens du joueur
	 */
	public int getOrientation() {
		return orientation;
	}

	/**
	 * Initialisation d'un joueur (pseudo et numéro, calcul de la 1ère position,
	 * affichage, création de la boule)
	 *
	 * @param pseudo     le pseudo du joueur
	 * @param numPerso   le numero du joueur
	 * @param lesJoueurs la collection de joueurs pour y inclure ce nouveau la
	 * @param lesMurs    la collection de murs pour verifier qu'il ne spawn pas dans
	 *                   un mur
	 */
	public void initPerso(String pseudo, int numPerso, Collection lesJoueurs, Collection lesMurs) {
		this.pseudo = pseudo;
		this.numPerso = numPerso;
		System.out.println("joueur " + pseudo + " - num perso " + numPerso + " créé");
		super.label = new JLabel();
		this.message = new JLabel();
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setFont(new Font("Dialog", Font.PLAIN, 8));
		this.boule = new Boule(this.jeuServeur);
		this.premierePosition(lesJoueurs, lesMurs);
		this.jeuServeur.ajoutLabelJeuArene(label);
		this.jeuServeur.ajoutLabelJeuArene(message);
		this.jeuServeur.ajoutLabelJeuArene(boule.getLabel());
		this.affiche(WALK, this.etape);
	}

	/**
	 * Calcul de la première position aléatoire du joueur (sans chevaucher un autre
	 * joueur ou un mur)
	 *
	 * @param lesJoueurs collection de joueurs pour verifier le bon spawn
	 * @param lesMurs    collection de murs pour verifier le bon spawn
	 */
	private void premierePosition(Collection lesJoueurs, Collection lesMurs) {
		label.setBounds(0, 0, CHAR_WIDTH, CHAR_HEIGHT);
		do {
			posX = (int) Math.round(Math.random() * (ARENA_WIDTH - CHAR_WIDTH));
			posY = (int) Math.round(Math.random() * (ARENA_HEIGHT - CHAR_HEIGHT - CHAR_TITLE_HEIGHT));
		} while (this.toucheCollectionObjets(lesJoueurs) != null || this.toucheCollectionObjets(lesMurs) != null);
	}

	/**
	 * Affiche le personnage et son message
	 *
	 * @param etat  etat du personnage
	 * @param etape etape de l'etat du personnage
	 */
	public void affiche(String etat, int etape) {
		super.label.setBounds(posX, posY, CHAR_WIDTH, CHAR_HEIGHT);
		String fileName = this.numPerso + etat + etape + "d" + orientation;
		URL resource = getClass().getClassLoader().getResource(CHAR_PATH + fileName + SPRITE_EXT);
		super.label.setIcon(new ImageIcon(resource));
		this.message.setBounds(posX - 10, posY + CHAR_HEIGHT, CHAR_WIDTH + 10, CHAR_TITLE_HEIGHT);
		this.message.setText(pseudo + " : " + vie);
		this.jeuServeur.envoiJeuATous();
	}

	/**
	 * Gère une action reçue et qu'il faut afficher (déplacement, tire de boule...)
	 *
	 * @param action     action de mouvement
	 * @param lesJoueurs collection des joueurs pour tester les collisions
	 * @param lesMurs    collection des murs pour tester les collisions
	 */
	public void action(int action, Collection<Joueur> lesJoueurs, ArrayList<Mur> lesMurs) {
		switch (action) {
			case KeyEvent.VK_LEFT:
				orientation = GAUCHE;
				this.posX -= deplace(GAUCHE, lesJoueurs, lesMurs);
				break;
			case KeyEvent.VK_RIGHT:
				orientation = DROITE;
				this.posX += deplace(DROITE, lesJoueurs, lesMurs);
				break;
			case KeyEvent.VK_UP:
				this.posY -= deplace(HAUT, lesJoueurs, lesMurs);
				break;
			case KeyEvent.VK_DOWN:
				this.posY += deplace(BAS, lesJoueurs, lesMurs);
				break;
			case KeyEvent.VK_SPACE:
				if (!this.boule.getLabel().isVisible()) {
					this.boule.tireBoule(this, lesMurs);
				}
				break;
		}
		affiche(WALK, this.etape);
	}

	/**
	 * Gère le déplacement du personnage
	 *
	 * @param sens    le sens du déplacement
	 * @param joueurs les joueurs pour tester les collisions
	 * @param murs    les murs pour tester les collisions
	 * @return 0 ou STEP_SIZE pour incrementer la position du personnage
	 */
	private int deplace(int sens, Collection<Joueur> joueurs, ArrayList<Mur> murs) {
		boolean collision = false;
		switch (sens) {
			case GAUCHE:
				if (toucheJoueur(this.posX - WALK_SPEED, this.posY, joueurs) ||
						toucheMur(this.posX - WALK_SPEED, this.posY, murs) ||
						posX - WALK_SPEED < 0) {
					collision = true;
				}
				break;
			case DROITE:
				if (toucheJoueur(this.posX + WALK_SPEED, this.posY, joueurs) ||
						toucheMur(this.posX + WALK_SPEED, this.posY, murs) ||
						posX + CHAR_WIDTH + WALK_SPEED > ARENA_WIDTH) {
					collision = true;
				}
				break;
			case HAUT:
				if (toucheJoueur(this.posX, this.posY - WALK_SPEED, joueurs) ||
						toucheMur(this.posX, this.posY - WALK_SPEED, murs) ||
						posY - WALK_SPEED < 0) {
					collision = true;
				}
				break;
			case BAS:
				if (toucheJoueur(this.posX, this.posY + WALK_SPEED, joueurs) ||
						toucheMur(this.posX, this.posY + WALK_SPEED, murs) ||
						posY + CHAR_HEIGHT + WALK_SPEED > ARENA_HEIGHT) {
					collision = true;
				}
				break;
		}
		etape = (etape % 4) + 1;
		if (!collision) {
			return WALK_SPEED;
		}
		return 0;
	}

	/**
	 * Contrôle si le joueur touche un des autres joueurs
	 *
	 * @param lesJoueurs les joueurs a tester la position
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
	 * Contrôle si le joueur touche un des autres joueurs
	 *
	 * @param testX      position X à tester
	 * @param testY      position Y à tester
	 * @param lesJoueurs les joueurs a tester la position
	 * @return true si deux joueurs se touchent
	 */
	private Boolean toucheJoueur(int testX, int testY, Collection<Joueur> lesJoueurs) {
		for (Joueur unJoueur : lesJoueurs) {
			if (!this.equals(unJoueur)) {
				if (super.toucheObjet(testX, testY, unJoueur)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * * Contrôle si le joueur touche un des murs
	 *
	 * @param lesMurs les murs a tester la position
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
	 * * Contrôle si le joueur touche un des murs
	 *
	 * @param testX   position X à tester
	 * @param testY   position Y à tester
	 * @param lesMurs les murs a tester la position
	 * @return true si un joueur touche un mur
	 */
	private Boolean toucheMur(int testX, int testY, ArrayList<Mur> lesMurs) {
		for (Mur unMur : lesMurs) {
			if (super.toucheObjet(testX, testY, unMur)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Gain de points de vie après avoir touché un joueur
	 */
	public void gainVie() {
		this.vie += GAIN;
	}

	/**
	 * Perte de points de vie après avoir été touché
	 */
	public void perteVie() {
		this.vie = Math.max(0, this.vie - PERTE);
	}

	/**
	 * vrai si la vie est à 0
	 *
	 * @return true si vie = 0
	 */
	public Boolean estMort() {
		return (this.vie == 0);
	}

	/**
	 * Le joueur se déconnecte et disparait
	 */
	public void departJoueur() {
	}

}
