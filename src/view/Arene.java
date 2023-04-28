package view;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import control.Controle;
import control.Global;

/**
 * frame de l'arene de jeu
 * 
 * @author JKerboeuf
 */
public class Arene extends JFrame implements Global {
	/**
	 * panel principal
	 */
	private JPanel contentPane;
	/**
	 * panel des murs
	 */
	private JPanel jpnMurs;
	/**
	 * panel du jeu, contient les joueurs et les boules
	 */
	private JPanel jpnJeu;
	/**
	 * champ de saisie du chat
	 */
	private JTextField txtInput;
	/**
	 * champ de texte du chat
	 */
	private JTextArea txtChat;
	/**
	 * controleur
	 */
	private Controle controle;
	/**
	 * booleen pour savoir si le chat doit etre affiché ou pas
	 */
	private boolean client;

	/**
	 * getter du chat
	 *
	 * @return le texte entier du chat
	 */
	public String getTxtChat() {
		return txtChat.getText();
	}

	/**
	 * setter du chat
	 *
	 * @param text le text pour remplacer le chat
	 */
	public void setTxtChat(String text) {
		this.txtChat.setText(text);
		this.txtChat.setCaretPosition(this.txtChat.getDocument().getLength());
	}

	/**
	 * getter du panel des murs
	 *
	 * @return le panel des murs
	 */
	public JPanel getJpnMurs() {
		return jpnMurs;
	}

	/**
	 * setter du panel des murs
	 *
	 * @param jpnMurs le panel des murs à set
	 */
	public void setJpnMurs(JPanel jpnMurs) {
		this.jpnMurs.add(jpnMurs);
		this.jpnMurs.repaint();
	}

	/**
	 * getter du panel de jeu
	 *
	 * @return le panel du jeu
	 */
	public JPanel getJpnJeu() {
		return jpnJeu;
	}

	/**
	 * setter du panel de jeu
	 *
	 * @param jpnJeu le panel de jeu à remplacer
	 */
	public void setJpnJeu(JPanel jpnJeu) {
		this.jpnJeu.removeAll();
		this.jpnJeu.add(jpnJeu);
		this.jpnJeu.repaint();
		this.contentPane.requestFocus();
	}

	/**
	 * ajoute les murs
	 *
	 * @param unMur mur à ajouter
	 */
	public void ajoutMurs(Object unMur) {
		jpnMurs.add((JLabel) unMur);
		jpnMurs.repaint();
	}

	/**
	 * ajoute un label de jeu
	 *
	 * @param label le label à ajouter
	 */
	public void ajoutLabelJeu(JLabel label) {
		this.jpnJeu.add(label);
		this.jpnJeu.repaint();
	}

	/**
	 * ajoute une ligne de texte au chat
	 *
	 * @param line ligne de texte à ajouter
	 */
	public void ajoutTchat(String line) {
		this.txtChat.setText(this.txtChat.getText() + line + "\r\n");
		this.txtChat.setCaretPosition(this.txtChat.getDocument().getLength());
	}

	/**
	 * évenement de touche de clavier
	 * vérifie la touche pour envoyer du texte au chat
	 *
	 * @param e evenement clavier
	 */
	public void txtInput_KeyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!this.txtInput.getText().equals("")) {
				this.controle.evenementArene(this.txtInput.getText());
				this.txtInput.setText("");
			}
			this.contentPane.requestFocus();
		}
	}

	/**
	 * évenement de touche de clavier
	 * vérifie les fleches pour faire bouger le personnag
	 *
	 * @param e evenement clavier
	 */
	public void contentPane_KeyPressed(KeyEvent e) {
		int key = -1;
		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_UP:
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_SPACE:
				key = e.getKeyCode();
				break;
		}
		if (key != -1) {
			this.controle.evenementArene(key);
		}
	}

	/**
	 * constructeur
	 *
	 * @param controle le controleur
	 * @param typeJeu  le type de jeu (client / serveur)
	 */
	public Arene(Controle controle, String typeJeu) {
		this.client = typeJeu.equals(CLIENT);
		this.getContentPane().setPreferredSize(new Dimension(ARENA_WIDTH, ARENA_HEIGHT + 200));
		this.pack();
		this.setResizable(false);
		setTitle("Arena");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 816, 816);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		jpnJeu = new JPanel();
		jpnJeu.setBounds(0, 0, ARENA_WIDTH, ARENA_HEIGHT);
		jpnJeu.setOpaque(false);
		jpnJeu.setLayout(null);
		contentPane.add(jpnJeu);

		jpnMurs = new JPanel();
		jpnMurs.setBounds(0, 0, ARENA_WIDTH, ARENA_HEIGHT);
		jpnMurs.setOpaque(false);
		jpnMurs.setLayout(null);
		contentPane.add(jpnMurs);

		JScrollPane jspChat = new JScrollPane();
		jspChat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jspChat.setBounds(0, 630, 800, 170);
		contentPane.add(jspChat);

		txtChat = new JTextArea();
		txtChat.setEditable(false);
		jspChat.setViewportView(txtChat);

		JLabel lblFond = new JLabel("");
		URL resource = getClass().getClassLoader().getResource(BG_ARENA);
		lblFond.setIcon(new ImageIcon(resource));
		lblFond.setBounds(0, 0, ARENA_WIDTH, ARENA_HEIGHT);
		contentPane.add(lblFond);

		if (this.client) {
			contentPane.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					contentPane_KeyPressed(e);
				}
			});

			txtChat.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					contentPane_KeyPressed(e);
				}
			});

			txtInput = new JTextField();
			txtInput.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					txtInput_KeyPressed(e);
				}
			});
			txtInput.setBounds(0, 600, 800, 30);
			contentPane.add(txtInput);
			txtInput.setColumns(10);
		}

		this.controle = controle;
	}
}
