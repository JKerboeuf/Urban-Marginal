package model;

import control.Controle;
import tools.connexion.Connection;

/**
 * Informations et méthodes communes aux jeux client et serveur
 *
 * @author JKerboeuf
 */
public abstract class Jeu {
	/**
	 * Instance du controleur
	 */
	protected Controle controle;

	/**
	 * Réception d'une connexion (pour communiquer avec un ordinateur distant)
	 *
	 * @param connection objet connection pour la communication
	 */
	public abstract void connexion(Connection connection);

	/**
	 * Réception d'une information provenant de l'ordinateur distant
	 *
	 * @param connection la connection dont provient l'info à traiter
	 * @param info       l'info à traiter
	 */
	public abstract void reception(Connection connection, Object info);

	/**
	 * Déconnexion de l'ordinateur distant
	 */
	public abstract void deconnexion();

	/**
	 * Envoi d'une information vers un ordinateur distant
	 *
	 * @param connection la connection de laquelle provient l'information
	 * @param info       l'information à envoyer
	 */
	public void envoi(Connection connection, Object info) {
		this.controle.envoi(connection, info);
	}
}
