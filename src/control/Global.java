package control;

/**
 * Interface contenant les globales utiles pour toutes les classes
 *
 * @author JKerboeuf
 */
public interface Global {
	// General settings
	int PORT = 6666;
	int WALLS_MAX = 20;
	int WALK_SPEED = 10;
	int HP_MAX = 10;
	int GAIN = 1;
	int PERTE = 2;
	int BALL_SPEED = 10;
	long ANIM_DELAY = 150;
	String CHAT_SEPARATOR = " > ";

	// Path
	String SPRITE_EXT = ".gif";
	String BG_CHOICE = "fonds/fondchoix.jpg";
	String BG_ARENA = "fonds/fondarene.jpg";
	String CHAR_PATH = "personnages/perso";
	String BALL_SPRITE = "boules/boule.gif";
	String WALL_SPRITE = "murs/mur.gif";
	String SOUND_WELCOME = "sons/welcome.wav";
	String SOUND_PREV = "sons/precedent.wav";
	String SOUND_NEXT = "sons/suivant.wav";
	String SOUND_GO = "sons/go.wav";
	String[] SOUNDS_ARENA = { "sons/fight.wav", "sons/hurt.wav", "sons/death.wav" };

	// Order
	String STR_SEPARATOR = "~";
	String CONNEXION = "connexion";
	String RECEPTION = "réception";
	String DECONNEXION = "déconnexion";
	String AJOUT_MUR = "ajout mur";
	String AJOUT_PANEL_MURS = "ajout panel murs";
	String AJOUT_PANEL_JEU = "ajout panel jeu";
	String AJOUT_LABEL_JEU = "ajout label jeu";
	String AJOUT_CHAT = "ajout chat";
	String MODIF_CHAT = "modif chat";
	String CHAT = "chat";
	String PSEUDO = "pseudo";
	String ACTION = "action";
	String SERVER = "serveur";
	String CLIENT = "client";
	String JOUE_SON = "joue son";
	int FIGHT = 0;
	int HURT = 1;
	int DEATH = 2;

	// Animation
	String WALK = "marche";
	String HIT = "touche";
	String DEAD = "mort";
	int ANIM_STEPS_WALK = 4;
	int ANIM_STEPS_HIT = 2;
	int ANIM_STEPS_DEAD = 2;
	int GAUCHE = 0;
	int DROITE = 1;
	int HAUT = 2;
	int BAS = 3;

	// Objects
	int CHAR_TYPES = 3;
	int CHAR_WIDTH = 40;
	int CHAR_HEIGHT = 45;
	int CHAR_TITLE_HEIGHT = 8;
	int ARENA_WIDTH = 800;
	int ARENA_HEIGHT = 600;
	int WALL_SIZE = 35;
	int BALL_SIZE = 20;
}
