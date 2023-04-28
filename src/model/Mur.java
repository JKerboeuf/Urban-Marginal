package model;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import control.Global;

/**
 * Gestion des murs
 *
 * @author JKerboeuf
 */
public class Mur extends Objet implements Global {
	/**
	 * Constructeur
	 */
	public Mur() {
		double randX = Math.random() * (ARENA_WIDTH - WALL_SIZE);
		double randY = Math.random() * (ARENA_HEIGHT - WALL_SIZE);
		posX = (int) Math.round(randX);
		posY = (int) Math.round(randY);

		label = new JLabel();
		label.setBounds(posX, posY, WALL_SIZE, WALL_SIZE);
		URL resource = getClass().getClassLoader().getResource(WALL_SPRITE);
		label.setIcon(new ImageIcon(resource));
	}
}
