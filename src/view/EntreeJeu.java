package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import control.Controle;

public class EntreeJeu extends JFrame {
	private Controle controle;
	private JPanel contentPane;
	private JTextField txtServerIp;

	/**
	 * Event sur le clic du bouton start
	 */
	private void btnStart_clic() {
		this.controle.evenementEntreeJeu("serveur");
	}

	/**
	 * Event sur le clic du bouton connect
	 */
	private void btnConnect_clic() {
		this.controle.evenementEntreeJeu(this.txtServerIp.getText());
	}

	/**
	 * Event sur le clic du bouton exit
	 */
	private void btnExit_clic() {
		System.exit(0);
	}

	/**
	 * Create the frame.
	 */
	public EntreeJeu(Controle controle) {
		setTitle("Urban Marginal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 383, 144);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblStartServer = new JLabel("Start a server :");
		lblStartServer.setBounds(10, 11, 131, 14);
		contentPane.add(lblStartServer);

		JLabel lblConnect = new JLabel("Connect to a server :");
		lblConnect.setBounds(10, 39, 131, 14);
		contentPane.add(lblConnect);

		JButton btnStartServer = new JButton("Start");
		btnStartServer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnStart_clic();
			}
		});
		btnStartServer.setBounds(275, 7, 89, 23);
		contentPane.add(btnStartServer);

		JButton btnConnect = new JButton("Connect");
		btnConnect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnConnect_clic();
			}
		});
		btnConnect.setBounds(275, 35, 89, 23);
		contentPane.add(btnConnect);

		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnExit_clic();
			}
		});
		btnExit.setBounds(275, 71, 89, 23);
		contentPane.add(btnExit);

		txtServerIp = new JTextField();
		txtServerIp.setText("127.0.0.1");
		txtServerIp.setBounds(151, 36, 114, 20);
		contentPane.add(txtServerIp);
		txtServerIp.setColumns(10);

		this.controle = controle;
	}
}
