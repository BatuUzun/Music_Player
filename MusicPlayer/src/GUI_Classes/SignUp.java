package GUI_Classes;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import DatabaseOperations.Database;
import SystemClasses.Encryptor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTextField;
	private JPasswordField passwordField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField passwordTextField;
	private JTextField nameTextField;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
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
	public SignUp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 718, 457);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(36, 36, 36));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("USERNAME:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(32, 64, 90, 14);
		contentPane.add(lblNewLabel);
		
		usernameTextField = new JTextField();
		usernameTextField.setForeground(new Color(255, 255, 255));
		usernameTextField.setBackground(new Color(36, 36, 36));
		usernameTextField.setBounds(166, 61, 147, 20);
		contentPane.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("PASSWORD:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(32, 103, 90, 14);
		contentPane.add(lblNewLabel_1);
		
		JPanel panelName = new JPanel();
		panelName.setBackground(new Color(36, 36, 36));
		panelName.setBounds(32, 128, 417, 42);
		contentPane.add(panelName);
		panelName.setLayout(null);
		panelName.setVisible(false);
		
		JLabel lblNewLabel_2 = new JLabel("NAME:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(0, 17, 58, 14);
		panelName.add(lblNewLabel_2);
		
		nameTextField = new JTextField();
		nameTextField.setForeground(new Color(255, 255, 255));
		nameTextField.setBackground(new Color(36, 36, 36));
		nameTextField.setBounds(135, 14, 147, 20);
		panelName.add(nameTextField);
		nameTextField.setColumns(10);
		
		JCheckBox listenerCheckBox = new JCheckBox("I am a listener");
		listenerCheckBox.setBackground(new Color(36, 36, 36));
		listenerCheckBox.setForeground(new Color(255, 255, 255));
		listenerCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameTextField.setText("");
				panelName.setVisible(false);
			}
		});
		listenerCheckBox.setSelected(true);
		buttonGroup.add(listenerCheckBox);
		listenerCheckBox.setBounds(32, 179, 112, 23);
		contentPane.add(listenerCheckBox);
		
		JCheckBox singerCheckBox = new JCheckBox("I am a singer");
		singerCheckBox.setBackground(new Color(36, 36, 36));
		singerCheckBox.setForeground(new Color(255, 255, 255));
		singerCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelName.setVisible(true);
			}
		});
		buttonGroup.add(singerCheckBox);
		singerCheckBox.setBounds(166, 179, 120, 23);
		contentPane.add(singerCheckBox);
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(36, 36, 36));
		panel.setBounds(166, 94, 177, 23);
		contentPane.add(panel);
		panel.setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(new Color(255, 255, 255));
		passwordField.setBackground(new Color(36, 36, 36));
		passwordField.setBounds(0, 0, 147, 20);
		panel.add(passwordField);
		
		passwordTextField = new JTextField();
		passwordTextField.setForeground(new Color(255, 255, 255));
		passwordTextField.setBackground(new Color(36, 36, 36));
		passwordTextField.setBounds(0, 0, 147, 20);
		panel.add(passwordTextField);
		passwordTextField.setColumns(10);
		
		
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
		showMyPasswordCheckBox.setBounds(360, 99, 147, 23);
		contentPane.add(showMyPasswordCheckBox);
		
		
		
		JButton goBackBtn = new JButton("BACK");
		goBackBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogInOrSignUp log = new LogInOrSignUp();
				log.setVisible(true);
				dispose();
			}
		});
		goBackBtn.setBounds(105, 312, 112, 23);
		contentPane.add(goBackBtn);
		
		JLabel missingInfoLbl = new JLabel("Missing information. Please try again!");
		missingInfoLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		missingInfoLbl.setForeground(new Color(255, 0, 0));
		missingInfoLbl.setBounds(32, 250, 417, 32);
		contentPane.add(missingInfoLbl);
		missingInfoLbl.setVisible(false);
		
		JLabel usernameInfoLbl = new JLabel("Username is already TAKEN!");
		usernameInfoLbl.setForeground(new Color(255, 255, 255));
		usernameInfoLbl.setBounds(360, 64, 177, 14);
		contentPane.add(usernameInfoLbl);
		usernameInfoLbl.setVisible(false);
		
		JButton btnNewButton = new JButton("SIGN UP");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean isUsernameExist = Database.checkUsername(usernameTextField.getText());
				if(isUsernameExist) {
					usernameInfoLbl.setVisible(true);
				}
				else {
					usernameInfoLbl.setVisible(false);
					
					String password = String.valueOf(passwordField.getPassword());
					
					
					if(!usernameTextField.getText().isEmpty() && (!password.isEmpty() || !passwordTextField.getText().isEmpty())) {
						
						if(singerCheckBox.isSelected()) {
							if(!nameTextField.getText().isEmpty()) {
								
								missingInfoLbl.setVisible(false);
								password = Encryptor.encrypt(password);
								
								Database.insertIntoUser_Info(usernameTextField.getText(), password, "singer", nameTextField.getText());
								JOptionPane.showMessageDialog(panel, "Your account is created!", "INFO", JOptionPane.INFORMATION_MESSAGE);
							}
							else {
								
								missingInfoLbl.setVisible(true);
							}
								
							
						}
						else {
							
							missingInfoLbl.setVisible(false);
							
							password = Encryptor.encrypt(password);
							
							Database.insertIntoUser_Info(usernameTextField.getText(), password, "listener", nameTextField.getText());
							JOptionPane.showMessageDialog(panel, "Your account is created!", "INFO", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else {
						
						missingInfoLbl.setVisible(true);
					}
					
					
				}
				
				
				
			}
		});
		btnNewButton.setBounds(325, 312, 112, 23);
		contentPane.add(btnNewButton);
		
		
		
	}
}
