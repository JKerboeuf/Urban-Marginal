package model;

import javax.swing.JLabel;

/**
 * Informations communes à tous les objets (joueurs, murs, boules) permet de
 * mémoriser la position de l'objet et de gérer les collisions
 *
 * @author JKerboeuf
 */
public abstract class Objet {
	/**
	 * position X de l'objet
	 */
	protected Integer posX;
	/**
	 * position Y de l'objet
	 */
	protected Integer posY;
	/**
	 * le label pour afficher l'objet
	 */
	protected JLabel label;

	/**
	 * getter du label de l'objet
	 *
	 * @return le label de l'objet
	 */
	protected JLabel getLabel() {
		return label;
	}

	/**
	 * contrôle si l'objet actuel touche l'objet passé en paramètre
	 *
	 * @param testX position X à tester
	 * @param testY position Y à tester
	 * @param objet contient l'objet à contrôler
	 * @return true si les 2 objets se touchent
	 */
	public Boolean toucheObjet(int testX, int testY, Objet objet) {
		if (objet.label == null || objet.label == null) {
			return false;
		} else {
			return (testX + this.label.getWidth() > objet.posX &&
					testX < objet.posX + objet.label.getWidth() &&
					testY + this.label.getHeight() > objet.posY &&
					testY < objet.posY + objet.label.getHeight());
		}
	}

	/**
	 * contrôle si l'objet actuel touche l'objet passé en paramètre
	 *
	 * @param objet contient l'objet à contrôler
	 * @return true si les 2 objets se touchent
	 */
	public Boolean toucheObjet(Objet objet) {
		if (objet.label == null || objet.label == null) {
			return false;
		} else {
			return (this.posX + this.label.getWidth() > objet.posX &&
					this.posX < objet.posX + objet.label.getWidth() &&
					this.posY + this.label.getHeight() > objet.posY &&
					this.posY < objet.posY + objet.label.getHeight());
		}
	}
}
