package GUI_Classes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class LogInOrSignUp extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInOrSignUp frame = new LogInOrSignUp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogInOrSignUp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 467);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(36, 36, 36));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton signUpBtn = new JButton("SIGN UP");
		signUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUp signUp = new SignUp();
				signUp.setVisible(true);
				dispose();
				
			}
		});
		signUpBtn.setBounds(324, 265, 105, 23);
		contentPane.add(signUpBtn);
		
		JButton logInBtn = new JButton("LOG IN");
		logInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogIn log = new LogIn();
				log.setVisible(true);
				dispose();
			}
		});
		logInBtn.setBounds(156, 265, 105, 23);
		contentPane.add(logInBtn);
		
		JLabel lblNewLabel = new JLabel("BATU MUSIC PLAYER");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel.setBounds(132, 33, 420, 199);
		contentPane.add(lblNewLabel);
	}
}
