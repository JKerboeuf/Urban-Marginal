package model;

import javax.swing.JLabel;

/**
 * Informations communes à tous les objets (joueurs, murs, boules) permet de
 * mémoriser la position de l'objet et de gérer les collisions
 *
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
	protected JLabel label;

	protected JLabel getLabel() {
		return label;
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
