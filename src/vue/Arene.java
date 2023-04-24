package vue;

import java.awt.Dimension;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class Arene extends JFrame {

	private JPanel contentPane;
	private JTextField txtInput;

	/**
	 * Create the frame.
	 */
	public Arene() {
		this.getContentPane().setPreferredSize(new Dimension(800, 800));
		this.pack();
		setTitle("Arena");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 816, 816);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFond = new JLabel("");
		lblFond.setBounds(0, 0, 800, 600);
		contentPane.add(lblFond);
		
		String chemin = "fonds/fondarene.jpg";
		URL resource = getClass().getClassLoader().getResource(chemin);
		lblFond.setIcon(new ImageIcon(resource));
		
		txtInput = new JTextField();
		txtInput.setBounds(0, 600, 800, 30);
		contentPane.add(txtInput);
		txtInput.setColumns(10);
		
		JTextArea txtChat = new JTextArea();
		txtChat.setBounds(0, 630, 800, 170);
		contentPane.add(txtChat);
	}
}
