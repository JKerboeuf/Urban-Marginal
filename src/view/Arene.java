package view;

import java.awt.Dimension;
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

public class Arene extends JFrame implements Global {
	private JPanel contentPane;
	private JPanel jpnMurs;
	private JPanel jpnJeu;
	private JTextField txtInput;
	private JTextArea txtChat;
	private Controle controle;

	public String getTxtChat() {
		return txtChat.getText();
	}

	public void setTxtChat(String text) {
		this.txtChat.setText(text);
	}

	public JPanel getJpnMurs() {
		return jpnMurs;
	}

	public void setJpnMurs(JPanel jpnMurs) {
		this.jpnMurs.add(jpnMurs);
		this.jpnMurs.repaint();
	}

	public JPanel getJpnJeu() {
		return jpnJeu;
	}

	public void setJpnJeu(JPanel jpnJeu) {
		this.jpnJeu.removeAll();
		this.jpnJeu.add(jpnJeu);
		this.jpnJeu.repaint();
	}

	public void ajoutMurs(Object unMur) {
		jpnMurs.add((JLabel) unMur);
		jpnMurs.repaint();
	}

	public void ajoutLabelJeu(JLabel label) {
		this.jpnJeu.add(label);
		this.jpnJeu.repaint();
	}

	public void ajoutTchat(String line) {
		txtChat.setText(txtChat.getText() + line + "\r\n");
	}

	public void txtSaisie_KeyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER && !this.txtInput.getText().equals("")) {
			this.controle.evenementArene(this.txtInput.getText());
			this.txtInput.setText("");
		}
	}

	// Create the frame.
	public Arene(Controle controle) {
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

		txtInput = new JTextField();
		txtInput.setBounds(0, 600, 800, 30);
		contentPane.add(txtInput);
		txtInput.setColumns(10);

		JScrollPane jspChat = new JScrollPane();
		jspChat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jspChat.setBounds(0, 630, 800, 170);
		contentPane.add(jspChat);

		txtChat = new JTextArea();
		jspChat.setViewportView(txtChat);

		JLabel lblFond = new JLabel("");
		URL resource = getClass().getClassLoader().getResource(BG_ARENA);
		lblFond.setIcon(new ImageIcon(resource));
		lblFond.setBounds(0, 0, ARENA_WIDTH, ARENA_HEIGHT);
		contentPane.add(lblFond);

		this.controle = controle;
	}
}
