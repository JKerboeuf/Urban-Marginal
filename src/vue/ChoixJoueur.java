package vue;

import java.awt.EventQueue;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChoixJoueur extends JFrame {

	private JPanel contentPane;
	private JTextField txtPseudo;
	private JLabel lblLeft;
	private JLabel lblRight;
	private JLabel lblGo;

	/**
	 * Event sur le clic du label gauche
	 */
	private void lblLeft_clic() {
		
	}

	/**
	 * Event sur le clic du label droit
	 */
	private void lblRight_clic() {
		
	}

	/**
	 * Event sur le clic du label go
	 */
	private void lblGo_clic() {
		
	}
	
	/**
	 * Create the frame.
	 */
	public ChoixJoueur() {
		setTitle("Choice");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 415, 313);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFond = new JLabel("");
		lblFond.setBounds(0, 0, 400, 275);
		contentPane.add(lblFond);
		
		String chemin = "fonds/fondchoix.jpg";
		URL resource = getClass().getClassLoader().getResource(chemin);
		lblFond.setIcon(new ImageIcon(resource));
		
		txtPseudo = new JTextField();
		txtPseudo.setHorizontalAlignment(SwingConstants.CENTER);
		txtPseudo.setBounds(143, 246, 120, 19);
		contentPane.add(txtPseudo);
		txtPseudo.setColumns(10);
		
		lblLeft = new JLabel("");
		lblLeft.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblLeft_clic();
			}
		});
		lblLeft.setBounds(64, 145, 32, 47);
		contentPane.add(lblLeft);
		
		lblRight = new JLabel("");
		lblRight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblRight_clic();
			}
		});
		lblRight.setBounds(302, 145, 25, 42);
		contentPane.add(lblRight);
		
		lblGo = new JLabel("");
		lblGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblGo_clic();
			}
		});
		lblGo.setBounds(309, 196, 67, 67);
		contentPane.add(lblGo);
	}

}
