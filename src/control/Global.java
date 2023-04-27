package control;

/**
 * Interface contenant les globales utiles pour toutes les classes
 *
 * @author JKerboeuf
 */
public interface Global {
	String PATH_SEPARATOR = "/";
	String BG_PATH = "fonds" + PATH_SEPARATOR;
	String BULLET_PATH = "boules" + PATH_SEPARATOR;
	String WALL_PATH = "murs" + PATH_SEPARATOR;
	String CHAR_PATH = "personnages" + PATH_SEPARATOR;
	String SOUNDS_PATH = "sons" + PATH_SEPARATOR;
	String BG_CHOICE = BG_PATH + "fondchoix.jpg";
	String BG_ARENA = BG_PATH + "fondarene.jpg";
	String SPRITE_EXT = ".gif";
	String CHAR = "perso";
	String BULLET_SPRITE = BULLET_PATH + "boule.gif";
	String WALL_SPRITE = WALL_PATH + "mur.gif";
	String WALK = "marche";
	String TOUCH = "touche";
	String DEAD = "mort";
	String STR_SEPARATOR = "~";
	String CONNEXION = "connexion";
	String RECEPTION = "réception";
	String DECONNEXION = "déconnexion";
	String PSEUDO = "pseudo";
	String SERVER = "serveur";
	String CLIENT = "client";
	String CHAT = "chat";
	String CHAT_SEPARATOR = " > ";
	String AJOUT_MUR = "ajout mur";
	String AJOUT_PANEL_MURS = "ajout panel murs";
	String AJOUT_LABEL_JEU = "ajout label jeu";
	String AJOUT_PANEL_JEU = "ajout panel jeu";
	String AJOUT_CHAT = "ajout chat";
	String MODIF_CHAT = "modif chat";
	int PORT = 6666;
	int CHAR_MAX = 3;
	int CHAR_WIDTH = 40;
	int CHAR_HEIGHT = 45;
	int CHAR_TITLE_HEIGHT = 8;
	int GAUCHE = 0;
	int DROITE = 1;
	int ARENA_WIDTH = 800;
	int ARENA_HEIGHT = 600;
	int WALLS_MAX = 20;
	int WALL_SIZE = 35;
	int HP_MAX = 10;
	int GAIN = 1;
	int PERTE = 2;
}
