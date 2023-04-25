package tools.son.ecouteurs;

import tools.son.Sound;

/**
 * Indique que l'objet écoute les évènements sons <br>
 */

public interface EcouteurSon {
	/**
	 * Indique qu'un son est terminé
	 * 
	 * @param son Son qui a finit de joué
	 */
	public void sonTermine(Sound son);

	/**
	 * Indique qu'un son vient d'avancer sur sa lecture
	 * 
	 * @param son Son qui est entrain d'avancer
	 */
	public void sonChangePosition(Sound son);
}
