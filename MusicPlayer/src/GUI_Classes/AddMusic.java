package GUI_Classes;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DatabaseOperations.Database;
import UserClasses.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;



public class AddMusic extends JFrame {

	private JPanel contentPane;
	private JTextField songNameTextField;
	private File selectedFile;
	private JLabel durationPreviewLbl;
	private File selectedIconFile;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMusic frame = new AddMusic(null);
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
	public AddMusic(User user) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 672, 455);
		contentPane = new JPanel();
contentPane.setBackground(new Color(36, 36, 36));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 153, 0));
		panel.setBounds(124, 241, 505, 130);
		contentPane.add(panel);
		
		panel.setLayout(null);
		
		JLabel imagePreviewLbl = new JLabel("ICON");
		imagePreviewLbl.setBounds(10, 11, 120, 100);
		panel.add(imagePreviewLbl);
		
		JLabel singerPreviewLbl = new JLabel("New label");
		singerPreviewLbl.setBounds(141, 29, 325, 14);
		panel.add(singerPreviewLbl);
		
		JLabel songNamePreviewLbl = new JLabel("Song Name");
		songNamePreviewLbl.setBounds(141, 54, 325, 14);
		panel.add(songNamePreviewLbl);
		
		durationPreviewLbl = new JLabel("Duration");
		durationPreviewLbl.setBounds(140, 79, 230, 14);
		panel.add(durationPreviewLbl);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl = new JLabel("Selected file:");
		lbl.setForeground(new Color(255, 255, 255));
		lbl.setBounds(10, 57, 93, 14);
		contentPane.add(lbl);
		
		JLabel selectedFileLbl = new JLabel("None");
		selectedFileLbl.setForeground(new Color(255, 255, 255));
		selectedFileLbl.setBounds(156, 57, 490, 14);
		contentPane.add(selectedFileLbl);
		
		JButton selectFileBtn = new JButton("SELECT FILE");
		selectFileBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				
				// Show the dialog and capture the user's selection
				int result = fileChooser.showOpenDialog(null);
				selectedFile = null;
				// If the user chooses a file
				if (result == JFileChooser.APPROVE_OPTION) {
					// Get the selected file
					selectedFile = fileChooser.getSelectedFile();
					String extension = selectedFile.getAbsolutePath();
					extension = extension.substring(extension.lastIndexOf("."), extension.length());
					if(extension.equalsIgnoreCase(".wav")) {
						selectedFileLbl.setText(selectedFile.getAbsolutePath());
						
						// Create a destination folder
						String destinationFolderPath = "./musics";
						File destinationFolder = new File(destinationFolderPath);

						// Copy the selected file to the destination folder
						try {
							Path destinationPath = destinationFolder.toPath().resolve(selectedFile.getName());
							Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
							System.out.println("File saved to: " + destinationPath);
						} catch (IOException en) {
							en.printStackTrace();
						}
						
						AudioInputStream audioInputStream;
						try {
							audioInputStream = AudioSystem.getAudioInputStream(selectedFile);
							AudioFormat format = audioInputStream.getFormat();
							long frames = audioInputStream.getFrameLength();
							double durationInSeconds = (frames+0.0) / format.getFrameRate();
							
							String min = String.valueOf((int) durationInSeconds / 60);
							String seconds = String.valueOf((int) durationInSeconds % 60);
							
							if(durationInSeconds / 60 < 10)
								min = "0"+min;
							if(durationInSeconds % 60 < 10)
								seconds = "0"+seconds;
							durationPreviewLbl.setText(min+":"+seconds);
						} catch (UnsupportedAudioFileException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					
					
			}
		}});
		selectFileBtn.setBounds(10, 23, 139, 23);
		contentPane.add(selectFileBtn);
		
		
		
		JLabel lblNewLabel = new JLabel("Singer:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(10, 100, 93, 14);
		contentPane.add(lblNewLabel);
		
		JLabel singerLbl = new JLabel("New label");
		singerLbl.setForeground(new Color(255, 255, 255));
		singerLbl.setBounds(156, 100, 490, 14);
		contentPane.add(singerLbl);
		
		JLabel lblNewLabel_1 = new JLabel("Path:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(10, 163, 120, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel picturePathLbl = new JLabel("None");
		picturePathLbl.setForeground(new Color(255, 255, 255));
		picturePathLbl.setBounds(156, 163, 490, 14);
		contentPane.add(picturePathLbl);
		
		JButton btnNewButton = new JButton("SELECT PICTURE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				
				// Show the dialog and capture the user's selection
				int result = fileChooser.showOpenDialog(null);

				// If the user chooses a file
				if (result == JFileChooser.APPROVE_OPTION) {
					// Get the selected file
					selectedIconFile = fileChooser.getSelectedFile();
					String extension = selectedIconFile.getAbsolutePath();
					extension = extension.substring(extension.lastIndexOf("."), extension.length());
					System.out.println("extension: "+extension);
					if(extension.equalsIgnoreCase(".jpeg") || extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg")) {
						picturePathLbl.setText(selectedIconFile.getAbsolutePath());
						String destinationFolderPath = "./pictures";
						File destinationFolder = new File(destinationFolderPath);
						try {
							Path destinationPath = destinationFolder.toPath().resolve(selectedIconFile.getName());
							Files.copy(selectedIconFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
							System.out.println("File saved to: " + destinationPath);
						} catch (IOException en) {
							en.printStackTrace();
						}
						
						ImageIcon image = new ImageIcon("./pictures/"+selectedIconFile.getName());					
						imagePreviewLbl.setIcon(image);
					}
					
					
					// Create a destination folder
					

					// Copy the selected file to the destination folder
					
					
					
			}
			}
		});
		btnNewButton.setBounds(10, 129, 139, 23);
		contentPane.add(btnNewButton);
		
		
		
		JLabel lblNewLabel_3 = new JLabel("Song name:");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(10, 207, 120, 14);
		contentPane.add(lblNewLabel_3);
		
		songNameTextField = new JTextField();
		songNameTextField.setBorder(new LineBorder(new Color(255, 255, 255)));
		songNameTextField.setBackground(new Color(36, 36, 36));
		songNameTextField.setForeground(new Color(255, 255, 255));
		songNameTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				songNamePreviewLbl.setText(songNameTextField.getText());
			}
		});
		songNameTextField.setBounds(156, 204, 230, 20);
		contentPane.add(songNameTextField);
		songNameTextField.setColumns(10);
		
		JButton releaseBtn = new JButton("RELEASE SONG");
		releaseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!selectedFileLbl.getText().equalsIgnoreCase("none") && !picturePathLbl.getText().equalsIgnoreCase("none") && !songNameTextField.getText().isEmpty()) {
					
					Database.insertIntoMusics(selectedFile.getName(), durationPreviewLbl.getText(), user.getUsername(), songNameTextField.getText(), selectedIconFile.getName());
					//String file_song, String duration, String singer_username, String song_name, String file_icon
					JOptionPane.showMessageDialog(panel, "Your song is added!", "INFO", JOptionPane.INFORMATION_MESSAGE);
					
				}
				else {
					JOptionPane.showMessageDialog(panel, "MISSING INFORMATION", "WARNING!", JOptionPane.WARNING_MESSAGE);
				}
				
				
			}
		});
		releaseBtn.setBounds(336, 382, 139, 23);
		contentPane.add(releaseBtn);
		
		
		singerLbl.setText(((Singer)user).getSingerName());
		
		JButton cancelBtn = new JButton("CANCEL");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelBtn.setBounds(156, 382, 139, 23);
		contentPane.add(cancelBtn);
		singerPreviewLbl.setText(((Singer)(user)).getSingerName());
		
		JLabel lblNewLabel_2 = new JLabel("Recomended size:     Width: 120 Height:100");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(166, 133, 278, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Accepted file types: jpeg, png, jpg");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setBounds(425, 133, 231, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Accepted file types: wav");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setBounds(166, 27, 220, 14);
		contentPane.add(lblNewLabel_5);
		
		
		
		
	}
}
