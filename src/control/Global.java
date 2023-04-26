package control;

public interface Global {
	String SEPARATOR = "/";
	String BG_PATH = "fonds" + SEPARATOR;
	String BULLET_PATH = "boules" + SEPARATOR;
	String WALL_PATH = "murs" + SEPARATOR;
	String CHAR_PATH = "personnages" + SEPARATOR;
	String SOUNDS_PATH = "sons" + SEPARATOR;
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
	String AJOUTMUR = "ajout mur";
	String AJOUTPANELMURS = "ajout panel murs";
	int PORT = 6666;
	int CHAR_MAX = 3;
	int ARENA_WIDTH = 800;
	int ARENA_HEIGHT = 600;
	int WALLS_MAX = 20;
	int WALL_SIZE = 35;
	int HP_MAX = 10;
	int GAIN = 1;
	int PERTE = 2;
}
