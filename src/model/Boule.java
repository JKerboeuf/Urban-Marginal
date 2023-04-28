package model;

import java.net.URL;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import control.Global;

/**
 * Classe de la boule
 *
 * @author JKerboeuf
 */
public class Boule extends Objet implements Global, Runnable {
	/**
	 * instance de JeuServeur pour la communication
	 */
	private JeuServeur jeuServeur;
	/**
	 * collection des murs de l'arene
	 */
	private Collection<Mur> lesMurs;
	/**
	 * joueur qui a tiré la boule
	 */
	private Joueur tireur;

	/**
	 * Constructeur
	 *
	 * @param jeuServeur le JeuServeur
	 */
	public Boule(JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur;
		super.label = new JLabel();
		super.label.setVisible(false);
		URL resource = getClass().getClassLoader().getResource(BALL_SPRITE);
		super.label.setIcon(new ImageIcon(resource));
		super.label.setBounds(0, 0, BALL_SIZE, BALL_SIZE);
	}

	/**
	 * Tire d'une boule
	 *
	 * @param tireur  le joueur qui a tiré la boule
	 * @param lesMurs la collection des murs a tester pour les colisions
	 */
	public void tireBoule(Joueur tireur, Collection<Mur> lesMurs) {
		this.lesMurs = lesMurs;
		this.tireur = tireur;
		if (tireur.getOrientation() == GAUCHE) {
			posX = tireur.getPosX() - BALL_SIZE - 1;
		} else {
			posX = tireur.getPosX() + CHAR_WIDTH + 1;
		}
		posY = tireur.getPosY() + CHAR_HEIGHT / 2;
		new Thread(this).start();
	}

	@Override
	public void run() {
		this.jeuServeur.envoi(FIGHT);
		this.tireur.affiche(WALK, 1);
		super.label.setVisible(true);
		Joueur victime = null;
		int vitesse;
		if (this.tireur.getOrientation() == DROITE) {
			vitesse = BALL_SPEED;
		} else {
			vitesse = -BALL_SPEED;
		}
		do {
			posX += vitesse;
			super.label.setBounds(posX, posY, BALL_SIZE, BALL_SIZE);
			this.jeuServeur.envoiJeuATous();
			Collection<Joueur> lesJoueurs = this.jeuServeur.getLesJoueurs();
			victime = (Joueur) super.toucheCollectionObjets((Collection) lesJoueurs);
		} while (posX >= 0 && posX <= ARENA_WIDTH && victime == null &&
				this.toucheCollectionObjets((Collection) lesMurs) == null);
		if (victime != null && !victime.estMort()) {
			this.jeuServeur.envoi(HURT);
			victime.perteVie();
			tireur.gainVie();
			for (int i = 1; i <= ANIM_STEPS_HIT; i++) {
				victime.affiche(HIT, i);
				pause(ANIM_DELAY);
			}
			if (victime.estMort()) {
				this.jeuServeur.envoi(DEATH);
				for (int i = 1; i <= ANIM_STEPS_DEAD; i++) {
					victime.affiche(DEAD, i);
					pause(ANIM_DELAY);
				}
			} else {
				victime.affiche(WALK, 1);
			}
		}
		super.label.setVisible(false);
		this.jeuServeur.envoiJeuATous();
	}

	/**
	 * fais une pause (bloque le processus) d'une durée précise
	 *
	 * @param millis durée en millisecondes
	 */
	private void pause(long millis) {
		try {
			Thread.sleep(millis, 0);
		} catch (InterruptedException e) {
			System.out.println("erreur pause");
		}
	}
}
