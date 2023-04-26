package view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.net.URL;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import control.Controle;
import control.Global;

public class ChoixJoueur extends JFrame implements Global {
	private int numPerso;
	private Controle controle;
	private JPanel contentPane;
	private JTextField txtPseudo;
	private JLabel lblPerso;

	private void sourisNormale() {
		contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	private void sourisDoigt() {
		contentPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	/**
	 * Event sur le clic du label gauche
	 */
	private void lblLeft_clic() {
		if (this.numPerso > 1) {
			this.numPerso--;
		} else {
			this.numPerso = CHAR_MAX;
		}
		this.affichePerso();
	}

	/**
	 * Event sur le clic du label droit
	 */
	private void lblRight_clic() {
		if (this.numPerso < CHAR_MAX) {
			this.numPerso++;
		} else {
			this.numPerso = 1;
		}
		this.affichePerso();
	}

	/**
	 * Event sur le clic du label go
	 */
	private void lblGo_clic() {
		if (this.txtPseudo.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "La saisie du pseudo est obligatoire");
			this.txtPseudo.requestFocus();
		} else {
			this.controle.evenementChoixJoueur(this.txtPseudo.getText(), this.numPerso);
		}
	}

	private void affichePerso() {
		String fileName = CHAR + this.numPerso + WALK + 1 + "d" + 1 + SPRITE_EXT;
		URL resource = getClass().getClassLoader().getResource(CHAR_PATH + fileName);
		this.lblPerso.setIcon(new ImageIcon(resource));
	}

	/**
	 * Create the frame.
	 */
	public ChoixJoueur(Controle controle) {
		this.getContentPane().setPreferredSize(new Dimension(400, 275));
		this.pack();
		this.setResizable(false);
		setTitle("Choice");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 415, 313);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblPerso = new JLabel("");
		lblPerso.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerso.setBounds(140, 113, 123, 122);
		contentPane.add(lblPerso);

		txtPseudo = new JTextField();
		txtPseudo.setHorizontalAlignment(SwingConstants.CENTER);
		txtPseudo.setBounds(140, 246, 123, 22);
		contentPane.add(txtPseudo);
		txtPseudo.setColumns(10);

		JLabel lblLeft = new JLabel("");
		lblLeft.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblLeft_clic();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				sourisDoigt();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				sourisNormale();
			}
		});
		lblLeft.setBounds(64, 145, 32, 47);
		contentPane.add(lblLeft);

		JLabel lblRight = new JLabel("");
		lblRight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblRight_clic();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				sourisDoigt();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				sourisNormale();
			}
		});
		lblRight.setBounds(302, 145, 25, 42);
		contentPane.add(lblRight);

		JLabel lblGo = new JLabel("");
		lblGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblGo_clic();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				sourisDoigt();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				sourisNormale();
			}
		});
		lblGo.setBounds(309, 196, 67, 67);
		contentPane.add(lblGo);

		JLabel lblFond = new JLabel("");
		lblFond.setBounds(0, 0, 400, 275);
		URL resource = getClass().getClassLoader().getResource(BG_CHOICE);
		lblFond.setIcon(new ImageIcon(resource));
		contentPane.add(lblFond);

		this.controle = controle;
		this.numPerso = 1;
		this.affichePerso();
		txtPseudo.requestFocus();
	}
}
