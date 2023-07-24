package GUI_Classes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DatabaseOperations.Database;
import SystemClasses.Encryptor;
import UserClasses.User;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;

import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LogIn extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTextField;
	private JPasswordField passwordField;
	private JTextField passwordTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn frame = new LogIn();
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
	public LogIn() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 426);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(36, 36, 36));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		JButton btnNewButton_1 = new JButton("OK");
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("USERNAME:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(34, 84, 90, 14);
		contentPane.add(lblNewLabel);
		
		usernameTextField = new JTextField();
		usernameTextField.setForeground(new Color(255, 255, 255));
		usernameTextField.setBackground(new Color(36, 36, 36));
		usernameTextField.setColumns(10);
		usernameTextField.setBounds(168, 81, 147, 20);
		contentPane.add(usernameTextField);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(36, 36, 36));
		panel.setLayout(null);
		panel.setBounds(168, 114, 177, 23);
		contentPane.add(panel);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(new Color(255, 255, 255));
		passwordField.setBackground(new Color(36, 36, 36));
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnNewButton_1.doClick();
				}
			}
		});
		passwordField.setBounds(0, 0, 147, 20);
		panel.add(passwordField);
		
		passwordTextField = new JTextField();
		passwordTextField.setForeground(new Color(255, 255, 255));
		passwordTextField.setBackground(new Color(36, 36, 36));
		passwordTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnNewButton_1.doClick();
				}
			}
		});
		passwordTextField.setColumns(10);
		passwordTextField.setBounds(0, 0, 147, 20);
		panel.add(passwordTextField);
		
		JLabel lblNewLabel_1 = new JLabel("PASSWORD:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(34, 123, 90, 14);
		contentPane.add(lblNewLabel_1);
		
		JCheckBox showMyPasswordCheckBox = new JCheckBox("Show my password");
		showMyPasswordCheckBox.setBackground(new Color(36, 36, 36));
		showMyPasswordCheckBox.setForeground(new Color(255, 255, 255));
		showMyPasswordCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(showMyPasswordCheckBox.isSelected()) {
					passwordTextField.setText(String.valueOf(passwordField.getPassword()));
					passwordField.setVisible(false);
					passwordTextField.setVisible(true);
				}
				else {
					passwordField.setText(passwordTextField.getText());
					passwordTextField.setVisible(false);
					passwordField.setVisible(true);
				}
			}
		});
		showMyPasswordCheckBox.setBounds(362, 119, 147, 23);
		contentPane.add(showMyPasswordCheckBox);
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogInOrSignUp log = new LogInOrSignUp();
				log.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(146, 260, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel wrongLbl = new JLabel("Wrong username or password!");
		wrongLbl.setForeground(new Color(255, 0, 0));
		wrongLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		wrongLbl.setBounds(34, 187, 281, 46);
		contentPane.add(wrongLbl);
		wrongLbl.setVisible(false);
		
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//if(usernameTextField.getText().isEmpty() || passwordField.getText().isEmpty() || passwordTextField.getText().isEmpty())
				
				String password; 
				if(showMyPasswordCheckBox.isSelected())
					password = String.valueOf(passwordTextField.getText());
				else
					password = String.valueOf(passwordField.getPassword());
					
				if(!usernameTextField.getText().isEmpty() && !password.isEmpty()) {		
					
					password = Encryptor.encrypt(password);
					
					boolean isCorrect = Database.checkLogInInformation(usernameTextField.getText(), password);
					if(isCorrect) {
						User user = Database.getUser(usernameTextField.getText());
						MusicScreen musicScreen = new MusicScreen(user);
						musicScreen.setVisible(true);
						dispose();
					}
					else {
						wrongLbl.setVisible(true);
					}
					
				}
			}
		});
		btnNewButton_1.setBounds(347, 260, 89, 23);
		contentPane.add(btnNewButton_1);
		
		
	}
}
