package vue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntreeJeu extends JFrame {

	private JPanel contentPane;
	private JTextField txtServerIp;

	/**
	 * Event sur le clic du bouton start
	 */
	private void btnStart_clic() {
		new Arene().setVisible(true);
		this.dispose();
	}

	/**
	 * Event sur le clic du bouton connect
	 */
	private void btnConnect_clic() {
		new ChoixJoueur().setVisible(true);
		this.dispose();
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
	public EntreeJeu() {
		setTitle("Urban Marginal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 364, 144);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFond = new JLabel("");
		lblFond.setBounds(0, 0, 46, 14);
		contentPane.add(lblFond);
		
		JLabel lblNewLabel = new JLabel("Start a server :");
		lblNewLabel.setBounds(10, 11, 81, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Connect to a server :");
		lblNewLabel_1.setBounds(10, 39, 114, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnStartServer = new JButton("Start");
		btnStartServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStart_clic();
			}
		});
		btnStartServer.setBounds(249, 7, 89, 23);
		contentPane.add(btnStartServer);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnConnect_clic();
			}
		});
		btnConnect.setBounds(249, 35, 89, 23);
		contentPane.add(btnConnect);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnExit_clic();
			}
		});
		btnExit.setBounds(10, 71, 328, 23);
		contentPane.add(btnExit);
		
		txtServerIp = new JTextField();
		txtServerIp.setBounds(125, 36, 114, 20);
		contentPane.add(txtServerIp);
		txtServerIp.setColumns(10);
	}
}
