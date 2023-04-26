package view;

import java.awt.Dimension;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.Global;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Arene extends JFrame implements Global {
	private JPanel contentPane;
	private JTextField txtInput;
	private JTextArea txtChat;

	// Create the frame.
	public Arene() {
		this.getContentPane().setPreferredSize(new Dimension(800, 800));
		this.pack();
		this.setResizable(false);
		setTitle("Arena");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 816, 816);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

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
		lblFond.setBounds(0, 0, 800, 600);
		contentPane.add(lblFond);
	}
}
