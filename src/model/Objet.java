package model;

import java.util.Collection;

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
	protected int posX;
	/**
	 * position Y de l'objet
	 */
	protected int posY;
	/**
	 * le label pour afficher l'objet
	 */
	protected JLabel label;

	/**
	 * getter sur la position X de l'objet
	 *
	 * @return la position en X de l'objet
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * getter sur la position Y de l'objet
	 *
	 * @return la position en Y de l'objet
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * getter du label de l'objet
	 *
	 * @return le label de l'objet
	 */
	public JLabel getLabel() {
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

	/**
	 * vérification si un objet touche un autre parmi une collection
	 *
	 * @param lesObjets la collection d'objet a tester
	 * @return l'objet en collision ou null si aucun autre objet ne touche
	 */
	public Objet toucheCollectionObjets(Collection<Objet> lesObjets) {
		for (Objet unObjet : lesObjets) {
			if (!unObjet.equals(this) && toucheObjet(unObjet)) {
				return unObjet;
			}
		}
		return null;
	}
}
